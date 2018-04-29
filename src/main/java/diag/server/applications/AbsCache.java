package diag.server.applications;

import golan.utils.MyLog;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

import java.io.Serializable;
import java.util.Map;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    14/05/2015 23:01
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * This is the generic implementation for a cache in {@link diag.server.applications.AppsInfoCache}
 * Most of the caching work is done for you by this abs class.
 *
 * You should choose Key and Value wisely
 * For {@link Key} consideration read the docs for {@link diag.server.applications.AppKey2AppIdCache}
 * For {@link Value} consideration read the docs for {@link #updateCache(java.util.Map)} and {@link #updateCache(java.util.Map, boolean)}
 *
 * </pre>
 */
/*package*/ abstract class AbsCache<Key extends Serializable, Value extends Serializable> {

  private Cache cache = null;

  public void initCache(CacheManager manager) {
    validateCacheName(manager);

    this.cache = new Cache(
      new CacheConfiguration()
        .name(getCacheName())
        .maxEntriesLocalHeap(getMaxEntriesLocalHeap())
        .timeToIdleSeconds(getTimeToIdleSeconds())
    );

    manager.addCache(this.cache);
    this.cache = manager.getCache(getCacheName());
  }

  private void validateCacheName(CacheManager manager) {
    if (manager.cacheExists(getCacheName())) {
      //you probably have 2 classes implementing this AbsCache and both return the same value of getCacheName. Copy paste issue? ;-)
      throw new IllegalStateException("A cache with the name ["+getCacheName()+"] already exists");
    }
  }


  public Value get(Key key) {
    final Element element = this.cache.get(key);
    if (element == null) {
      return getKeyNotFoundValue();
    } else {
      //noinspection unchecked
      return (Value) element.getObjectValue();
    }
  }

  /**
   * <pre>
   * Call this method in order to update the cache.
   * Pass in the full map or the delta; we don't delete anyway.
   * But we do update though (see performance alert below)
   *
   * Why we don't delete?
   * =====================
   * Because the EhCache configuration supports aging mechanism.
   * You can override {@link #getTimeToIdleSeconds()}) if you want to set the time for that.
   *
   * Performance Alert:
   * ==================
   * We do perform updates.
   * We use {@link Value#equals(Object)} in order to know if update is needed or not.
   * If your {@link Value} uses the "default" equals (from {@link java.lang.Object#equals(Object)}) you should consider using {@link #updateCache(java.util.Map, boolean)} or expect redundant cache updates
   * </pre>
   *
   * @param currentMapping a map with KeyValue pairs for updating the cache
   * @return number of add/updates
   */
  public int updateCache(Map<Key,Value> currentMapping) {
    int updateCount = 0;
    int addCount = 0;
    for (Key key : currentMapping.keySet()) {
      final Value value = currentMapping.get(key);
      final Element element = this.cache.get(key);

      if (element==null) {
        addCount++;
      }
      if ( shouldAddOrUpdate(element, value) ) {
        this.cache.put( new Element(key,value) );
        updateCount++;
      }
    }
    MyLog.log(MyLog.LogLevel.DEBUG, "updateCache - END - cacheName=["+getCacheName()+"] addCount=[" + addCount + "] updateCount=[" + updateCount + "] itemsInCache=[" + this.cache.getSize() + "]");
    return updateCount;
  }

  private boolean shouldAddOrUpdate(Element element, Value newValue) {
    if (element==null) return true;

    final Object objectValue = element.getObjectValue();
    if (objectValue==null) return (newValue!=null);   //if the value in cache is null then we need to update only if newValue has some value to replace it (not null)

    //so the key exists, do you want to update?
    if (!shouldUpdateExisting()) {
      return false;
    }
    else {
      Value cacheValue = (Value) objectValue;
      return !cacheValue.equals(newValue);
    }
  }

  /**
   * Each cache should have a different name in the {@link net.sf.ehcache.CacheManager}
   * @see net.sf.ehcache.config.CacheConfiguration#name(String)
   * @return the unique string for this cache
   */
  protected abstract String getCacheName();

  /**
   * You must decide whether items in your cache support update or not.
   * If they do support update you must implement equals method on {@link Value} and not use the default one from {@link java.lang.Object#equals(Object)}
   *
   * @return true or false -)
   */
  protected abstract boolean shouldUpdateExisting();

  /**
   * What value would you like to return if someone calls {@link #get(java.io.Serializable)} but there is not such Key in the cache?
   * @return a {@link Value} that will be returned when no such key exists in cache
   */
  protected abstract Value getKeyNotFoundValue();

  /**
   * This is part of the ehcache configuration {@link net.sf.ehcache.config.CacheConfiguration}
   * @see net.sf.ehcache.config.CacheConfiguration#maxEntriesLocalHeap(int)
   * @return the maximum number of cache entries in local heap memory.
   */
  protected int getMaxEntriesLocalHeap() {
    return 10000;
  }

  /**
   * This is part of the ehcache configuration {@link net.sf.ehcache.config.CacheConfiguration}
   * @see net.sf.ehcache.config.CacheConfiguration#timeToIdleSeconds(long)
   * @return The maximum number of seconds an element can exist in the cache without being accessed. The element expires at this limit and will no longer be returned from the cache.
   */
  protected long getTimeToIdleSeconds() {
    return 60*60*24;
  }

  public int size() {
    return this.cache.getSize();
  }
}
