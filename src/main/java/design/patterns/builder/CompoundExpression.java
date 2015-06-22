package design.patterns.builder;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    29/05/2015 17:23
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public abstract class CompoundExpression<T> extends AbsSimpleExpression<T> {
  private final Expression<T> lhs;
  private final Expression<T> rhs;

  public CompoundExpression(Expression<T> lhs, Expression<T> rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  @Override
  public boolean evaluate(T ci) {
    return evaluateExpression(lhs.evaluate(ci), rhs.evaluate(ci));
  }

  protected abstract boolean evaluateExpression(boolean lhs, boolean rhs);

  @Override
  protected String getExpressionAsString() {
    return "("+lhs+")"+getExpressionName()+"("+rhs+")";
  }
}
