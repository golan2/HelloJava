package interview.trees;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    10/01/2015 21:40
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class IsBalancedTree {

  public static void main(String[] args) {
    System.out.println("constructNonBalancedTree");
    System.out.println(constructNonBalancedTree().isBalanced());

    System.out.println("=============================");
    System.out.println("=============================");
    System.out.println("=============================");

    System.out.println("constructBalancedTree");
    System.out.println(constructBalancedTree().isBalanced());
  }

  private static IsBalancedBST constructBalancedTree() {
    IsBalancedBST tree = new IsBalancedBST();

    tree.put(5,1);
    tree.put(2,1);
    tree.put(1,1);
    tree.put(4,1);
    tree.put(3,1);
    tree.put(6,1);
    tree.put(7,1);

    return tree;
  }

  private static IsBalancedBST constructNonBalancedTree() {
    IsBalancedBST tree = new IsBalancedBST();

    tree.put(20,1);
    tree.put(25,1);
    tree.put(23,1);
    tree.put(28,1);
    tree.put(10,1);
    tree.put(5,1);
    tree.put(2,1);
    tree.put(7,1);
    tree.put(6,1);
    tree.put(11,1);
    tree.put(15,1);
    tree.put(14,1);

    return tree;
  }

}
