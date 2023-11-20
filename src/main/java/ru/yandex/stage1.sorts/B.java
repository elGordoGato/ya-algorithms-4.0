package ru.yandex.stage1.sorts;

import java.io.*;
import java.util.Random;

public class B {
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] tokens = br.readLine().split(" ");

        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokens[i]);
        }


        quickSort(0, n);
        for (int num : nums) {
            pw.print(num + " ");
        }

        br.close();
        pw.close();

    }

    // A method to partition the array around a pivot element
    public static int[] partition(int low, int high, int x) {
        int eq = low, gr = low;
        for (int i = low; i < high; i++) {
            if (nums[i] <= x) {
                int temp = nums[i];
                nums[i] = nums[gr];
                if (temp == x) {
                    nums[gr] = temp;
                } else {
                    nums[gr] = nums[eq];
                    nums[eq] = temp;
                    eq++;
                }
                gr++;
            }
        }
        return new int[]{eq, gr};
    }

    // A method to sort the array using quick sort algorithm
    public static void quickSort(int low, int high) {
        while (high - low > 15) {
            Random rand = new Random(); // create a Random object
            int x = nums[low + rand.nextInt(high - low + 1)];
            int[] pi = partition(low, high, x);
            if (pi[0] - low >= high - pi[1]) {
                quickSort(pi[1], high);
                high = pi[0];
            } else {
                quickSort(low, pi[0]);
                low = pi[1];
            }
        }
        for (int i = low + 1; i < high; i++) {
            int value = nums[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (nums[j] <= value){
                    break;
                }
                nums[j+1] = nums[j];
            }
            nums[j+1] = value;
        }
    }
}
