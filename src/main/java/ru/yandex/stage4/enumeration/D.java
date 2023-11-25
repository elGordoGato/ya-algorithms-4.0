package ru.yandex.stage4.enumeration;

import java.util.Arrays;
import java.util.Scanner;

public class D {
    // константа для бесконечности
    public static final int INF = 1000000000;
    // максимальное число вершин
    public static final int MAXN = 10;

    // число вершин
    public static int n;
    // матрица смежности
    public static int[][] w = new int[MAXN][MAXN];
    // массив для хранения значений d(S, v)
    public static int[][] d = new int[1 << MAXN][MAXN];

    public static void main(String[] args) {
        // создаём объект для считывания ввода
        Scanner sc = new Scanner(System.in);
        // считываем число вершин
        n = sc.nextInt();
        // считываем матрицу смежности
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                w[i][j] = sc.nextInt();
            }
        }
        // инициализируем массив d
        for (int mask = 0; mask < (1 << n); mask++) {
            Arrays.fill(d[mask], INF);
        }
        d[1][0] = 0; // база рекурсии
        // перебираем все подмножества вершин
        for (int mask = 1; mask < (1 << n); mask++) {
            // перебираем все вершины, которые могут быть конечными
            for (int v = 0; v < n; v++) {
                // если вершина v принадлежит множеству mask
                if ((mask & (1 << v)) != 0) {
                    // перебираем все вершины, которые могут быть предыдущими
                    for (int u = 0; u < n; u++) {
                        // если вершина u принадлежит множеству mask и отлична от v
                        if ((mask & (1 << u)) != 0 && u != v) {
                            // обновляем значение d(mask, v)
                            d[mask][v] = Math.min(d[mask][v], d[mask ^ (1 << v)][u] + w[u][v]);
                        }
                    }
                }
            }
        }
        // ищем минимальное значение суммарной длины цикла
        int ans = INF;
        for (int v = 0; v < n; v++) {
            ans = Math.min(ans, d[(1 << n) - 1][v] + w[v][0]);
        }
        // выводим ответ или -1, если цикла не существует
        if (ans == INF) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }
}

