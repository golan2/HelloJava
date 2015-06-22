package design.patterns.builder;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    29/05/2015 17:10
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public abstract class SimpleExpressionExpression<T> extends AbsSimpleExpression<T> {
  protected final T value;

  protected SimpleExpressionExpression(T value) {this.value = value;}

  @Override
  protected String getExpressionAsString() {
    return getExpressionName()+this.value;
  }

}
