package network.timeout;

import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    04/01/2015 19:40
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class SimpleConnection {

  public static void main(String[] args) throws IOException {
    URL url = new URL("http://stackoverflow.com/questions/3163693/java-urlconnection-timeout");
    URLConnection urlConn = url.openConnection();
    urlConn.setConnectTimeout(15000);
    urlConn.setReadTimeout(15);
    urlConn.setAllowUserInteraction(false);
    urlConn.setDoOutput(true);

    InputStream inStream = urlConn.getInputStream();
    InputSource input = new InputSource(inStream);


    System.out.println(inStream.available());
  }
}
