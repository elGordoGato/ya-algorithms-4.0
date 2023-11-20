package ru.yandex;

import java.io.*;

public class DraftReader {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");


        bw.write("Hola");
        bw.newLine();
        bw.write("amigos!");

        br.close();
        bw.close();
        pw.close();
    }
}
