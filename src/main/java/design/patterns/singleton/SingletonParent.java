package design.patterns.singleton;

import java.util.UUID;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    18/12/2011 10:55:54
 * <B>Since:</B>       BSM 9.1
 * <B>Description:</B>
 * <p/>
 * </pre>
 */
public class SingletonParent {
  private final String parentId = initId("SingletonParent");

  static {
    System.out.println("SingletonParent - static block");
  }

  public SingletonParent() {
    System.out.println("SingletonParent - CTOR - id=["+ parentId +"]");
  }



  /*package*/ static String initId(String className) {
    System.out.println("initId (class:"+className+")");
    return UUID.randomUUID().toString();
  }
}
