package interview.trees.bst.iterator;

import java.util.stream.IntStream;

public class TreeIteratorMain {
    public static void main(String[] args) {

        testTree("someTree", someTree());
        testTree("balanced", balanced(100));
        testTree("orderedPath", orderedPath(100));
        testTree("lightningWithLongTail", lightningWithLongTail(100));


        System.out.println("OK");
    }

    private static void testTree(String name, BSTree tree) {
        System.out.println(" ------------------ ");
        System.out.println(name);
        System.out.println("-------");
        final BSTIterator it = new BSTIterator(tree.root);
        int prev = -1;
        while (it.hasNext()) {
            Integer current = it.next();
            if (prev > current) {
                throw new IllegalStateException("[" + name + "] Found " + prev + " before " + current);
            }
        }
    }

    /**
     * http://cs.middlesexcc.edu/~schatz/csc236/handouts/BST.delete.ex.html
     */
    private static BSTree someTree() {
        return new BSTree()
                .add(400)
                .add(200)
                .add(600)
                .add(75)
                .add(350)
                .add(500)
                .add(700)
                .add(15)
                .add(150)
                .add(375)
                .add(450)
                .add(550)
                .add(585)
                .add(800)
                .add(100)
                .add(225)
                .add(366)
                .add(380)
                .add(675)
                .add(750)
                .add(900)
                .add(120)
                .add(378);
    }

    private static BSTree balanced(int size) {
        final BSTree tree = new BSTree();

        int[] arr = new int[size];
        for (int i = 0; i< size; i++) {
            arr[i] = i;
        }

        insert(tree, arr, 0, size -1);

        return tree;
    }

    /**
     * Insery them in the sorted ascending order will end up having a linked list
     */
    private static BSTree orderedPath(int size) {
        final BSTree tree = new BSTree();
        IntStream.range(0, size).forEach(tree::add);
        return tree;
    }

    /**
     * This is a tree with a shape like "lightning" but with a long tail.
     * From the ROOT we have LEFT child and then a RIGHT child and then a long tail of descending numbers
     */
    private static BSTree lightningWithLongTail(int tailLength) {
        final BSTree tree = new BSTree()
                .add(tailLength + 5)
                .add(1)
                .add(tailLength + 3)
                ;
        for (int i = tailLength+1 ; i > 1 ; i--) {
            tree.add(i);
        }
        return tree;
    }

    private static void insert(BSTree tree, int[] arr, int left, int right) {
        if (right-left == 1) {
            tree.add(arr[left]);
            tree.add(arr[right]);
        }
        else if (right-left == 0) {
            tree.add(arr[left]);
        }
        else {
            int mid = left + (right - left) / 2;
            tree.add(arr[mid]);
            insert(tree, arr, left, mid - 1);
            insert(tree, arr, mid + 1, right);
        }
    }
}
