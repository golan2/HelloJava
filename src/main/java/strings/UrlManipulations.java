package strings;

import com.sun.jndi.toolkit.url.Uri;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    10/08/2015 22:34
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class UrlManipulations {

  public static void main(String[] args) throws MalformedURLException {
    String url = "http://myd-vm14696.hpswlabs.adapps.hp.com:443/topaz/TopazSiteServlet?createSession=true&userlogin=admin&userpassword=admin&requestType=login";
    System.out.println(getRootUrlFromString(url));

    Uri uri1 = new Uri(url);
    System.out.println(uri1.getPath());

    String uri = "/";
    String component = uri.substring(0,uri.indexOf("/",1)+1);
    System.out.println(component);
  }

  private static String getRootUrlFromString(String url) {
    String result = "";
    URL topazURL;

    try {
      if (null != url) {
        topazURL = new URL(url);
        String topazHost = topazURL.getHost();
        String topazProtocol = topazURL.getProtocol();
        int topazPort = topazURL.getPort();
        if (topazPort==80) topazPort = -1;    //avoid writing ":80"  but if it is SSL we need the port
        topazURL = new URL(topazProtocol, topazHost, topazPort, "");
        result = topazURL.toString();
      }
    }
    catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return result;
  }
}
