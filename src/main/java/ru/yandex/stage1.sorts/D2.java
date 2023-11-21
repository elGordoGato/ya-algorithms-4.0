package ru.yandex.stage1.sorts;

import java.io.*;
import java.util.Arrays;

public class D2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        String[] tokens = br.readLine().split(" ");
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(tokens[i]);
        }

        array = mergeSort(array);
        for (int num : array) {
            pw.print(num + " ");
        }
        br.close();
        pw.close();
    }


    private static int[] mergeSort(int[] array) {
        if(array.length <= 1) { // базовый случай рекурсии
            return array;
        }

        // заводим массив для результата сортировки
        int[] ret = new int[array.length];

        // запускаем сортировку рекурсивно на левой половине
        int[] left = mergeSort(Arrays.copyOfRange(array, 0, array.length / 2));

        // запускаем сортировку рекурсивно на правой половине
        int[] right = mergeSort(Arrays.copyOfRange(array, array.length / 2, array.length));

        int leftIdx = 0;
        int rightIdx = 0;
        int retIdx= 0;

        // сливаем результаты
        while (leftIdx < left.length && rightIdx < right.length){
        /* Выбираем, из какого массива забрать минимальный элемент,
        тем самым сортируем значения в итоговом массиве. */
            if(left[leftIdx] <= right[rightIdx]){
                ret[retIdx] = left[leftIdx];
                leftIdx++;
            } else {
                ret[retIdx] = right[rightIdx];
                rightIdx++;
            }
            retIdx++;
        }

    /* Если один массив закончился раньше, чем второй, то
    переносим оставшиеся элементы второго массива в результирующий,
    иначе просто не заходим в цикл. */
        while (leftIdx < left.length){
            ret[retIdx] = left[leftIdx];
            leftIdx++;
            retIdx++;
        }
        // то же для правой части
        while (rightIdx < right.length){
            ret[retIdx] = right[rightIdx];
            rightIdx++;
            retIdx++;
        }

        return ret;
    }
}
