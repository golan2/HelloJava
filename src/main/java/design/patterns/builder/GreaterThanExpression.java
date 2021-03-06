package design.patterns.builder;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    29/05/2015 17:13
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public class GreaterThanExpression<T extends Comparable<T>> extends SimpleExpressionExpression<T> {

  protected GreaterThanExpression(T value) { super(value); }

  @Override
  protected String getExpressionName() {
    return ">";
  }

  @Override
  public boolean evaluate(T ci) {
    return this.value.compareTo(ci)<0;
  }
}
