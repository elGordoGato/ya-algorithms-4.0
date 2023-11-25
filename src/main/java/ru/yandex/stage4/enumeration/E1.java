package ru.yandex.stage4.enumeration;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class E1 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        // считываем число верши

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = sc.nextInt();
        if (n%2 != 0){
            pw.close();
            return;
        }

        StringBuilder string = new StringBuilder(n);


        Stack<String> queue = new Stack<>();
        addBr(pw, n, string, queue);



        pw.close();
    }

    private static void addBr(PrintWriter pw, int n, StringBuilder br, Stack<String> open) {
        if (br.length() == n) {
            pw.println(br);
            return;
        }
        if (open.size() < n - br.length()) {
            open.push("(");
            addBr(pw, n, br.append("("), open);
            br.setLength(br.length() - 1);
            open.pop();
            open.push("[");
            addBr(pw, n, br.append("["),open);
            br.setLength(br.length() - 1);
            open.pop();
        }
        if (!open.empty()) {
            String openBr = open.pop();
            addBr(pw, n, br.append(closeBracket(openBr)), open);
            br.setLength(br.length() - 1);
            open.add(openBr);
        }
    }

    private static String closeBracket(String fromStack) {
        return switch (fromStack) {
            case "(" -> ")";
            case "[" -> "]";
            default -> throw new IllegalStateException("Unexpected value: " + fromStack);
        };
    }
}

