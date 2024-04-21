import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Расскомментируйте секцию, которую вы хотите выполнить

//        testArrayList();
//        testLinkedList();
//        testStack();
//        testQueue();
        testHeap();
    }

    public static void testArrayList() {
        MyArrayList<Integer> list = new MyArrayList<>();

        list.add(2);
        list.add(3);
        list.sort();

        Object[] intArr = list.toArray();
        printArray(intArr);

        System.out.println(list + " " + list.size() + " " + list.length());
    }

    public static void testLinkedList() {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        list.sort();

        list.add(0, 10);
        System.out.println(list.getLast());

        Object[] listArr = list.toArray();
        printArray(listArr);

        System.out.println(list.exists(4));
    }

    public static void testStack() {
        MyStack<String> stack = new MyStack<>();

        stack.push("first");
        stack.push("second");
        stack.push("third");
        stack.push("fourth");
        stack.push("fifth");
        stack.push("sixth");

        System.out.println(stack.peek());
        printStack(stack);
    }

    public static void testQueue() {
        MyQueue<String> queue = new MyQueue<>();

        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.size());
    }

    public static void testHeap() {
        MyHeap<Integer> heap = new MyHeap<>();

        heap.insert(10);
        heap.insert(9);
        heap.insert(8);
        heap.insert(7);
        heap.insert(6);
        heap.insert(5);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);

        heap.extractMin();

        for (int i = 0; i < heap.size(); i++) {
            System.out.print(heap.get(i) + " ");
        }
    }

    public static void printArray(Object[] arr) {
        for (Object item : arr) {
            System.out.println(item);
        }
    }

    public static <T> void printStack(MyStack<?> stack) {
        while (!stack.isEmpty()) {
            Object item = stack.pop();
            System.out.println(item);
        }
    }
}
