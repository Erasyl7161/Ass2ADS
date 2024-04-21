import java.util.Comparator;
import java.util.Iterator;

public class MyArrayList<T extends Comparable<T>> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 5;
    private Object[] array;
    private int length;
    private int size;

    // Конструктор
    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = DEFAULT_CAPACITY;
        length = 0;
    }

    // Тізімдің еңуірік қалпына келтіру үшін конструктор
    public MyArrayList(int initialCapacity) {

        if (initialCapacity > 0) {
            array = new Object[initialCapacity];
            size = initialCapacity;
            length = 0;
        } else if (initialCapacity == 0) {
            new MyArrayList();
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }

    }

    // Элемент қосу
    @Override
    public void add(T item) {
        if (length >= size - 1) {
            arrayWidening();
        }
        array[length++] = item;
    }

    // Индексі бойынша элементті орнату
    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("out of range");
        }

        array[index] = item;
    }

    // Белгілі индекске элементті қосу
    @Override
    public void add(int index, T item) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("out of range");
        }

        if (length == size) {
            arrayWidening();
        }

        for (int i = length - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = item;
        length++;
    }

    // Бірінші элементті қосу
    @Override
    public void addFirst(T item) {
        if (length >= size - 1) {
            arrayWidening();
        }

        for (int i = length; i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = item;
        length++;
    }

    // Соңғы элементті қосу
    @Override
    public void addLast(T item) {
        add(item);
    }

    // Берілген индекстегі элементті алу
    @Override
    public T get(int index) {
        if (index < 0 || index > array.length - 1) {
            throw new IndexOutOfBoundsException("out of range");
        }
        return (T)array[index];
    }

    // Бірінші элементті алу
    @Override
    public T getFirst() {
        return (T)array[0];
    }

    // Соңғы элементті алу
    @Override
    public T getLast() {
        return (T)array[length - 1];
    }

    // Берілген индекстегі элементті алу және алдын алу
    @Override
    public void remove(int index) {
        if (index < 0 || index > length - 1) {
            throw new IndexOutOfBoundsException("out of range");
        }
        for (int i = index; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        length--;
    }

    // Бірінші элементті алу және алдын алу
    @Override
    public void removeFirst() {
        remove(0);
    }

    // Соңғы элементті алу және алдын алу
    @Override
    public void removeLast() {
        remove(length - 1);
    }

    // Тізімді сұрыптау
    @Override
    public void sort() {
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0 ; j < length - i - 1; j++) {
                if (get(j).compareTo(get(j + 1)) > 0) {
                    Object temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Объекттің индексін табу
    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < length; i++) {
            if (array[i] == object) {
                return i;
            }
        }

        return -1;
    }

    // Объекттің соңғы индексін табу
    @Override
    public int lastIndexOf(Object object) {
        for (int i = length - 1; i >= 0; i--) {
            if (array[i] == object) {
                return i;
            }
        }

        return -1;
    }

    // Берілген объектті тізімде табу
    @Override
    public boolean exists(Object object) {
        for (int i = 0; i < length; i++) {
            if (array[i] == object) {
                return true;
            }
        }

        return false;
    }

    // Массивті объектпен аудару
    @Override
    public Object[] toArray() {
        return array;
    }

    // Массивтің есімін арттыру
    private void arrayWidening() {
        size *= 2;
        Object[] newArray = new Object[size];
        for(int i = 0; i < length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    // Тізімді тазалау
    @Override
    public void clear() {
        array = new Object[DEFAULT_CAPACITY];
        length = 0;
        size = DEFAULT_CAPACITY;
    }

    // Тізім үлгісін алу
    @Override
    public int size() {
        return size;
    }

    public int length() {
        return length;
    }

    // Итераторды қайта пайдалану
    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    public class MyIterator implements Iterator<T> {
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            return currentIndex < length;
        }

        @Override
        public T next() {
            return get(currentIndex++);
        }
    }
}
