package network.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    11/03/2015 21:53
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class SimpleSocket {

  public static void main(String[] args) {
    try {

      computing_try();

    } catch (UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private static void computing_try () throws IOException
  {
    String s = readFromComputingDcuIe();
    System.out.println(s);
  }

  public static String readFromComputingDcuIe() throws IOException {
    Socket s = null;
    try
    {
      s = initializeSocket("computing.dcu.ie", "/~humphrys/howtomailme.html", 80);

      BufferedReader br = getBufferedReaderFromSocket(s);

      StringBuilder buf = new StringBuilder();
      String line;
      while ((line = br.readLine()) != null)
      {
        buf.append(line).append("\n");
      }

      return buf.toString();

    }
    finally {
      if (s != null) {
        try {
          s.close();
        } catch (IOException ignored) {
        }
      }
    }


  }

  private static Socket initializeSocket(String host, String file, int port) throws IOException {
    Socket s;

    s = new Socket(host, port);

    OutputStream out = s.getOutputStream();
    PrintWriter outw = new PrintWriter(out, false);
    outw.print("GET " + file + " HTTP/1.0\r\n");
    outw.print("Accept: text/plain, text/html, text/*\r\n");
    outw.print("\r\n");
    outw.flush();
    return s;
  }

  private static BufferedReader getBufferedReaderFromSocket(Socket s) throws IOException {
    InputStream in = s.getInputStream();
    InputStreamReader inr = new InputStreamReader(in);
    return new BufferedReader(inr);
  }

  private static void mytry() throws IOException {InetAddress inetAddress = InetAddress.getByName("localhost");
    Socket s = new Socket(inetAddress, 8080);
    PrintWriter pw = new PrintWriter(s.getOutputStream());
    pw.println("GET / HTTP/1.1");
    pw.println("Host: stackoverflow.com");
    pw.println("");
    pw.flush();
    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    String t;
    int lines = 0;
    while((t = br.readLine()) != null) {
      lines++;
    }
    br.close();

    System.out.println(lines);
  }
}
