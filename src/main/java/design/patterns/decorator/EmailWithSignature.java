package design.patterns.decorator;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    27/05/2015 22:47
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public class EmailWithSignature extends EmailDecorator
{
  private final String signature;

  public EmailWithSignature(IEmail basicEmail, String signature)
  {
    this.originalEmail = basicEmail;
    this.signature = signature;
  }



  @Override
  public String getContents()
  {
    return addSignature(originalEmail.getContents());
  }


  private String addSignature(String message)
  {
    //append company disclaimer to message
    return  message + " ~ " + getSignature() + " ~ ";
  }

  private String getSignature() {return signature;}

}
