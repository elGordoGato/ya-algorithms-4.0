package ru.yandex.stage2.stringhash;

import java.util.Scanner;

// Класс для решения задачи о кубиках в зеркале
public class CubesInMirror {

    // Метод для определения, является ли строка палиндромом
    public static boolean isPalindrome(int[] arr, int left, int right) {
        // Пока левый и правый указатели не сойдутся
        while (left < right) {
            // Если элементы не равны, строка не палиндром
            if (arr[left] != arr[right]) {
                return false;
            }
            // Сдвигаем указатели ближе к центру
            left++;
            right--;
        }
        // Если цикл завершился, строка палиндром
        return true;
    }

    // Метод для нахождения всех возможных количеств кубиков у Пети
    public static void findCubes(int[] arr, int n) {
        // Создаем массив для хранения результатов
        int[] res = new int[n];
        // Инициализируем счетчик результатов
        int count = 0;
        // Перебираем все возможные позиции зеркала
        for (int i = 0; i < n; i++) {
            // Если строка слева от зеркала палиндром
            if (isPalindrome(arr, 0, i)) {
                // Добавляем в результат количество кубиков, равное длине строки справа от зеркала
                res[count] = n - i - 1;
                count++;
            }
        }
        // Выводим количество результатов
        System.out.println(count);
        // Выводим результаты в порядке возрастания
        for (int i = 0; i < count; i++) {
            System.out.print(res[i] + " ");
        }
        // Переходим на новую строку
        System.out.println();
    }

    // Главный метод
    public static void main(String[] args) {
        // Создаем объект Scanner для считывания ввода
        Scanner sc = new Scanner(System.in);
        // Считываем число N и M
        int n = sc.nextInt();
        int m = sc.nextInt();
        // Создаем массив для хранения цветов кубиков
        int[] arr = new int[n];
        // Считываем цвета кубиков
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Находим все возможные количества кубиков у Пети
        findCubes(arr, n);
    }
}
