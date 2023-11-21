package ru.yandex.stage1.sorts;

import java.io.*;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        String[] tokens = br.readLine().split(" ");
        int m = Integer.parseInt(br.readLine());

        int[] nums = new int[n + m];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokens[i]);
        }

        tokens = br.readLine().split(" ");
        for (int i = n; i < n+m; i++) {
            nums[i] = Integer.parseInt(tokens[i-n]);
        }
        int[] merged = new int[n + m];
        merge(merged, nums, 0, n - 1, n, n + m - 1);
        for (int num : merged) {
            pw.print(num + " ");
        }

        br.close();
        pw.close();
    }

    static int[] merge(int[] buffer, int[] toSort, int left, int leftBorder, int right, int rightBorder){
        int bufferStart = left;
        while (left <= leftBorder && right <= rightBorder){
            buffer[bufferStart++] = toSort[left] <= toSort[right]
                    ? toSort[left++] : toSort[right++];
        }
        while (left <= leftBorder){
            buffer[bufferStart++] = toSort[left++];
        }
        while (right <= rightBorder) {
            buffer[bufferStart++] = toSort[right++];
        }
        return buffer;
    }
}
