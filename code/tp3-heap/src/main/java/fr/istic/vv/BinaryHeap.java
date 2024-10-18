package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

class BinaryHeap<T> {

    private final ArrayList<T> heap;
    private final Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Le tas est vide.");
        }
        T minElement = heap.get(0);
        T lastElement = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }
        return minElement;
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Le tas est vide.");
        }
        return heap.get(0);
    }

    public void push(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    public int count() {
        return heap.size();
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        if (index > 0 && comparator.compare(heap.get(index), heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            heapifyUp(parentIndex);
        }
    }

    private void heapifyDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallest = index;

        if (leftChildIndex < heap.size() && comparator.compare(heap.get(leftChildIndex), heap.get(smallest)) < 0) {
            smallest = leftChildIndex;
        }

        if (rightChildIndex < heap.size() && comparator.compare(heap.get(rightChildIndex), heap.get(smallest)) < 0) {
            smallest = rightChildIndex;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }
}
