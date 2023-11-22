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

        int counter = 1;
        for (int i = 2; i < n; i++) {
            int maxLen = Math.min(i, n - i);
            counter += checkForPalindrome(equalChecker, n, i, i, maxLen) - i;
            maxLen = Math.min(i - 1, n - i);
            counter += checkForPalindrome(equalChecker, n, i - 1, i, maxLen + 1) - i;
        }
        pw.print(counter);

        br.close();
        pw.close();
    }

    private static int checkForPalindrome(HashChecker equalChecker, int n, int left, int right, int maxLen) {
        if (maxLen <= 1) {
            return right;
        }
        int checkedRange = maxLen - maxLen / 2;
        if (equalChecker.isEqual(right, 2*n-left-1, checkedRange)) {
            right = checkForPalindrome(equalChecker, n,
                    left - maxLen / 2,
                    right + checkedRange,
                    maxLen / 2);
        } else {
            right = checkForPalindrome(equalChecker, n,
                    left,
                    right,
                    maxLen / 2);
        }
        return right;
    }

    public static int binarySearch(char c, String s) {
        // Проверяем, что строка не пустая
        if (s == null || s.isEmpty()) {
            return -1;
        }
        // Определяем границы поиска
        int left = 0; // Левая граница
        int right = s.length() - 1; // Правая граница
        // Пока левая граница не превысит правую
        while (left <= right) {
            // Находим середину отрезка
            int mid = (left + right) / 2;
            // Сравниваем символ в середине с искомым
            if (s.charAt(mid) == c) {
                // Если совпал, то возвращаем индекс
                return mid;
            } else if (s.charAt(mid) < c) {
                // Если меньше, то сдвигаем левую границу за середину
                left = mid + 1;
            } else {
                // Если больше, то сдвигаем правую границу перед серединой
                right = mid - 1;
            }
        }
        // Если не нашли, то возвращаем -1
        return -1;
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
