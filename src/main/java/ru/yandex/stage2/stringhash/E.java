package ru.yandex.stage2.stringhash;

import java.io.*;

public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int n = s.length() + 1;
        StringBuilder sb = new StringBuilder(s).append(" ").reverse().append(s);

        HashChecker equalChecker = new HashChecker(sb.toString());

        int maxLen = 1;
        int counter = n - 1;

        if (n > 2) {
            counter = 2;
            counter += checkForPalindrome(equalChecker, n, 1, 2, maxLen) - 2;
        }

        for (int i = 2; i < n - 1; i++) {
            maxLen = Math.min(i - 1, n - i - 1);
            counter += checkForPalindrome(equalChecker, n, i - 1, i + 1, maxLen) - i;
            maxLen = Math.min(i, n - i - 1);
            counter += checkForPalindrome(equalChecker, n, i, i + 1, maxLen) - i - 1;
        }

        pw.print(counter);

        br.close();
        pw.close();
    }

    private static int checkForPalindrome(HashChecker equalChecker, int n, int left, int right, int maxLen) {
        if (maxLen == 0) {
            return right;
        }

        int checkedRange = maxLen - maxLen / 2;

        if (equalChecker.isEqual(right, 2 * n - left - 1, checkedRange)) {

            right = checkForPalindrome(equalChecker, n,
                    left - checkedRange,
                    right + checkedRange,
                    maxLen / 2);

        } else if (checkedRange == 1) {
            return right;
        } else {

            right = checkForPalindrome(equalChecker, n,
                    left,
                    right,
                    checkedRange);
        }
        return right;
    }


    static class HashChecker {
        int n;
        long p;
        int x_;

        long[] h;
        long[] x;

        public HashChecker(String s) {
            n = s.length() - 1;
            p = (long) (Math.pow(10, 9) + 7);
            x_ = 257;

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
