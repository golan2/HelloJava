package design.patterns.builder;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    29/05/2015 18:21
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public class LessThanExpression<T extends Comparable<T>> extends SimpleExpressionExpression<T> {

  protected LessThanExpression(T value) { super(value); }

  @Override
  protected String getExpressionName() {
    return "<";
  }

  @Override
  public boolean evaluate(T ci) {
    return this.value.compareTo(ci)>0;
  }


}
