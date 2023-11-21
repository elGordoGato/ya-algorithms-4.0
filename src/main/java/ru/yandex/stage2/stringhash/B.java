package ru.yandex.stage2.stringhash;

import java.io.*;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();

        HashChecker equalChecker = new HashChecker(s);

        int n = s.length();

        for (int i = 1; i <= n; i++) {
            if(equalChecker.isEqual(1, 1+i, n-i)){
                pw.print(i);
                break;
            }
        }


        br.close();
        pw.close();
    }

    static class HashChecker {
        int n;
        long p;
        int x_;

        long[] h;
        long[] x;

        public HashChecker(String s) {
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
