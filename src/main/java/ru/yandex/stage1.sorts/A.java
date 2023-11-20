package ru.yandex.stage1.sorts;

import java.io.*;

public class A {
    static Integer[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] tokens = br.readLine().split(" ");
        nums = new Integer[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokens[i]);

        }
        int x = Integer.parseInt(br.readLine());
        int less = partition(0, 0, x);

        pw.println(less);
        pw.println(n - less);

        br.close();
        bw.close();
        pw.close();
    }

    public static int partition(int eq, int gr, int x) {
        for (int i = gr; i < nums.length; i++) {
            if (nums[i] <= x) {
                int temp = nums[i];
                nums[i] = nums[gr];
                if (temp == x) {
                    nums[gr] = temp;
                } else {
                    nums[gr] = nums[eq];
                    nums[eq] = temp;
                    eq++;
                }
                gr++;
            }
        }
        return eq;
    }
}
