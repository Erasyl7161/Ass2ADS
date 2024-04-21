import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {
    Node<T> head;
    Node<T> tail;
    int size;

    public MyLinkedList() {
        head = tail = null;
    }

    public MyLinkedList(T item) {
        head = tail = new Node<>(item);
        size++;
    }

    @Override
    public void add(T item) {
        Node<T> newNode = new Node<>(item);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
    }

    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("out of range");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = item;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size ) {
            throw new IndexOutOfBoundsException("out of range");
        }

        if (index == 0) {
            addFirst(item);
            return;
        }

        if (index == size) {
            add(item);
            return;
        }

        Node<T> current = head;
        Node<T> newNode = new Node<>(item);
        for (int i = 1; i < index; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        newNode.prev = current;
        current.next = newNode;
        size++;
    }

    @Override
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = head;
        head = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        add(item);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("out of range");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        return head.data;
    }

    @Override
    public T getLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        return tail.data;
    }


    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("out of range");
        }

        if (index == 0) {
            removeFirst();
            return;
        } else if (index == size - 1) {
            removeLast();
            return;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            size--;
        }
    }

    @Override
    public void removeFirst() {
        if (head != null) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            size--;
        }
    }

    @Override
    public void removeLast() {
        if (tail == head) {
            head = tail = null;
        } else {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
        }
        size--;
    }

    @Override
    public void sort() {
        for (int i = 0; i < size - 1; i++) {
            Node<T> current = head;
            for (int j = 0; j < size - 1 - i; j++) {
                if (current.data.compareTo(current.next.data) > 0) {
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }
                current = current.next;
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(object)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        Node<T> current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (current.data.equals(object)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(object)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }
        return array;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {

        Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}
