package ru.yandex.warmup;

import java.io.*;
import java.util.EmptyStackException;
import java.util.Stack;

public class I {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();

        if(str==null){
            pw.println("yes");
            br.close();
            pw.close();
            return;
        }

        String[] tokens = str.split("");

        String open = "({[";
        boolean right = true;

        Stack<String> queue = new Stack<>();
        for (String token : tokens) {
            try {
                if (open.contains(token)) {
                    queue.push(token);
                } else if (!checkBracket(token, queue.pop())) {
                    right = false;
                    break;
                }
            } catch (EmptyStackException e){
                right = false;
                break;
            }
        }
        if (!queue.empty()){
            right = false;
        }



        pw.println(right ? "yes" : "no");


        br.close();
        pw.close();
    }

    private static boolean checkBracket(String token, String fromStack){
        return switch (token) {
            case ")" -> fromStack.equals("(");
            case "}" -> fromStack.equals("{");
            case "]" -> fromStack.equals("[");
            default -> false;
        };
    }
}
