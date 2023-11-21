package ru.yandex.stage2.stringhash;

import java.io.*;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");




        br.close();
        pw.close();
    }
}
