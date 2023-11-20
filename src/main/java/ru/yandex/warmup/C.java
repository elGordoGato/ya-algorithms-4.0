package ru.yandex.warmup;

import java.io.*;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");

        double x1 = Double.parseDouble(tokens[0]);
        double y1 = Double.parseDouble(tokens[1]);
        double x2 = Double.parseDouble(tokens[2]);
        double y2 = Double.parseDouble(tokens[3]);

        double rad1 = Math.sqrt(x1*x1 + y1*y1);
        double rad2 = Math.sqrt(x2*x2 + y2*y2);
        double diffRad = Math.abs(rad2 - rad1);

        double theta1 = Math.atan2(y1, x1);
        double theta2 = Math.atan2(y2, x2);
        double beta = Math.min(Math.abs(theta2 - theta1), 2 * Math.PI - theta1 + theta2);
        double arc = Math.min(rad1, rad2) * beta;

        pw.println(Math.min(
                Double.isNaN(arc)
                ? Double.MAX_VALUE
                : (arc + diffRad),
                rad1 + rad2));

        br.close();
        pw.close();
    }
}
