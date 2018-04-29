package classloader;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    19/12/2011 10:53:00
 * <B>Since:</B>       BSM 9.1
 * <B>Description:</B>
 * <p/>
 * </pre>
 */
public class MyClassLoader extends ClassLoader {

  public MyClassLoader() {
    super();
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    System.out.println("loadClass: " + name);
    return super.loadClass(name);
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    System.out.println("findClass: " + name);
    return super.findClass(name);
  }
}
