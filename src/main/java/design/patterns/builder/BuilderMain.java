package design.patterns.builder;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    29/05/2015 17:18
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public class BuilderMain {
  public static void main(String[] args) {
    final EqualsExpression<Integer>      equals12       = new EqualsExpression<>(12);
    final GreaterThanExpression<Integer> gt13           = new GreaterThanExpression<>(12);
    final GreaterThanExpression<Integer> gt10           = new GreaterThanExpression<>(10);
    final LessThanExpression<Integer>    lt30           = new LessThanExpression<>(30);
    final Expression<Integer>            between10and30 = lt30.and(gt10);

    //System.out.println(equals12.evaluate(13));
    //System.out.println(equals12.evaluate(12));
    //System.out.println(gt13.evaluate(13));

    System.out.println("[13]: " + equals12 + " => " + equals12.evaluate(13));
    System.out.println("[12]: " + between10and30 + " => " + between10and30.evaluate(12));
    System.out.println("[9]:  " + between10and30 + " => " + between10and30.evaluate(9));
    System.out.println("[90]: " + between10and30 + " => " + between10and30.evaluate(90));
  }
}
