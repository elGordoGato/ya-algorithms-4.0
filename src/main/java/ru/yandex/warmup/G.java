package ru.yandex.warmup;

import java.io.*;
import java.util.Arrays;

public class G {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");


        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);
        int[][] field = new int[N][M];

        for (int i = 0; i < N; i++) {
            field[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int maxSquare = 0;
        SquareFinder finder = new SquareFinder(field);

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (field[y][x] == 1) {
                    int possible = finder.findMaxPossible(y, x);
                    if (possible>maxSquare) {
                        maxSquare = Math.max(maxSquare, finder.findSquare(y-1, x, 1, possible, 2));
                    }
                }
            }
        }


        pw.println(maxSquare);

        br.close();
        pw.close();
    }

    static class SquareFinder {
        final int[][] field;
        final int N;
        final int M;

        public SquareFinder(int[][] field) {
            this.field = field;
            N = field.length;
            M = field[0].length;
        }

        int findMaxPossible(int y, int x) {
            int down = N - 1 - y;
            int right = M - 1 - x;

            int upRight = Math.min(y, right);
            int leftDown = Math.min(x, down);

            int minSide = Math.min(upRight, leftDown);

            return minSide * 2 + (minSide == upRight ? 1 : 2);
        }

        int findSquare(int y, int x, int direction, int possible, int current) {
            if (current > possible) {
                return possible;
            }
            for (int i = 1; i < current; i++) {
                if (field[y][x] != 1){
                    return current-1;
                }
                x = x+direction;
            }
            for (int j = 0; j < current; j++) {
                if (field[y][x] != 1){
                    return current-1;
                }
                y = y+direction;
            }
            return findSquare(y, x, -1*direction, possible, current+1);
        }
    }
}
