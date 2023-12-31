package ru.yandex.stage2.stringhash;

import java.io.*;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();

        EqualChecker equalChecker = new EqualChecker(s);

        int times = Integer.parseInt(br.readLine());

        for (int i = 0; i < times; i++) {
            String[] tokens = br.readLine().split(" ");
            int slen = Integer.parseInt(tokens[0]);
            int from1 = Integer.parseInt(tokens[1]) + 1;
            int from2 = Integer.parseInt(tokens[2]) + 1;
            pw.println(equalChecker.isEqual(from1, from2, slen) ? "yes" : "no");
        }

        br.close();
        pw.close();
    }

    static class EqualChecker {
        int n;
        long p;
        int x_;

        long[] h;
        long[] x;

        public EqualChecker(String s) {
            n = s.length();
            p = (long) (Math.pow(10, 9) + 7);
            x_ = 257;

            h = new long[n + 1];
            x = new long[n + 1];
            x[0] = 1L;
            s = " " + s;
            for (int i = 1; i < n + 1; i++) {
                h[i] = (h[i - 1] * x_ + (int) s.charAt(i)) % p;
                x[i] = (x[i - 1] * x_) % p;
            }
        }

        boolean isEqual(int from1, int from2, int slen) {
            return (
                    (h[from1 + slen - 1] + h[from2 - 1] * x[slen]) % p
                            == (h[from2 + slen - 1] + h[from1 - 1] * x[slen]) % p
            );
        }
    }


}
