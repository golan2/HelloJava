package interview.lists;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    02/01/2015 08:27
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public interface MyList<T> {
  void addFirst(T value);

  boolean hasCycle();

  boolean contains(MyList<T> rhs);

  void addLast(T value);

  void checkIntegrity();

  boolean isEmpty();

  boolean remove(T value);

  void reverse();

  void recursiveReverse();
}
