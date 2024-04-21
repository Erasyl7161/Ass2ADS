
public class Nope<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    public Node(T item) {
        this.data = item;
    }

    public Node() {
        data = null;
        next = null;
        prev = null;
    }

}
