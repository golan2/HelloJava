package design.patterns.builder;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    29/05/2015 17:29
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public abstract class AbsSimpleExpression<T> implements Expression<T> {
  @Override
  public Expression<T> and(Expression<T> rhs) {
    return new AndExpression<T>(this, rhs);
  }

  @Override
  public final String toString() {
    return "{"+ getExpressionAsString()+"}";
  }

  protected abstract String getExpressionAsString();

  protected abstract String getExpressionName();
}
