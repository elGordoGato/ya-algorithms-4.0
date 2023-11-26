package ru.yandex.stage4.enumeration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class D {
    // константа для бесконечност

    // число вершин
    public static int n;
    // матрица смежности

    public static void main(String[] args) throws IOException {
        // создаём объект для считывания ввода
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        // считываем число вершин
        n = Integer.parseInt(br.readLine());
        String[] tokens;

        int minWay = Integer.MAX_VALUE;

        int[][] matrix = new int[n][n];

        Set<Integer> notVisited = new HashSet<>(n);
        // считываем матрицу смежности
        for (int i = 0; i < n; i++) {
            notVisited.add(i);
            tokens = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int way = Integer.parseInt(tokens[j]);
                matrix[i][j] = way;
                minWay = Math.min(way, minWay);
            }
        }

        int maxSum = Integer.MAX_VALUE;

        maxSum = checkCycle(0, 0, matrix, notVisited, maxSum, minWay);
        if(n <= 1){
            System.out.println(0);
        } else if (maxSum == Integer.MAX_VALUE){
            System.out.println(-1);
        } else {
            System.out.println(maxSum);
        }
        br.close();
    }

    private static int checkCycle(int current, int sum, int[][] matrix, Set<Integer> notVisited, int maxSum, int minWay) {
        if (notVisited.size()==1 && matrix[current][0] > 0){
            maxSum = Math.min(sum+matrix[current][0], maxSum);
            return maxSum;
        }
        for (int i = 1; i < n; i++) {
            if(matrix[current][i]>0 && notVisited.contains(i)) {
                if(sum + matrix[current][i] + minWay*notVisited.size() > maxSum){
                    continue;
                }
                notVisited.remove(i);
                maxSum = checkCycle(i, sum + matrix[current][i], matrix, notVisited, maxSum, minWay);
                notVisited.add(i);
            }

        }
        return maxSum;

    }
}

