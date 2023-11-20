package ru.yandex.warmup;

import java.io.*;

public class G1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");


        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);
        int[][] field = new int[N][M];
        int maxSquare = 0;

        String[] firstStr = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            field[0][i] = Integer.parseInt(firstStr[i]);
            maxSquare = Math.max(maxSquare, field[0][i]);
        }

        for (int i = 1; i < N; i++) {
            String[] str = br.readLine().split(" ");

            field[i][0] = Integer.parseInt(str[0]);
            maxSquare = Math.max(maxSquare, field[i][0]);

            for (int j = 1; j < M; j++) {
                if (str[j].equals("1")) {

                    field[i][j] = Math.min(field[i][j - 1], Math.min(field[i - 1][j], field[i - 1][j - 1])) + 1;
                    maxSquare = Math.max(maxSquare, field[i][j]);

                } else {
                    field[i][j] = 0;
                }
            }
        }


        pw.println(maxSquare);

        br.close();
        pw.close();
    }
}
