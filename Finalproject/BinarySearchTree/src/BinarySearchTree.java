
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class BinarySearchTree<E extends Comparable<E>> {

    private Node<E> root;
    private int size;

    /**
     * Adds an item to its proper place in the binary search tree by going left
     * (if less than the current node) or right (if greater than the current
     * node) until the first null reference is encountered, at which point the
     * item is inserted into the tree.
     *
     * @param item The item to be inserted.
     */
    public void add(E item) {
        Node<E> newNode = new Node<>(item);
        if (this.root == null) {
            this.root = newNode;
        }
        else {
            Node<E> temp = this.root;
            while (true) {
                /* Item should go in temp's left subtree. */
                if (item.compareTo(temp.getContents()) < 0) {
                    /* If temp has a left subtree. */
                    if (temp.getLeft() != null) {
                        temp = temp.getLeft();
                    }
                    /* Otherwise insert the item as the left child of temp. */
                    else {
                        temp.setLeft(newNode);
                        break;
                    }

                }
                /* Item should go in temp's right subtree. */
                else if (item.compareTo(temp.getContents()) > 0) {
                    /* If temp has a right subtree. */
                    if (temp.getRight() != null) {
                        temp = temp.getRight();
                    }
                    /* Otherwise insert the item as the right child of temp. */
                    else {
                        temp.setRight(newNode);
                        break;
                    }
                }
                /* If item is already present in the tree, exit the method 
                because a tree doesn't store duplicate values. */
                else {
                    return;
                }
            }

        }
        /* Increment the size of the tree if an item was inserted. */
        this.size++;

    }

    /**
     * The public method for a breadth-first-search.
     *
     * @param target The item to be found.
     * @return True if the tree contains the target, and false otherwise.
     */
    public boolean bfs(E target) {
        /* Search the tree starting with the tree's root. */
        return this.bfs(this.root, target);
    }

    /**
     * Implements a breadth-first-search. If the current node's contents do not
     * match the target, the root's nonnull children are placed in a queue. Thus
     * the tree is traversed layer by layer, from top to bottom, and left to
     * right in each layer.
     *
     * @param root The starting node for the search.
     * @param target The item to be found.
     * @return True if target is present in the tree, and false otherwise.
     */
    private boolean bfs(Node<E> root, E target) {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<E> temp = queue.remove();
            if (temp.getContents().equals(target)) {
                return true;
            }
            if (temp.getLeft() != null) {
                queue.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                queue.add(temp.getRight());
            }
        }
        return false;
    }

    /**
     * A wrapper class for the method that searches for the occurrence of an
     * item using a depth-first search.
     *
     * @param target The item sought.
     * @return True if the item is present in the tree, and false otherwise.
     */
    public boolean dfs(E target) {
        return this.dfs(this.root, target);
    }

    /**
     * Searches for the occurrence of an item in the tree using a depth-first
     * search, i.e. by visiting the given local root, then the root's left
     * subtree, followed by the root's right subtree.
     *
     * @param target The item being sought.
     * @return True if the item is present in the tree, and false otherwise.
     */
    private boolean dfs(Node<E> root, E target) {
        if (root == null) {
            return false;
        }
        if (root.getContents().equals(target)) {
            return true;
        }
        if (root.getLeft() != null) {
            if (this.dfs(root.getLeft(), target)) {
                return true;
            }
        }
        if (root.getRight() != null) {
            if (this.dfs(root.getRight(), target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * A wrapper method for the overloaded method that prints the contents of
     * the tree using a depth-first traversal.
     */
    public void dfs() {
        this.dfs(this.root);
    }

    /**
     * Prints the contents of the tree using a depth-first traversal, i.e. print
     * the contents of the current node (if not null), print the contents of the
     * left subtree (if not null), print the contents of the right subtree (if
     * not null).
     */
    private void dfs(Node<E> root) {
        if (root != null) {
            System.out.println(root.getContents());

            if (root.getLeft() != null) {
                this.dfs(root.getLeft());
            }
            if (root.getRight() != null) {
                this.dfs(root.getRight());
            }
        }
    }

    /**
     * Prints the contents of the tree using a breadth-first traversal, i.e.
     * from the top level to the bottom level, from left to right in each level.
     */
    public void bfs() {
        Queue<Node<E>> queue = new LinkedList<>();
        if (this.root != null) {
            queue.add(this.root);
        }
        while (!queue.isEmpty()) {
            Node<E> temp = queue.remove();
            System.out.println(temp.getContents());
            if (temp.getLeft() != null) {
                queue.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                queue.add(temp.getRight());
            }
        }
    }

    public void clear() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public void runTester() {
        BinarySearchTree tree = new BinarySearchTree();
        System.out.print("Adding the following to the tree: [");
        for (int i = 0; i < 10; i++) {
            int next = new Random().nextInt(100);
            System.out.print(next + " ");
            tree.add(next);
        }
        System.out.println("]");
        System.out.println("\nPrinting using BFS: ");
        tree.bfs();
        System.out.println("\nPrinting using DFS: ");
        tree.dfs();
        System.out.println("\nChecking the contains method using BFS: ");
        for (int i = 0; i < 100; i++) {
            if (tree.bfs(i)) {
                System.out.println(i + ": true");
            }
        }
        System.out.println("\nChecking the contains method using DFS: ");
        for (int i = 0; i < 100; i++) {
            if (tree.dfs(i)) {
                System.out.println(i + ": true");
            }
        }
    }

}
