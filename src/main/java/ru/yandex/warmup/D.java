package ru.yandex.warmup;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split("");
        int n = tokens.length;
        Map<String, Integer> map1 = stream(tokens).collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(i -> 1)));
        tokens = br.readLine().split("");
        Map<String, Integer> map2 = new HashMap<>(map1.size());
        if (tokens.length != n) {
            pw.println("NO");
            br.close();
            pw.close();
            return;
        }
        for (String token : tokens) {
            map2.put(token,
                    map2.getOrDefault(token, 0) + 1);
            Integer count = map1.get(token);
            if (count == null || map2.get(token) > count) {
                pw.println("NO");
                br.close();
                pw.close();
                return;
            }
        }

        pw.println("YES");
        br.close();
        pw.close();
    }
}
