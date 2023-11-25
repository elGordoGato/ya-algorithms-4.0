package ru.yandex.stage4.enumeration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class C {
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

        int positions = (int) (Math.pow(2, n)) / 2;

        int max = 0;
        String maxPos = "";

        for (int i = 1; i < positions; i++) {
            String pos = String.format("%" + n + "s", Integer.toBinaryString(i)).replace(" ", "0");
            int sum = 0;
            List<Integer> zeroList = new ArrayList<>();
            int vertexO = pos.indexOf("0");
            while (vertexO != -1) {
                zeroList.add(vertexO);
                vertexO = pos.indexOf("0", ++vertexO);
            }
            for (Integer zeroIndex : zeroList) {
                for (int j = 0; j < n; j++) {
                    if (pos.charAt(j) == '1' && matrix[zeroIndex][j] > 0) {
                        sum += matrix[zeroIndex][j];
                    }
                }
            }
            if (sum > max) {
                max = sum;
                maxPos = pos;
            }
        }


        pw.println(max);
        pw.println(maxPos.replaceAll("(?<=.)", " ").replace("1", "2").replace("0", "1"));


        br.close();
        pw.close();
    }
}
