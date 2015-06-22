package diag.server.applications;

import golan.utils.MyLog;
import net.sf.ehcache.CacheManager;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    16/05/2015 22:15
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 * </pre>
 */
public class TestCache {

  private static final int ITEMS_IN_CACHE = 1000000;
  private static final int SEED_APP_KEY = 60504;

  public static void main(String[] args) {
    CacheManager manager = CacheManager.create();
    testAppKey2AppId(manager);
    testStringCache(manager);



  }

  private static void testAppKey2AppId(CacheManager manager) {
    final AppKey2AppIdCache4Test appKey2AppIdCache = new AppKey2AppIdCache4Test();
    appKey2AppIdCache.initCache(manager);

    long prev = System.currentTimeMillis();
    MyLog.log(MyLog.LogLevel.DEBUG, "[0] Begin testAppKey2AppId");
    int counter = 0;
    Map<TenantIdAndAppKey, Long> map = new HashMap<>();
    final Random random = new Random(System.currentTimeMillis());
    for (long appKey = SEED_APP_KEY ; appKey<SEED_APP_KEY+ITEMS_IN_CACHE+1 ; appKey++) {
      map.put(new TenantIdAndAppKey((int)appKey%5, ""+appKey), random.nextLong()%1000);
    }
    MyLog.log(MyLog.LogLevel.DEBUG, "[" + (System.currentTimeMillis()-prev) + "] Map populated");
    prev = System.currentTimeMillis();
    appKey2AppIdCache.updateCache(map);
    MyLog.log(MyLog.LogLevel.DEBUG, "[" + (System.currentTimeMillis() - prev) + "] Cache populated");
    prev = System.currentTimeMillis();
    int hitCount = 0;
    for (long appKey = SEED_APP_KEY ; appKey<SEED_APP_KEY+ITEMS_IN_CACHE+1 ; appKey++) {
      final Long appId = appKey2AppIdCache.get((int) appKey % 5, "" + appKey);
      if (appId!=Long.MIN_VALUE) hitCount++;
    }
    MyLog.log(MyLog.LogLevel.DEBUG, "[" + (System.currentTimeMillis()-prev) + "] Cache read end. hitCount=["+hitCount+"]");
  }

  private static void testStringCache(CacheManager manager) {
    final StringCache stringCache = new StringCache();
    stringCache.initCache(manager);

    long prev = System.currentTimeMillis();
    MyLog.log(MyLog.LogLevel.DEBUG, "[0] Begin testStringCache");
    int counter = 0;
    Map<String, Long> map = new HashMap<>();
    final Random random = new Random(System.currentTimeMillis());
    for (long appKey = SEED_APP_KEY ; appKey<SEED_APP_KEY+ITEMS_IN_CACHE+1 ; appKey++) {
      map.put(((int)appKey%5+"_"+appKey), random.nextLong()%1000);
    }
    MyLog.log(MyLog.LogLevel.DEBUG, "[" + (System.currentTimeMillis()-prev) + "] Map populated");
    prev = System.currentTimeMillis();
    stringCache.updateCache(map);
    MyLog.log(MyLog.LogLevel.DEBUG, "[" + (System.currentTimeMillis() - prev) + "] Cache populated");
    prev = System.currentTimeMillis();
    int hitCount = 0;
    for (long appKey = SEED_APP_KEY ; appKey<SEED_APP_KEY+ITEMS_IN_CACHE+1 ; appKey++) {
      final Long appId = stringCache.get((int) appKey % 5, "" + appKey);
      if (appId!=Long.MIN_VALUE) hitCount++;
    }
    MyLog.log(MyLog.LogLevel.DEBUG, "[" + (System.currentTimeMillis()-prev) + "] Cache read end. hitCount=["+hitCount+"]");
  }

  private static class StringCache extends AbsCache<String, Long> {

    public Long get(Integer tenantId, String applicationKey) {
      return super.get(tenantId+"_"+applicationKey);
    }

    @Override
    protected Long getKeyNotFoundValue() {
      return Long.MIN_VALUE;
    }

    @Override
    protected String getCacheName() {
      return StringCache.class.getSimpleName();
    }

    @Override
    protected boolean shouldUpdateExisting() {
      return true;
    }

  }

  private static class AppKey2AppIdCache4Test extends AbsCache<TenantIdAndAppKey, Long> {

    public Long get(Integer tenantId, String applicationKey) {
      return super.get(new TenantIdAndAppKey(tenantId, applicationKey));
    }

    @Override
    protected Long getKeyNotFoundValue() {
      return Long.MIN_VALUE;
    }

    @Override
    protected String getCacheName() {
      return AppKey2AppIdCache4Test.class.getSimpleName();
    }

    @Override
    protected boolean shouldUpdateExisting() {
      return false;
    }

  }


  private static class TenantIdAndAppKey extends AbstractMap.SimpleImmutableEntry<Integer,String> {

    public TenantIdAndAppKey(Integer tenantId, String applicationKey) {
      super(tenantId, applicationKey);
    }
  }
}
