/*
 * (c) Copyright 2015 Hewlett-Packard Development Company, L.P. 
 * --------------------------------------------------------------------------
 * This file contains proprietary trade secrets of Hewlett Packard Company.
 * No part of this file may be reproduced or transmitted in any form or by
 * any means, electronic or mechanical, including photocopying and
 * recording, for any purpose without the expressed written permission of
 * Hewlett Packard Company.
 */

package diag.server.applications;

import golan.utils.MyLog;
import net.sf.ehcache.CacheManager;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    14/05/2015 22:50
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 *  A cache for mapping all kind if info we need from Apps (PURPLE server).
 *  For example we need to map ApplicationKey to ApplicationId. This information is in PURPLE and we need to Cache'n'Rest it.
 *
 *  This class holds more than a single cache depending on the different types of information we need from PURPLE.
 *
 *  So what do you need to do if you want to adda another cache here?
 *  Copy and paste from AppKey2AppId :-)  Really !! there are comments in the relevant classes. Look for: [#AppKey2AppId#] to show you all the places you need to handle.
 *  [1] Create a class for you cache (extend form {@link diag.server.applications.AbsCache})
 *      Choose the Key properly and you can read more in the docs for {@link diag.server.applications.AppKey2AppIdCache}
 *  [2] Create a data member in this class ({@link diag.server.applications.AppsInfoCache}) to hold an instance of your cache
 *  [3] Initialize your cache in the {@link #initCache()}
 *      Nothing special from your side because the {@link diag.server.applications.AbsCache#initCache(net.sf.ehcache.CacheManager)} implementation is good enough.
 *      All you need to do is to call it and pass in the CacheManager
 *  [4] Write the code to update your cache.
 *      (before you go to this part you should first make sure that someone update the REST on PURPLE ({@link diag.server.applications.AppsInfoCache.AppsInfoRefreshTask#restCall2PurpleServer()}) to add new information you need to the result)
 *      It should be done in {@link diag.server.applications.AppsInfoCache.AppsInfoRefreshTask#run()}
 *      Preferably in a different method of your own (like {@link diag.server.applications.AppsInfoCache.AppsInfoRefreshTask#updateAppKey2AppId(javax.json.JsonObject)})
 *      This method is where you need "to do the actual work" for your cache.
 *      You will need to "parse" the JsonObject and get to the place where it holds your relevant info and update your cache.
 *      Actual cache update is also implemented in {@link diag.server.applications.AbsCache}.
 *      Just create a corresponding {@link java.util.Map} and call to {@link diag.server.applications.AbsCache#updateCache(java.util.Map)}
 *
 * Why is the REST here?
 * =====================
 * Good question.
 * Architecture-wise it would be better to have the REST implementation in each cache (the descendants of {@link diag.server.applications.AbsCache}).
 * But it would mean to do a REST per cache and performance-wise this is something we prefer to avoid.
 * So we have a single REST
 *
 * </pre>
 */
public class AppsInfoCache {
  private final static long INTERVAL = 1;

  private final ScheduledExecutorService      scheduler    = Executors.newScheduledThreadPool(1, new AppInfoThreadFactory());
  private final AppsInfoRefreshTask           refreshTask  = new AppsInfoRefreshTask();
  private final AppKey2AppIdCache             appKey2AppId = new AppKey2AppIdCache();                                         //[#AppKey2AppId#][2]
  private final ProbeAuthenticationTokenCache probeToken   = new ProbeAuthenticationTokenCache();                             //[#ProbeAuthenticationTokenCache][2]

  private static volatile AppsInfoCache     _instance;

  public static AppsInfoCache getInstance() {

    if (_instance == null) {
      synchronized (AppsInfoCache.class) {
        if (_instance == null) {
          _instance = new AppsInfoCache();
        }
      }
    }

    return _instance;
  }

  private AppsInfoCache() {
    initCache();
    startScheduler();
  }

  private void initCache() {
    CacheManager manager = CacheManager.create();

    //[#AppKey2AppId#][3]
    this.appKey2AppId.initCache(manager);
    //[#ProbeToken#][3]
    this.probeToken.initCache(manager);

  }

  private void startScheduler() {
    scheduler.schedule(this.refreshTask, 0, TimeUnit.SECONDS);
    scheduler.scheduleAtFixedRate(this.refreshTask, INTERVAL, INTERVAL, TimeUnit.SECONDS);  //and schedule a reoccurring task
  }

  public void shutdown() {
    scheduler.shutdown();
  }

  //[#AppKey2AppId#][5]
  public long getApplicationId(String applicationKey, int tenantId) {
    return this.appKey2AppId.get(tenantId, applicationKey);
  }

  //=======================================================================
  
  private class AppsInfoRefreshTask implements Runnable {

    @Override
    public void run() {
      try {
        final JsonObject json = restCall2PurpleServer();
        updateAppKey2AppId(json);
        updateProbeToken(json);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    private JsonObject restCall2PurpleServer() {
      final JsonObject json = RestMock.restCall2Purple();
      MyLog.log(MyLog.LogLevel.DEBUG, "restCall2PurpleServer - json: " + json.toString());
      return json;
    }

    //[#AppKey2AppId#][4]
    private void updateAppKey2AppId(JsonObject json) {
      Map<String,Long> applicationsMapping = new HashMap<>();
      JsonArray array = json.getJsonArray("applications");

      if (array==null) {
        MyLog.log(MyLog.LogLevel.WARN, "updateAppKey2AppId - END - Update Nothing! - No [applications] in JSON");
        return;
      }

      for (int i = 0; i < array.size(); i++) {

        int tenantId = array.getJsonObject(i).getInt("tenantId");
        String appKey = array.getJsonObject(i).getString("applicationKey");
        long appId = array.getJsonObject(i).getJsonNumber("appExternalId").longValue();

        applicationsMapping.put(AppKey2AppIdCache.concatenateKey(tenantId, appKey), appId);
      }
      AppsInfoCache.this.appKey2AppId.updateCache(applicationsMapping);

    }

    //[#ProbeToken#][4]
    private void updateProbeToken(JsonObject json) {
      Map<Integer, String> tokenPerTenant = new HashMap<>();
      JsonArray array = json.getJsonArray("probeTokens");

      if (array==null) {
        MyLog.log(MyLog.LogLevel.WARN, "updateProbeToken - END - Update Nothing! - No [probeTokens] in JSON");
        return;
      }

      for (int i = 0; i < array.size(); i++) {

        int tenantId = array.getJsonObject(i).getInt("tenantId");
        String token = array.getJsonObject(i).getString("token");

        tokenPerTenant.put(tenantId, token);
      }
      AppsInfoCache.this.probeToken.updateCache(tokenPerTenant);

    }
  }

  //=======================================================================
  
  private static class AppInfoThreadFactory implements ThreadFactory {
    private static final String        NAME_PREFIX  = "app-info-cache-thread-";
    private        final ThreadGroup   group;
    private        final AtomicInteger threadNumber = new AtomicInteger(1);

    AppInfoThreadFactory() {
      SecurityManager s = System.getSecurityManager();
      group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    }

    @SuppressWarnings("NullableProblems")
    public Thread newThread(Runnable r) {
      Thread t = new Thread(group, r, NAME_PREFIX + threadNumber.getAndIncrement(), 0);
      if (t.isDaemon()) {
        t.setDaemon(false);
      }
      if (t.getPriority() != Thread.NORM_PRIORITY)
        t.setPriority(Thread.NORM_PRIORITY);
      return t;
    }
  }


  //=======================================================================

  public static void main(String[] args) throws InterruptedException {
    int counter = 0;
    while (++counter<12) {
      long jm94gebira_1 = AppsInfoCache.getInstance().getApplicationId("jm94gebira_1", 123456);
      long test11 = AppsInfoCache.getInstance().getApplicationId("test11", 11);
      MyLog.log(MyLog.LogLevel.DEBUG, "jm94gebira_1=["+jm94gebira_1+"] test11=["+test11+"] ");
      Thread.sleep(500);
    }
    AppsInfoCache.getInstance().shutdown();
  }
}
