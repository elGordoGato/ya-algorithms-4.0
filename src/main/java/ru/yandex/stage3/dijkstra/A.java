package ru.yandex.stage3.dijkstra;

import java.io.*;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");

        int N = Integer.parseInt(tokens[0]);
        int S = Integer.parseInt(tokens[1]);
        int F = Integer.parseInt(tokens[2]);

        int[][] nodeMatrix = new int[N + 1][N + 1];
        Queue<Node> notVisited = new PriorityQueue<>(N - 1);
        int[] shortestDist = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if(i!=S) notVisited.add(new Node(i, Integer.MAX_VALUE));
            shortestDist[i] = Integer.MAX_VALUE;

            tokens = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                int distance = Integer.parseInt(tokens[j - 1]);
                nodeMatrix[i][j] = distance;
            }
        }


        notVisited.add(new Node(S, 0));
        shortestDist[S] = 0;

        scanMatrix(nodeMatrix, notVisited, shortestDist);

        pw.println(shortestDist[F]!= Integer.MAX_VALUE ? shortestDist[F]: -1);


        br.close();
        pw.close();
    }

    private static void scanMatrix(int[][] nodeMatrix, Queue<Node> notVisited, int[] shortestDist) {

        while (!notVisited.isEmpty()) {
            Node current = notVisited.poll();
            int vertex = current.vertex;
            int dist = current.dist;
            if (shortestDist[vertex] < dist) {
                continue;
            }

            for (int i = 1; i < nodeMatrix.length; i++) {
                int neighborDist = nodeMatrix[vertex][i];
                if (neighborDist > 0 && shortestDist[i] > neighborDist+dist) {
                    shortestDist[i] = neighborDist+dist;
                    notVisited.add(new Node(i, shortestDist[i]));
                }
            }
        }

    }

    static class Node implements Comparable<Node> {
        Integer vertex;
        Integer dist;

        public Node(Integer vertex, Integer dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(vertex, node.vertex);
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex);
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }

        @Override
        public String toString() {
            return vertex +
                    " - dist=" + dist;
        }
    }
}
