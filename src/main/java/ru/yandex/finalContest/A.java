package ru.yandex.finalContest;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        int a = 1;
        int b = 1;
        for (int i = 1; i < x; i++) {
            if (Math.pow(a, 2) > Math.pow(b, 3)) {
                b++;
            } else if (Math.pow(a, 2) < Math.pow(b, 3)) {
                a++;
            } else {
                a++;
                b++;
            }
        }
        System.out.println((long) Math.min(Math.pow(a, 2), Math.pow(b, 3)));
    }
}

