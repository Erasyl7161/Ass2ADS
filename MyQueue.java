import java.util.NoSuchElementException;

public class MyQueue<T extends Comparable<T>> {
    private final MyLinkedList<T> queue;

    public MyQueue() {
        queue = new MyLinkedList<>();
    }

    public boolean empty() {
        return queue.size == 0;
    }

    public int size() {
        return queue.size();
    }

    public T peek() {
        return queue.getFirst();
    }

    public void enqueue(T item) {
        queue.add(item);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        T item = queue.head.data;
        queue.removeFirst();
        return item;
    }

    public boolean isEmpty() {
        return queue.size == 0;
    }

}