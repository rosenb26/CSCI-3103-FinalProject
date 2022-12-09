
public class Node<E> {

    private E data;
    private Node<E> left;
    private Node<E> right;

    public Node(E data) {
        this.data = data;
    }

    public void setLeft(Node<E> node) {
        this.left = node;
    }

    public void setRight(Node<E> node) {
        this.right = node;
    }

    public Node<E> getLeft() {
        return this.left;
    }

    public Node<E> getRight() {
        return this.right;
    }

    public E getContents() {
        return this.data;
    }
}
