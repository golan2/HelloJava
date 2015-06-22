package diag.server.applications;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    14/05/2015 23:32
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * Mapping from ApplicationKey to ApplicationId
 * Actually it is a mapping from the pair {TenantId,ApplicationKey} to the {ApplicationId}.
 * So the Key for this cache is a String where we concatenate: {TenantId + "_" + ApplicationKey}
 *
 * Implementation Consideration:
 * =============================
 * There were 2 decisions that I took
 * [1] Store all applications of all tenants in the same {@link net.sf.ehcache.Cache} object
 * [2] Use a String as the Key for the Cache
 *
 * Below are the details.
 *
 * Why Using a Single Cache?
 * =========================
 * So now the dilemma to choose between 2 options:
 * [1] Cache per tenant (each cache for mapping AppKey to AppId)
 * [2] Single cache for all tenants
 *
 * I chose option [2] because I didn't want to create a {@link net.sf.ehcache.Cache} for each tenant.
 * I thought that we might end up having many "trial tenants" that might only have one application.
 * It would lead to a situation when i have many {@link net.sf.ehcache.Cache} objects with very few applications in each.
 *
 * Why Using a String as Cache Key?
 * ================================
 * Now that we store all of them in a single {@link net.sf.ehcache.Cache} we need to talk about the Key.
 * (Reminder: ApplicationKey is only unique per tenant not in farm level!)
 * Again 2 options:
 * [1] Concatenate TenantId and ApplicationKey to a string (with "_")
 * [2] Create a special class implementing {@link Object#equals(Object)}
 *
 * So I ran a small performance test and it seems that [1] is better.
 * For the test I used {@link java.util.AbstractMap.SimpleImmutableEntry} as the cache key.
 * It is the closed thing I found in java for Pair and it also gives the {@link Object#equals(Object)} implementation for free.
 *
 * The results for 10K items (values are in ms):
 *
 *                      String   SimpleImmutableEntry
 * Insert to Map        1574     1427
 * Insert to EhCache    2510     3925
 * Read from EhCache    404      163
 *
 * The "Insert" operations are used every time we update the cache so the penalty is not that high since we do it every x minutes.
 * But the "Read from EhCache" costing 3 times more .... this is something that I can't pay :-)
 * We will probably need to read tons of applications while traversing OC and writing to Vertica.
 *
 * [#AppKey2AppId#][1]    (if you wonder what it means go look at the java docs of {@link diag.server.applications.AppsInfoCache}
 * </pre>
 */
/*package*/  class AppKey2AppIdCache extends AbsCache<String, Long> {

  public static String concatenateKey(int tenantId, String applicationKey) { return tenantId + "_" + applicationKey; }

  public Long get(int tenantId, String applicationKey) {
    return super.get(concatenateKey(tenantId, applicationKey));
  }


  @Override
  protected String getCacheName() {
    return AppKey2AppIdCache.class.getSimpleName();
  }

  @Override
  protected boolean shouldUpdateExisting() {
    return true;
  }

  @Override
  protected Long getKeyNotFoundValue() {
    return Long.MIN_VALUE;
  }

}
