import java.util.NoSuchElementException;

public class MyHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap;

    // Конструктор класса MyHeap
    public MyHeap() {
        heap = new MyArrayList<T>();
    }

    // Получение элемента по индексу
    public T get(int index) {
        return heap.get(index);
    }

    // Проверка на пустоту
    public boolean empty() {
        return heap.length() == 0;
    }

    // Получение размера кучи
    public int size() {
        return heap.length();
    }

    // Извлечение минимального элемента из кучи
    public T extractMin() {
        if (empty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T min = getMin();

        heap.remove(heap.indexOf(min));

        int startIdx = (heap.length() / 2) - 1;

        for (int i = startIdx; i >= 0; i--) {
            heapify(i);
        }

        return min;
    }

    // Получение минимального элемента в куче
    public T getMin() {
        if (empty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T min = heap.get(0);

        for (int i = 0; i < heap.length(); i++) {
            if (min.compareTo(heap.get(i)) > 0) {
                min = heap.get(i);
            }
        }

        return min;
    }

    // Вставка элемента в кучу
    public void insert(T item) {
        heap.add(item);
        traverseUp(heap.indexOf(item));
    }

    // Получение левого потомка элемента кучи
    private T leftChildOf(int index) {
        int leftChildIndex = 2 * index + 1;
        if (leftChildIndex < heap.length()) {
            return heap.get(leftChildIndex);
        } else {
            throw new IndexOutOfBoundsException("No left child for the given index");
        }
    }

    // Получение правого потомка элемента кучи
    private T rightChildOf(int index) {
        int rightChildIndex = 2 * index + 2;
        if (rightChildIndex < heap.length()) {
            return heap.get(rightChildIndex);
        } else {
            throw new IndexOutOfBoundsException("No right child for the given index");
        }
    }

    // Получение индекса родительского элемента
    private int parentOf(int index) {
        if (index <= 0 || index >= heap.length()) {
            throw new IndexOutOfBoundsException("No parent for the given index");
        }

        return (index - 1) / 2;
    }

    // Обмен значениями двух элементов кучи
    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    // Просеивание вниз
    private void heapify(int index) {
        int smallest = index;
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;

        if (leftChildIndex < heap.length() && heap.get(leftChildIndex).compareTo(heap.get(smallest)) < 0) {
            smallest = leftChildIndex;
        }

        if (rightChildIndex < heap.length() && heap.get(rightChildIndex).compareTo(heap.get(smallest)) < 0) {
            smallest = rightChildIndex;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    // Просеивание вверх
    private void traverseUp(int index) {
        int parentIndex = parentOf(index);

        while (index > 0 && heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = parentOf(index);
        }
    }
}
