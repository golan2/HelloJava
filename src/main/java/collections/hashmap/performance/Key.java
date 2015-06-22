package collections.hashmap.performance;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    20/11/2014 10:13
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class Key implements Comparable<Key>  {
  private final int value;
  Key(int value) {
    this.value = value;
  }
  @Override
  public int compareTo(Key o) {
    return Integer.compare(this.value, o.value);
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Key key = (Key) o;
    return value == key.value;
  }
  @Override
  public int hashCode() {
    return value;
  }

}
