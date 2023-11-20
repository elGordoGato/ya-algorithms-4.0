package ru.yandex.warmup;

import java.io.*;

public class A {
    public static void main(String[] args) throws IOException {
        // Read input from standard input or input.txt
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        // Write output to standard output or output.txt
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        // Parse the first line to get N and M
        String[] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);
        // Parse the second line to get the sequence
        int[] a = new int[N];
        tokens = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(tokens[i]);
        }

        // Use a segment tree to store the minimum and any other element on each segment
        // Process each query
        for (int i = 0; i < M; i++) {
            // Parse the query to get L and R
            tokens = br.readLine().split(" ");
            int L = Integer.parseInt(tokens[0]);
            int R = Integer.parseInt(tokens[1]);
            // Query the segment tree to get the answer
            int ans = -1;
            for (int j = L; j <= R-1; j++) {
                if(a[j] != a[j+1]){
                    ans = Math.max(a[j], a[j+1]);
                    break;
                }
            }
            // Output the answer or NOT FOUND
            if (ans == -1) {
                pw.println("NOT FOUND");
            } else {
                pw.println(ans);
            }
        }
        // Close the input and output streams
        br.close();
        pw.close();
    }
}

// A class to represent a segment tree

