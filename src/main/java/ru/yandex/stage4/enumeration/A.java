package ru.yandex.stage4.enumeration;

import java.io.*;

public class A {

    // Метод для обмена двух элементов массива
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Метод для генерации следующей перестановки в лексикографическом порядке
    public static boolean nextPermutation(int[] array) {
        // Ищем первый элемент справа, который меньше своего соседа слева
        int j = array.length - 2;
        while (j >= 0 && array[j] >= array[j + 1]) {
            j--;
        }
        // Если такого элемента нет, то это последняя перестановка
        if (j < 0) {
            return false;
        }
        // Ищем первый элемент справа, который больше элемента j
        int k = array.length - 1;
        while (array[j] >= array[k]) {
            k--;
        }
        // Меняем местами элементы j и k
        swap(array, j, k);
        // Обращаем порядок элементов справа от j
        int l = j + 1, r = array.length - 1;
        while (l < r) {
            swap(array, l, r);
            l++;
            r--;
        }
        // Возвращаем true, так как перестановка существует
        return true;
    }

    // Метод для вывода перестановки
    public static void printPermutation(int[] array, PrintWriter pw) {
        // Выводим номер перестановки
        count++;
        // Выводим элементы перестановки
        for (int i = 0; i < array.length; i++) {
            pw.print(array[i]);
        }
        pw.println();
    }

    // Переменная для хранения номера перестановки
    public static int count = 1;

    public static void main(String[] args) throws IOException {
        // Создаем объект класса Scanner для считывания ввода
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        // Считываем число n
        int n = Integer.parseInt(br.readLine());
        // Создаем массив для хранения текущей перестановки
        int[] permutation = new int[n];
        // Заполняем массив числами от 1 до n
        for (int i = 0; i < n; i++) {
            permutation[i] = i + 1;
        }
        // Выводим первую перестановку
        printPermutation(permutation, pw);
        // Пока есть следующая перестановка
        while (nextPermutation(permutation)) {
            // Выводим следующую перестановку
            printPermutation(permutation, pw);
        }

        pw.close();
        br.close();
    }
}
