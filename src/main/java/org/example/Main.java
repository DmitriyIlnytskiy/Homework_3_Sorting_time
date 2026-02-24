package org.example;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Array sizes to test
        int[] sizes = {100, 1000, 2000, 4000, 8000, 16000};
        Random rand = new Random();

        System.out.println("Size\tAlgo\t\tTime(ms)\tComparisons");
        System.out.println("-------------------------------------------------------");

        for (int size : sizes) {
            int[] originalArray = new int[size];
            for (int i = 0; i < size; i++) {
                originalArray[i] = rand.nextInt(100000); // Random numbers
            }

            // --- Bubble Sort ---
            int[] bubbleArray = Arrays.copyOf(originalArray, size);
            long startBubble = System.nanoTime();
            long bubbleComps = bubbleSort(bubbleArray);
            long endBubble = System.nanoTime();
            double bubbleTime = (endBubble - startBubble) / 1_000_000.0;
            System.out.printf("%d\tBubble\t\t%.2f\t\t%d\n", size, bubbleTime, bubbleComps);

            // --- Selection Sort ---
            int[] selectionArray = Arrays.copyOf(originalArray, size);
            long startSelection = System.nanoTime();
            long selectionComps = selectionSort(selectionArray);
            long endSelection = System.nanoTime();
            double selectionTime = (endSelection - startSelection) / 1_000_000.0;
            System.out.printf("%d\tSelection\t%.2f\t\t%d\n", size, selectionTime, selectionComps);

            // --- Insertion Sort ---
            int[] insertionArray = Arrays.copyOf(originalArray, size);
            long startInsertion = System.nanoTime();
            long insertionComps = insertionSort(insertionArray);
            long endInsertion = System.nanoTime();
            double insertionTime = (endInsertion - startInsertion) / 1_000_000.0;
            System.out.printf("%d\tInsertion\t%.2f\t\t%d\n", size, insertionTime, insertionComps);

            System.out.println("-------------------------------------------------------");
        }
    }

    // Returns number of comparisons
    public static long bubbleSort(int[] arr) {
        long comparisons = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return comparisons;
    }

    // Returns number of comparisons
    public static long selectionSort(int[] arr) {
        long comparisons = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        return comparisons;
    }

    // Returns number of comparisons
    public static long insertionSort(int[] arr) {
        long comparisons = 0;
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
        return comparisons;
    }
}