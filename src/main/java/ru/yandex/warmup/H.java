package ru.yandex.warmup;

import java.io.*;

public class H {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int a = Integer.parseInt(br.readLine());
        double b = Double.parseDouble(br.readLine());
        double n = Double.parseDouble(br.readLine());

        int bMin = (int) Math.ceil(b/n);


        pw.println((a>bMin) ? "Yes" : "No");

        br.close();
        pw.close();
    }
}
