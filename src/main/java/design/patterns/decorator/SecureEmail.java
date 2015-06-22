package design.patterns.decorator;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    27/05/2015 22:53
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
//concrete decorator
public class SecureEmail extends EmailDecorator
{
  private String content;

  public SecureEmail(IEmail basicEmail)
  {
    originalEmail = basicEmail;
  }

  @Override
  public String getContents()
  {
    //  secure original (add ## and reverse string. best encryption ever !!! :-)
    try {
      content = new StringBuilder(originalEmail.getContents()).append("##").reverse().toString();
    } catch (Exception e) {
      content = null;
      e.printStackTrace();
    }
    return content;
  }

}
