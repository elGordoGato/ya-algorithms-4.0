package ru.yandex.stage1.sorts;

import java.io.*;
import java.util.Arrays;

public class D1 {

    public static void merge(int[] arr, int left, int mid, int right, int[] buf) {

        if (right + 1 - left >= 0) System.arraycopy(arr, left, buf, left, right + 1 - left);

        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i > mid) {
                arr[k] = buf[j];
                j++;
            } else if (j > right) {
                arr[k] = buf[i];
                i++;
            } else if (buf[i] < buf[j]) {
                arr[k] = buf[i];
                i++;
            } else {
                arr[k] = buf[j];
                j++;
            }
        }
    }

    public static void mergeSort(int[] arr, int left, int right, int[] buf) {

        if (left == right) {
            return;
        }

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid, buf);
        mergeSort(arr, mid + 1, right, buf);

        merge(arr, left, mid, right, buf);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));

        int n = Integer.parseInt(br.readLine());

        String[] tokens = br.readLine().split(" ");
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(tokens[i]);
        }
        int[] buf = new int[array.length];

        //mergeSort(array, 0, array.length - 1, buf);

        Arrays.sort(array);

        for (int num : array) {
            bw.write(num + " ");
        }
        br.close();
        bw.close();
    }
}

