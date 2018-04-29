package exceptions.arcane;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    30/09/2015 22:39
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class CatchImmediateThrow {

  //just wanted to make sure that when you catch'n'throw then you still go via finally on your way out.
  //yep. as expected :-)

  public static void main(String[] args) {
    try {
      System.out.println("parseInt");
      int i = Integer.parseInt("1233x");
    } catch (NumberFormatException e) {
      System.out.println("catch'n'throw");
      throw e;
    } finally {
      System.out.println("finally");
    }
  }
}
