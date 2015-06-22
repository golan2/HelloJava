package collections.hashmap.performance;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    20/11/2014 10:14
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class Keys {
  public static final int MAX_KEY = 10_000_000;
  private static final Key[] KEYS_CACHE = new Key[MAX_KEY];
  static {
    for (int i = 0; i < MAX_KEY; ++i) {
      KEYS_CACHE[i] = new Key(i);
    }
  }
  public static Key of(int value) {
    return KEYS_CACHE[value];
  }
}