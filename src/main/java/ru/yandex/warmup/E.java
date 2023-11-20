package ru.yandex.warmup;

import java.io.*;
import java.util.Arrays;

public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] rates = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] result = new int[n];

        int lastRate = 0;
        for (int i = 0; i < n-1; i++) {
            lastRate += rates[n-1] - rates[i];
        }
        result[n-1] = lastRate;

        for (int i = n - 2; i >= 0; i--) {
            int diff = rates[i+1] - rates[i];
            result[i] = result[i + 1] - diff*(i+1) + diff*(n-1-i);
        }



        pw.println(String.join(" ", Arrays.stream(result).mapToObj(String::valueOf) .toArray(String[]::new)));

        br.close();
        pw.close();
    }
}
