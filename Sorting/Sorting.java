import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author EASHAN SINHA
 * @version 1.0
 * @userid esinha6
 * @GTID 903598987
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */

public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("The passed in array or comparator parameters are null");
        }
        for (int i = 1; i < arr.length; i++) {
            T key = arr[i];
            int j = i - 1;
            while ((j >= 0) && (comparator.compare(arr[j], key) > 0)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("The passed in array or comparator parameters are null");
        }
        boolean swapsMade = true;
        int startInd = 0;
        int endInd = arr.length - 1;
        int lastSwappedEnd = 0;
        int lastSwappedStart = 0;
        while (swapsMade) {
            swapsMade = false;
            lastSwappedEnd = endInd;
            for (int i = startInd; i < endInd; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapsMade = true;
                    lastSwappedEnd = i;
                }
            }
            endInd = lastSwappedEnd;
            if (swapsMade) {
                swapsMade = false;
                lastSwappedStart = startInd;
                for (int j = endInd; j > startInd; j--) {
                    if (comparator.compare(arr[j - 1], arr[j]) > 0) {
                        T temp = arr[j - 1];
                        arr[j - 1] = arr[j];
                        arr[j] = temp;
                        swapsMade = true;
                        lastSwappedStart = j;
                    }
                }
                startInd = lastSwappedStart;
            }
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("The passed in array or comparator parameters are null");
        }
        if (arr.length > 1) {
            int length = arr.length;
            int midIndex = length / 2;
            T[] left = (T[]) new Object[midIndex];
            T[] right = (T[]) new Object[length - midIndex];
            /**
            int i = 0;
            int j = midIndex;
            while (i < left.length) {
                left[i] = arr[i + j];
                i++;
            }
            while (j < length) {
                right[j] = arr[j + i];
                j++;
            }
            */
            for (int i = 0; i < midIndex; i++) {
                left[i] = arr[i];
            }
            for (int j = midIndex; j < length; j++) {
                right[j - midIndex] = arr[j];
            }
            mergeSort(left, comparator);
            mergeSort(right, comparator);
            merge(left, right, arr, comparator);
        }
    }

    /**
     * This method is a helper method for mergeSort
     *
     * @param left the left array
     * @param right the right array
     * @param arr the array to be partitioned
     * @param comparator the Comparator used to compare the data in arr
     * @param <T> the data type of the elements
     */
    private static <T> void merge(T[] left, T[] right, T[] arr, Comparator<T> comparator) {
        int i = 0;
        int j = 0;
        while (i + j < arr.length) {
            if (j >= right.length || (i < left.length && comparator.compare(left[i], right[j]) <= 0)) {
                arr[i + j] = left[i++];
            } else {
                arr[i + j] = right[j++];
            }
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("The passed in array is null");
        }
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        int largestMagNum = 0;
        for (int value : arr) {
            if (Math.abs(value) > largestMagNum) {
                largestMagNum = Math.abs(value);
            }
            if (value == Integer.MIN_VALUE) {
                largestMagNum = Integer.MIN_VALUE;
                break;
            }
        }
        int count = 0;
        while (largestMagNum != 0) {
            largestMagNum = largestMagNum / 10;
            count++;
        }
        int divisor = 1;
        for (int i = 0; i < count; i++) {
            for (int number : arr) {
                /**
                if (buckets[(number / divisor) % 10 + 9] != null) {
                    buckets[(number / divisor) % 10 + 9] = new LinkedList<>();
                }
                */
                buckets[(number / divisor) % 10 + 9].add(number);
            }
            int idx = 0;
            for (LinkedList<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[idx++] = bucket.remove();
                }
                /**
                if (bucket != null) {
                    for (int value : bucket) {
                        arr[idx++] = value;
                    }
                    bucket.clear();
                }
                */
            }
            divisor *= 10;
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not receive
     * credit if you do not use the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws java.lang.IllegalArgumentException if the array or comparator or
     *                                            rand is null
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("The passed in array, comparator, or rand parameters are null");
        }
        quickSortHelper(arr, 0, arr.length - 1, comparator, rand);
    }

    /**
     * This method is a helper method for quickSort
     *
     * @param arr the array to be sorted
     * @param start the starting index
     * @param end the last index
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param <T> data type to sort
     */
    private static <T> void quickSortHelper(T[] arr, int start, int end, Comparator<T> comparator, Random rand) {
        if ((end - start) < 1) {
            return;
        } else {
            int pivotIndex = rand.nextInt(end - start + 1) + start;
            //T pivotVal = arr[pivotIndex];
            swap(arr, start, pivotIndex);
            int i = start + 1;
            int j = end;
            while (i <= j) {
                while (i <= j && comparator.compare(arr[i], arr[start]) <= 0) {
                    i++;
                }
                while (i <= j && comparator.compare(arr[j], arr[start]) >= 0) {
                    j--;
                }
                if (i <= j) {
                    swap(arr, i, j);
                    i++;
                    j--;
                }
            }
            swap(arr, start, j);
            quickSortHelper(arr, start, j - 1, comparator, rand);
            quickSortHelper(arr, j + 1, end, comparator, rand);
        }
    }

    /**
     * This is a helper method for swapping elements in an array
     *
     * @param arr the array being passed in
     * @param firstIndex the first element to be swapped
     * @param secondIndex the second element to be swapped with the first element
     * @param <T> the data type elements
     */
    private static <T> void swap(T[] arr, int firstIndex, int secondIndex) {
        T tempValue = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = tempValue;
    }
}