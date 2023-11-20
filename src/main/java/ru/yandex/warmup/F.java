package ru.yandex.warmup;

import java.io.*;
import java.math.BigInteger;

public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());  //capacity

        int n = Integer.parseInt(br.readLine());  //floors number

        int c = k; //available places

        BigInteger t = BigInteger.ZERO; //timer
        long total = 0L;
        int[] floors = new int[n];
        for (int i = 0; i < n; i++) {
            floors[i] = Integer.parseInt(br.readLine());
            total += floors[i];
        }

        while (total > 0L) {
            BigInteger timeConsumed;
            for (int i = n - 1; i >= 0 && c > 0; i--) {
                if (c == k && floors[i] > 0) {
                    BigInteger times = BigInteger.valueOf((long) Math.ceil((double) floors[i] / k));
                    timeConsumed = BigInteger.valueOf(2L * (i + 1)).multiply(times);
                    c = k - floors[i] % k;
                    total -= floors[i];
                    floors[i] = 0;
                    n = i;
                    t = t.add(timeConsumed);

                } else if (c < k && floors[i] > 0) {
                    int initialOnFloor = floors[i];
                    int diff = floors[i] - c;
                    if (diff > 0) {
                        floors[i] = diff;
                        c = 0;
                        n = i + 1;
                    } else {
                        floors[i] = 0;
                        c = diff * -1;
                        n = i;
                    }
                    total -= initialOnFloor - floors[i];
                }
            }
            c = k;
        }

        pw.println(t);

        br.close();
        pw.close();
    }
}
