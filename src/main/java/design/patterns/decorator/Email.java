package design.patterns.decorator;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    27/05/2015 22:46
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public class Email implements IEmail
{
  private String content;

  public Email(String content)
  {
    this.content = content;
  }

  @Override
  public String getContents()
  {
    //general email stuff
    return content;

  }

}