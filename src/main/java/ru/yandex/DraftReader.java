package ru.yandex;

import java.io.*;

public class DraftReader {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");




        br.close();
        pw.close();
    }
}
