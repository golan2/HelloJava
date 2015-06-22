package design.patterns.decorator;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    27/05/2015 23:00
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 * Decorator is a Wrapper with a fancy name and usually with a purpose to change the behavior
 * </pre>
 */
public class DecoratorMain {

  public static void main(String[] args) {
    final Email mail = new Email("Testing mail decorator");
    final EmailWithSignature emailWithSignature = new EmailWithSignature(mail, "Thanks, Izik Golan");
    final SecureEmail secureEmail = new SecureEmail(emailWithSignature);

    System.out.println("mail=["+mail.getContents()+"]");
    System.out.println("emailWithSignature=["+emailWithSignature.getContents()+"]");
    System.out.println("secureEmail=["+secureEmail.getContents()+"]");

  }
}
