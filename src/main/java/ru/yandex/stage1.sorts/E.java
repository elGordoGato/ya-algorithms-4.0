package ru.yandex.stage1.sorts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class E {
/*Initial array:
12, 32, 45, 67, 98, 29, 61, 35, 09
**********
Phase 1
Bucket 0: empty
Bucket 1: 61
Bucket 2: 12, 32
Bucket 3: empty
Bucket 4: empty
Bucket 5: 45, 35
Bucket 6: empty
Bucket 7: 67
Bucket 8: 98
Bucket 9: 29, 09
**********
Phase 2
Bucket 0: 09
Bucket 1: 12
Bucket 2: 29
Bucket 3: 32, 35
Bucket 4: 45
Bucket 5: empty
Bucket 6: 61, 67
Bucket 7: empty
Bucket 8: empty
Bucket 9: 98
**********
Sorted array:
09, 12, 29, 32, 35, 45, 61, 67, 98
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        List<List<String>> buckets = new ArrayList<>(n);
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<>());
        }
        pw.println("Initial array:");
        for (int i = 0; i < n; i++) {
            String number = br.readLine();
            buckets.get(0).add(number);
        }
        String array = buckets.get(0).toString();
        pw.println(array.substring(1, array.length()-1));
        int length = buckets.get(0).get(0).length();
        for (int i = 1; i <= length; i++) {
            pw.println("**********");
            pw.println("Phase " + i);
            buckets = reorderByIndex(length-i, buckets);
            printBuckets(pw, buckets);
        }
        pw.println("**********\nSorted array:");
        StringBuilder sb = new StringBuilder();
        for (List<String> bucket : buckets) {
            for (String s : bucket) {
                sb.append(s).append(", ");
            }
        }
        sb.setLength(sb.length()-2);
        pw.print(sb);

        br.close();
        pw.close();
    }

    private static void printBuckets(PrintWriter pw, List<List<String>> buckets) {
        for (int i = 0; i < 10; i++) {

            String bucket = buckets.get(i).toString();
            bucket = bucket.substring(1, bucket.length()-1);
            String bucketLine = bucket.isBlank() ? "empty"
                    : bucket;
            pw.println(String.format("Bucket %s: %s", i, bucketLine));
        }
    }

    private static List<List<String>> reorderByIndex(int i, List<List<String>> buckets) {
        List<List<String>> newBuckets = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            newBuckets.add(new ArrayList<>());
        }
        for (List<String> bucket : buckets) {
            for (String s : bucket) {
                int number = Integer.parseInt(String.valueOf(s.charAt(i)));
                newBuckets.get(number).add(s);
            }
        }
        return newBuckets;
    }
}
