package ru.yandex.stage4.enumeration;

import java.util.Scanner;

public class B {

    // Метод для проверки, можно ли поставить динозавра в клетку (row, col)
    public static boolean isSafe(int row, int col, int[][] board) {
        // Проверяем, есть ли динозавры в той же строке
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }
        // Проверяем, есть ли динозавры в левой верхней диагонали
        int i = row;
        int j = col;
        while (i >= 0 && j >= 0) {
            if (board[i][j] == 1) {
                return false;
            }
            i--;
            j--;
        }
        // Проверяем, есть ли динозавры в левой нижней диагонали
        i = row;
        j = col;
        while (i < N && j >= 0) {
            if (board[i][j] == 1) {
                return false;
            }
            i++;
            j--;
        }
        // Если все проверки пройдены, то можно поставить динозавра
        return true;
    }

    // Метод для решения задачи рекурсивно
    public static void solve(int col, int[][] board) {
        // Если мы дошли до последнего столбца, то мы нашли решение
        if (col == N) {
            count++;
            return;
        }
        // Перебираем все строки в текущем столбце
        for (int i = 0; i < N; i++) {
            // Проверяем, можно ли поставить динозавра в клетку (i, col)
            if (isSafe(i, col, board)) {
                // Ставим динозавра в клетку (i, col)
                board[i][col] = 1;
                // Рекурсивно решаем задачу для следующего столбца
                solve(col + 1, board);
                // Убираем динозавра из клетки (i, col)
                board[i][col] = 0;
            }
        }
    }

    // Переменная для хранения размера решетки
    public static int N;
    // Переменная для хранения количества решений
    public static int count;

    public static void main(String[] args) {
        // Создаем объект класса Scanner для считывания ввода
        Scanner scanner = new Scanner(System.in);
        // Считываем число N
        N = scanner.nextInt();
        // Создаем пустую решетку N x N
        int[][] board = new int[N][N];
        // Обнуляем счетчик решений
        count = 0;
        // Запускаем решение задачи с первого столбца
        solve(0, board);
        // Выводим количество решений
        System.out.println(count);
    }
}


