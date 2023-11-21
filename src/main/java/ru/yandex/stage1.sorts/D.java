package ru.yandex.stage1.sorts;

import java.io.*;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        String[] tokens = br.readLine().split(" ");
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(tokens[i]);
        }

        mergeSort(array, 0, n-1, array);
        for (int num : array) {
            pw.print(num + " ");
        }
        br.close();
        bw.close();
        pw.close();
    }

    public static void mergeSort(int[] arr, int left, int right, int[] buf) {
        // Базовый случай: если подмассив состоит из одного элемента, он уже отсортирован
        if (left == right) {
            return;
        }
        // Находим середину подмассива
        int mid = (left + right) / 2;
        // Сортируем левый и правый подмассивы рекурсивно
        mergeSort(arr, left, mid, buf);
        mergeSort(arr, mid + 1, right, buf);
        // Сливаем отсортированные подмассивы в один
        merge(buf, arr, left, mid, mid + 1, right);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = buf[i];
        }
    }

    static int[] merge(int[] buffer, int[] toSort, int left, int leftBorder, int right, int rightBorder){
        int bufferStart = left;
        while (left <= leftBorder && right <= rightBorder){
            buffer[bufferStart++] = toSort[left] <= toSort[right]
                    ? toSort[left++] : toSort[right++];
        }
        while (left <= leftBorder){
            buffer[bufferStart++] = toSort[left++];
        }
        while (right <= rightBorder) {
            buffer[bufferStart++] = toSort[right++];
        }
        return buffer;
    }



    }
