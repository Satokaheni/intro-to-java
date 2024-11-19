package sorting;

public class Sorting {
    public T[] insertionSort(T[] array) {
        for(int i = 1; i < array.length; i++) {
            T element = (T)array[i];
            int j = i;
            while (j > 0 && array[j-1].compareTo(element) < 0) {
                array[j] = array[j-1];
                array[j-1] = element;
                j -= 1;
            }
        }

        return array;
    }

    public T[] bubbleSort(T[] array) {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for(int i = 1; i < array.length; i++) {
                if(array[i-1].compareTo(array[i]) < 0) {
                    swapped = true;
                    T temp = array[i];
                    array[i] = array[i-1];
                    array[i-1] = temp;
                }
            }
        }

        return array;
    }

    private T getPivot(int low, int middle, int high, T[] array) {
        if((array[low].comapreTo(array[middle]) < 0 && array[low].compareTo(array[high]) > 0) || (array[low].comapreTo(array[middle]) > 0 && array[low].compareTo(array[high]) < 0)) {
            return low;
        }
        else if ((array[middle].compareTo(array[low]) < 0 && array[middle].compareTo(array[high]) > 0) || (array[middle].compareTo(array[low]) > 0 && array[middle].compareTo(array[high]) < 0)) {
            return middle;
        }
        else {
            return high;
        }
    }

    public T[] quickSort(T[] array) {
        return quickSortHelper(array, 0, array.length-1);
    }

    private T[] quickSortHelper(T[] array, int low, int high) {
        int middle = low + (high-low)/2

        int pivot = getPivot(low, middle, high, array);

        // split into two parts and move pivot
        /*
         * 
         * 
         *  Your Code Here
         * 
         * 
         */

        while(array[pivot] < array[pivot-1] && pivot > low) {
            T temp = array[pivot];
            array[pivot] = array[pivot-1];
            array[pivot-1] = temp;
            pivot--;
        }

        return array;
    }

    private T[] elementSwap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;

        return array;
    }

    private T[] merge(T[] array, T[] left, T[] right) {
        int counter_left = 0;
        int counter_right = 0;

        int index = 0;
        while(counter_left < left.length && counter_right < right.length) {
            if (left[counter_left] < right[counter_left]) {
                array[index] = left[counter_left];
                counter_left++;
            }
            else{
                array[index] = right[counter_right];
                counter_right++;
            }
            index++;
        }

        while(counter_left < left.length) {
            array[index] = left[counter_left];
            counter_left++;
            index++;
        }

        while(counter_right > right.length) {
            array[index] = right[counter_right];
            counter_right++;
            index++;
        }

        return array;
    }

    public T[] mergeSort(T[] array) {
        if(array.length == 1) {
            return array;
        }
        else if(array.length == 2) {
            if(array[0] > array[1]) {
                elementSwap(array, 0, 1);
            }
            return array;
        }
        else {
            int middle = (array.length)/2 - 1;

            T[] left = new T[middle];
            T[] right = new T[array.length-middle-1];

            for(int i = 0; i < array.length; i++) {
                if (i < middle)
                    left[i] = array[i];
                else 
                    right[i-middle] = array[i];
            }

            left = mergeSort(left);
            right = mergeSort(right);

            return merge(array, left, right);
        }
    }
}
