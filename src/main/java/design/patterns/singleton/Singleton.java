package design.patterns.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    18/12/2011 10:42:40
 * <B>Since:</B>       BSM 9.1
 * <B>Description:</B>
 * <p/>
 * </pre>
 */
public class Singleton extends SingletonParent{
  private final String id = initId("Singleton");
  private final Map<Integer, String> map = initMap();

  static {
    System.out.println("Singleton - static block");
  }

  private static HashMap<Integer, String> initMap() {
    System.out.println("initMap");
    return new HashMap<Integer, String>();
  }

  public static final Singleton _instance = new Singleton();

  public static Singleton getInstance() {
    return _instance;
  }

  public Singleton() {
    System.out.println("Singleton - CTOR - id=["+ id +"]");
  }

}


