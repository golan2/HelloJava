package interview.lists;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    03/01/2012 00:51:36
 * <B>Since:</B>       BSM 9.1
 * <B>Description:</B>
 * <p/>
 * </pre>
 */
public class Main {

  public static void main(String[] args) {
    DoublyLinkedList myList = new DoublyLinkedList<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });

    System.out.println(myList);
    System.out.println("================");

  }


  private static void listContainsList() {

    DoublyLinkedList a = new DoublyLinkedList<>(new Integer[] {1,2,1,2,1,2,3,6});
    DoublyLinkedList b = new DoublyLinkedList<>(new Integer[] {1,2,1,2,3});
    System.out.println("a="+a);
    System.out.println("b="+b);
    System.out.println(a.contains(b));

    a.reverse();
    b.reverse();
    System.out.println("a="+a);
    System.out.println("b="+b);
    System.out.println(a.contains(b));
  }
}
