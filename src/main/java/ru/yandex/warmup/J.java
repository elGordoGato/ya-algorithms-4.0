package ru.yandex.warmup;

import java.io.*;

public class J {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String[] tokens = br.readLine().split(" ");
            long n = Long.parseLong(tokens[0]);
            long a = Long.parseLong(tokens[1]);
            long b = Long.parseLong(tokens[2]);

            long groups = n / a;
            long rest = n%a;
            long possible = (b - a) * groups;
            String ans = possible >= rest ? "YES" : "NO";

//            BufferedReader ar = new BufferedReader(new FileReader("ans.txt"));
//            pw.print(ans.equals(ar.readLine()) ? "" : n + " " + a + " " + b + " !!!  " );


            pw.println(ans);
        }




        pw.println();

        br.close();
        pw.close();
    }
}
