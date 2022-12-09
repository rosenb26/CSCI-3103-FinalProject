
public class MinHeap<E extends Comparable<E>> extends Heap<E> {

    public int compare(E first, E second) {
        return first.compareTo(second);
    }

    public static void minHeapTester() {
        Heap<String> heap = new MinHeap<>();
        String[] temp = {"hello", "world", "didn't we", "all", "love", "Data Structures I", "so", "very", "much?"};
        System.out.print("\nAdding: ");
        for (int i = 0; i < temp.length; i++) {
            heap.add(temp[i]);
            System.out.print(temp[i] + " ");
        }
        System.out.println("\nmin heap after adding: " + heap.getHeap() + "\n");

        System.out.print("Removing: ");
        while (heap.getSize() > 0) {
            System.out.print(heap.remove() + " ");
        }
        System.out.println("\nmin heap after removing: " + heap.getHeap() + "\n");
    }
}
