package interview.leetcode.bst.iterator;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + v(left) +
                ", right=" + v(right) +
                ", next=" + v(next) +
                '}';
    }

    private String v(TreeNode n) {
        if (n == null) return "null";
        return n.val + "";
    }
}
