package design.patterns.builder;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    29/05/2015 17:27
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public class AndExpression<T> extends CompoundExpression<T> {

  public AndExpression(Expression<T> lhs, Expression<T> rhs) {
    super(lhs, rhs);
  }

  @Override
  protected boolean evaluateExpression(boolean lhs, boolean rhs) {
    return lhs && rhs;
  }

  @Override
  protected String getExpressionName() {
    return "&&";
  }
}
