package ru.yandex.warmup;

import java.io.*;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");

        int m = Integer.parseInt(tokens[0]);
        int n = Integer.parseInt(tokens[1]);
        int a = Integer.parseInt(tokens[2]);
        int b = Integer.parseInt(tokens[3]);

        int d = n * b;
        int c = (m * b) + (a * n);
        int div = gcd(c, d);
        c /= div;
        d /= div;

        pw.println(c + " " + d);

// Close the input and output streams
        br.close();
        pw.close();
    }

    static int gcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }
}
