package interview.Whattim;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    20/11/2014 16:03
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class ConstructorPolymorphism {

  public static void main(String[] args) {
    SubClass parentClass = new SubClass();
  }


  private static abstract class ParentClass {
    int i = 4;
    public ParentClass(){
      print();
    }

    public void print(){
      System.out.println("im from the parent "+i);
    }
  }

  private static class SubClass extends ParentClass {
    int i = 5;
    public void print() {
      System.out.println("im from the Sub "+i);
    }
  }



}
