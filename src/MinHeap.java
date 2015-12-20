/**
 * Your implementation of a min heap.
 * @author YOUR NAME HERE
 * @version 1.0
 */
public class MinHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables

    /**
     * Creates a Heap.
     */
    public MinHeap() {
        size = 0;
        backingArray = (T[]) (new Comparable[STARTING_SIZE]);
        //instantiate backing Array here or in add......

    }
    /**
     * Resizes the array to double the current size and copies over info
     */
    private void resize() {
        T[] newBackingArray = (T[]) (new Comparable[backingArray.length * 2]);
        for (int i = 0; i < backingArray.length; i++) {
            newBackingArray[i] = backingArray[i];
        }
        this.backingArray = newBackingArray;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if ((size + 1) >= backingArray.length) {
            resize();
        }
        size++;
        backingArray[size] = item;
        int i = size;
        while (i > 1 && (backingArray[i].compareTo(backingArray[i / 2]) < 0)) {
            swap(i, (i / 2));
            i = i / 2;
        }



    }



    @Override
    public T remove() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The heap is empty");
        }
        T data = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        heapify(1);
        size--;
        return data;

    }

    /**
     * Swaps the values at give indices of the backing array
     * @param i1 the first index
     * @param i2 the second index
     */
    private void swap(int i1, int i2) {
        T old = backingArray[i1];
        backingArray[i1] = backingArray[i2];
        backingArray[i2] = old;

    }

    /**
     * Checks that he heap property is being maintained and fixes it if not
     * @param i the index that is to be heapified or checked
     */

    private void heapify(int i) {
        if (!(2 * i + 1 > backingArray.length - 1)) {
            if (backingArray[2 * i] != null && backingArray[2 * i + 1]
                    != null) {
                if ((backingArray[i]).compareTo(backingArray[2 * i]) > 0
                        && (backingArray[i].compareTo(
                                backingArray[2 * i + 1])) > 0) {
                    if ((backingArray[2 * i]).compareTo(
                            backingArray[2 * i + 1]) < 0) {
                        swap(i, 2 * i);
                        heapify(2 * i);
                    } else {
                        swap(i, 2 * i + 1);
                        heapify(2 * i + 1);
                    }

                } else if ((backingArray[i]).compareTo(
                        backingArray[2 * i]) > 0) {
                    swap(i, 2 * i);
                    heapify(2 * i);
                } else {
                    swap(i, 2 * i + 1);
                    heapify(2 * i + 1);
                }

            } else if (backingArray[2 * i] != null) {
                if ((backingArray[i].compareTo(
                        backingArray[2 * i])) > 0) {
                    swap(i, 2 * i);
                    heapify(2 * i);
                }
            } else if (backingArray[2 * i + 1] != null) {
                if ((backingArray[i].compareTo(
                        backingArray[2 * i + 1])) > 0) {
                    swap(i, 2 * i + 1);
                    heapify(2 * i + 1);
                }
            }

        }


    }

    @Override
    public boolean isEmpty() {
        return (size == 0);

    }

    @Override
    public int size() {
        return this.size;

    }

    @Override
    public void clear() {
        this.backingArray = (T[]) (new Comparable[STARTING_SIZE]);
        size = 0;

    }

    /**
     * Used for grading purposes only. Do not use or edit.
     * @return the backing array
     */
    public Comparable[] getBackingArray() {
        return backingArray;
    }

}
