package design.patterns.builder;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    29/05/2015 16:45
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public interface Expression<T> {
  public boolean evaluate(T ci);
  public Expression<T> and(Expression<T> rhs);

}
