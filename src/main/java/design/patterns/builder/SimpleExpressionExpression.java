package design.patterns.builder;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
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
