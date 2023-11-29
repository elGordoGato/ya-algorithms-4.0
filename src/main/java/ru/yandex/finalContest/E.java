package ru.yandex.finalContest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");

        int n = Integer.parseInt(tokens[0]);

        int m = Integer.parseInt(tokens[1]);

        int[] types = new int[m+1];
        int[] quantity = new int[m+1];
        int maxSum = 0;

        tokens = br.readLine().split(" ");
        for (int i = 1; i <= m; i++) {
            types[i] = Integer.parseInt(tokens[i-1]);
            maxSum += types[i]*2;
            quantity[i] = n/types[i];
            if (quantity[i] > 2){
                quantity[i] = 2;
            }
        }
        List<Integer> builded = new ArrayList<>();
        int ans = -1;
        int minSize = m*2+1;

        if(n<=maxSum) {
            ans = 0;
            for (int i = m/2; i <= m; i++) {
                if(quantity[i]>0) {
                    List<Integer> startWith = new ArrayList<>(2*m);
                    startWith.add(types[i]);
                    quantity[i] -= 1;
                    startWith = putBrick(minSize, m, startWith, types, quantity, types[i], n);
                    if (startWith != null && startWith.size() < minSize) {
                        builded = startWith;
                        minSize = startWith.size();
                    }
                }
            }
        }


        if (builded.size()>0){
            pw.println(builded.size());
            for (Integer integer : builded) {
                pw.print(integer + " ");
            }
        } else pw.println(ans);


        br.close();
        pw.close();
    }

    private static List<Integer> putBrick(int minSize, int out, List<Integer> built, int[] types, int[] quantity, int builtLength, int n) {
        if (builtLength>n){
            return null;
        }
        if (builtLength==n){
            return built;
        }
        List<Integer> bestPos = null;
        for (int i = out; i > 0; i--) {
            if (quantity[i]>0){
                quantity[i] -= 1;
                List<Integer> thisStep = new ArrayList<>(built);
                thisStep.add(types[i]);
                thisStep = putBrick(minSize, out, thisStep, types, quantity, builtLength+types[i], n);
                if (thisStep!=null && thisStep.size()<minSize){
                    minSize = thisStep.size();
                    bestPos = thisStep;
                }
                quantity[i] += 1;
            } else out = i-1;
        }
        return bestPos;
    }
}
