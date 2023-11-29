package ru.yandex.finalContest;


import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");

        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);

        int[] types = new int[m];
        tokens = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            types[i] = Integer.parseInt(tokens[i]);
        }

        pw.print(generateWall(n, m, types));

        br.close();
        pw.close();
    }

    static String generateWall(int n, int m, int[] types) {
        int maxSum = 0;
        for (int brick : types) {
            maxSum += brick;
        }
        if (maxSum * 2 < n) {
            return "-1";
        }

        Map<Integer, List<Integer>> minsMap = sumMins(types);
        int minLen = Integer.MAX_VALUE;
        List<Integer> minStreak = null;

        for (int sum1 : minsMap.keySet()) {
            int sum2 = n - sum1;
            if (minsMap.containsKey(sum2)) {
                List<Integer> group1 = minsMap.get(sum1);
                List<Integer> group2 = minsMap.get(sum2);
                int total = group1.size() + group2.size();
                if (total < minLen) {
                    minStreak = new ArrayList<>(group2);
                    minStreak.addAll(group1);
                    minLen = total;
                }
            }
        }

        if (minStreak == null) {
            return "0";
        }

        StringBuilder ans = new StringBuilder().append(minStreak.size()).append("\n");
        for (int type : minStreak) {
            ans.append(type).append(" ");
        }
        return ans.toString();
    }

    public static Map<Integer, List<Integer>> sumMins(int[] types) {
        Map<Integer, List<Integer>> minMap = new HashMap<>();
        Set<Integer> dp = new HashSet<>();
        dp.add(0);
        minMap.put(0, new ArrayList<>());

        for (int br1 : types) {
            Set<Integer> thisStep = new HashSet<>(dp);
            for (int br2 : dp) {
                int newSum = br1 + br2;
                thisStep.add(newSum);

                List<Integer> thisStepSub = new ArrayList<>(minMap.get(br2));
                thisStepSub.add(br1);

                if (minMap.containsKey(newSum)) {
                    List<Integer> prevSub = minMap.get(newSum);
                    if (thisStepSub.size() < prevSub.size()) {
                        minMap.put(newSum, thisStepSub);
                    }
                }
                else {
                    minMap.put(newSum, thisStepSub);
                }
            }
            dp = thisStep;
        }
        return minMap;
    }
}
