
import java.util.NoSuchElementException;

public class MyStack<T extends Comparable<T>> {
    private final MyLinkedList<T> stack;

    public MyStack() {
        stack = new MyLinkedList<>();
    }

    public boolean isEmpty() {
        return stack.size == 0;
    }

    public int size() {
        return stack.size;
    }

    public T peek() {
        return stack.getLast();
    }

    public void push(T item) {
        stack.add(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        T item = stack.get(stack.size - 1);
        stack.removeLast();
        return item;
    }

}
