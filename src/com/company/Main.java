package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random random = new Random();

    // Generates a random number between -1000 to 1000
    public static int randomNumberGenerator() {
        int min = -1000;
        int max = 1000;
        int range = (max - min) + 1;
        int randomNumber = random.nextInt(range) + min;
        return randomNumber;
    }

    // Generates a random key from an array of numbers
    public static int randomKeyGenerator(int[] a) {
        int key = a[random.nextInt(a.length)];
        return key;
    }

    // Sorts the array in ascending order
    public static void sort_array(int[] a) {
        Arrays.sort(a);
    }

    // Populates the array with random numbers upto the size n
    public static int[] populateArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomNumberGenerator();
        }
        return a;
    }

    // Finds a key from the array a using Linear Search
    public static boolean linear_search(int[] a, int key) {
        for (int i = 0; i < a.length; i++) {
            if (key == a[i]) {
                return true;
            }
        }
        return false;
    }

    // Finds a key from the array a using Binary Search
    public static boolean binary_search(int[] a, int key) {
        int left = 0;
        int right = a.length - 1;
        int middle = (left + right) / 2;

        while (right > left || key == a[middle]) {
            if (key == a[middle]) {
                return true;
            } else if (key < a[middle]) {
                right = middle - 1;
                middle = (left + right) / 2;
            } else {
                left = middle + 1;
                middle = (left + right) / 2;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a Positive Number: ");
        int n = input.nextInt();
        System.out.print("\n");
        // Populate array
        int[] a = populateArray(n);
        // Sort array
        sort_array(a);
        int key;

        long start_time;
        long end_time;
        long totalAverageTime;

        /*
            Part A - In this part we will calculate average-case running time of each function
         */

        // Average time for Linear Search Algorithm
        start_time = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            key = randomKeyGenerator(a);
            linear_search(a, key);
        }
        end_time = System.nanoTime();
        totalAverageTime = (end_time - start_time) / 100;
        System.out.println("Average Running Time for Linear Search: " + totalAverageTime + "ns");

        // Average time for Binary Search Algorithm
        start_time = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            key = randomKeyGenerator(a);
            binary_search(a, key);
        }
        end_time = System.nanoTime();
        totalAverageTime = (end_time - start_time) / 100;
        System.out.println("Average Running Time for Binary Search: " + totalAverageTime + "ns\n");

        /*
            Part B - In this part we will calculate the worst-case running times of each function
         */

        System.out.print("Enter a Positive Number: ");
        n = input.nextInt();
        System.out.print("\n");
        // Populate array
        a = populateArray(n);
        // Sort array
        sort_array(a);

        double total_time_linear;
        double total_time_binary;
        key = 5000;

        // Worst-case running time for Linear Search
        start_time = System.nanoTime();
        linear_search(a, key);
        end_time = System.nanoTime();
        total_time_linear = end_time - start_time;
        System.out.println("Worst Case Running Time for Linear Search: " + total_time_linear + "ns");

        // Worst-case running time for Binary Search
        start_time = System.nanoTime();
        binary_search(a, key);
        end_time = System.nanoTime();
        total_time_binary = end_time - start_time;
        System.out.print("Worst Case Running Time for Binary Search: " + total_time_binary + "ns\n\n");

        // Time machine takes to run one single line using Binary Search function
        int lines_in_BST = 4;
        double singleLineTimeBinary = (total_time_binary / (lines_in_BST * Math.log10(n)));
        System.out.print("Time machine takes to run one single line using Binary Search: " + singleLineTimeBinary + "ns\n");
        System.out.print("Worst-case running time for Binary Search for n = 10^9: " + (singleLineTimeBinary * Math.pow(10, 9)) + "ns\n\n");

        int lines_in_LST = 3;
        double singleLineTimeLinear = (total_time_linear / (lines_in_LST * n));
        System.out.print("Time machine takes to run one single line using Linear Search: " + singleLineTimeLinear + "ns\n");
        System.out.print("Worst-case running time for Linear Search for n = 10^9: " + (singleLineTimeLinear * Math.pow(10, 9)) + "ns");
    }
}

