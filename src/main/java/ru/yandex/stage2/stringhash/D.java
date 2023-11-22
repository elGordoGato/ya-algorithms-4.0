package ru.yandex.stage2.stringhash;

import java.io.*;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        StringBuilder sb = new StringBuilder(br.readLine().replace(" ", ""));

        sb.append(sb.reverse());
        String s = sb.toString();
        System.out.println(s);


        HashChecker equalChecker = new HashChecker(s, m);


        for (int i = n/2; i > 0; i--) {
            if (equalChecker.isEqual(1, 2*n-2*i+1, i)){
                pw.print(n-i + " ");
            }
        }
        pw.print(n);


        br.close();
        pw.close();
    }

    static class HashChecker {
        int n;
        long p;
        int x_;

        long[] h;
        long[] x;

        public HashChecker(String s, int m) {
            n = s.length()-1;
            p = (long) (Math.pow(10, 9) + 7);
            x_ = m+1;

            h = new long[n + 1];
            x = new long[n + 1];
            x[0] = 1L;
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

