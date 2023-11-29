package ru.yandex.finalContest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        StringBuilder rev = new StringBuilder(s).reverse();


        HashChecker equalChecker = new HashChecker(s + rev);
        List<Integer> list = new ArrayList<>(n);

        for (int i = 1; i <= n; i++) {
            int maxLength = n - i + 1;
            int from1 = 1;
            int from2 = n + i;
            int ans = 0;
            while (maxLength > 0) {
                if (equalChecker.isEqual(from1, from2, maxLength - maxLength / 2)) {
                    ans += maxLength - maxLength / 2;
                    from1 = from1 + maxLength - maxLength / 2;
                    from2 = from2 + maxLength - maxLength / 2;
                    maxLength = maxLength / 2;
                } else if (maxLength - maxLength / 2 == 1) {
                    break;
                } else {
                    maxLength = maxLength - maxLength / 2;
                }
            }
            list.add(ans);
        }

        for (int i = list.size()-1; i >= 1; i--) {
            pw.print(list.get(i) + " ");
        }
        pw.print(list.get(0));


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

