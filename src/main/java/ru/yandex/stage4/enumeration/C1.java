package ru.yandex.stage4.enumeration;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class C1 {
    static Integer max = 0;
    static int[] maxPos;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] tokens = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(tokens[j]);
            }
        }


        maxPos = new int[n];
        int[] pos = new int[n];
        pos[0] = 2;

        addToTeam(n, matrix,pos, 1, 1, 0);


        pw.println(max);
        for (int maxPo : maxPos) {
            pw.print(maxPo + " ");
        }


        br.close();
        pw.close();
    }

    private static void addToTeam(int n, int[][] matrix, int[] pos, int pointer, int secCounter, int sum) {
        if(pointer == n){
            if (sum > max){
                max = sum;
                maxPos = pos.clone();
            }
            return;
        }
        int localSum = sum;
        if(secCounter+1 < n){
            pos[pointer] = 2;
            for (int i = 1; i < pointer; i++) {
                if (pos[i] == 1 && matrix[pointer][i]>0){
                    localSum+=matrix[pointer][i];
                }
            }
            addToTeam(n, matrix, pos, pointer+1,secCounter+1, localSum);
            pos[pointer] = 0;
        }
        localSum = sum;
        pos[pointer] = 1;
        for (int i = 0; i < pointer; i++) {
            if (pos[i] == 2 && matrix[pointer][i]>0){
                localSum+=matrix[pointer][i];
            }
        }
        addToTeam(n, matrix, pos, pointer+1, secCounter, localSum);
        pos[pointer] = 0;
    }
}
