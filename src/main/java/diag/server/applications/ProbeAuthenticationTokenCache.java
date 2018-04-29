package diag.server.applications;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    17/05/2015 08:36
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 *
 * [#ProbeToken#][1]    (if you wonder what it means go look at the java docs of {@link diag.server.applications.AppsInfoCache}
 * </pre>
 */
/*package*/ class ProbeAuthenticationTokenCache extends AbsCache<Integer, String> {
  @Override
  protected String getCacheName() {
    return ProbeAuthenticationTokenCache.class.getSimpleName();
  }

  @Override
  protected boolean shouldUpdateExisting() {
    return true;
  }

  @Override
  protected String getKeyNotFoundValue() {
    return "NOT_FOUNT";
  }
}
