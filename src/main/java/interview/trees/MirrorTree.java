package interview.trees;

public class MirrorTree {

    public static void main(String[] args) {

        final Node n7 = new Node(7);
        final Node n8 = new Node(8);

        final Node n6 = new Node(6, n7, n8);
        final Node n5 = new Node(5);
        final Node n4 = new Node(4);

        final Node n3 = new Node(3, null, n6);
        final Node n2 = new Node(2, n4, n5);

        final Node n1 = new Node(1, n2, n3);

        System.out.println(printTree(n1));
        flip(n1);
        System.out.println(printTree(n1));

    }

    private static void flip(Node root) {
        if (root==null) {
            return;
        }

        Node t = root.left;
        root.left = root.right;
        root.right = t;

        flip(root.left);
        flip(root.right);
    }

    private static class Node {
        String val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = ""+val;
        }

        public Node(int val, Node left, Node right) {
            this(val);
            this.left = left;
            this.right = right;
        }
    }

    private static String printTree(Node root) {
        StringBuilder res = new StringBuilder();

        toJsonString(root, res);

        return res.toString();
    }

    private static void toJsonString(Node node, StringBuilder buf) {
        if (node==null) {
            buf.append("null");
            return;
        }

        buf.append("{\"Node\":{\"key\": ").append(node.val).append(" , \"left\" : ");
        toJsonString(node.left, buf);
        buf.append(" , \"right\" : ");
        toJsonString(node.right, buf);
        buf.append(" }}");
    }
}
