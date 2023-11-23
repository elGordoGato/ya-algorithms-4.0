package ru.yandex.stage3.dijkstra;

import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");

        int N = Integer.parseInt(tokens[0]);
        int K = Integer.parseInt(tokens[1]);


        Map<Integer, Node> nodeMap = new HashMap<>(N);
        for (int i = 0; i < K; i++) {
            tokens = br.readLine().split(" ");
            Integer a = Integer.parseInt(tokens[0]);
            Integer b = Integer.parseInt(tokens[1]);
            Integer l = Integer.parseInt(tokens[2]);
            Node nodeA = nodeMap.getOrDefault(a, new Node(a, Long.MAX_VALUE));
            nodeA.getAdjacentNodes().put(b, l);
            nodeMap.put(a, nodeA);
            Node nodeB = nodeMap.getOrDefault(b, new Node(b, Long.MAX_VALUE));
            nodeB.getAdjacentNodes().put(a, l);
            nodeMap.put(b, nodeB);
        }

        tokens = br.readLine().split(" ");
        int A = Integer.parseInt(tokens[0]);
        int B = Integer.parseInt(tokens[1]);
        if(!(nodeMap.containsKey(A) && nodeMap.containsKey(B))){
            pw.println("-1");
            br.close();
            pw.close();
            return;
        }

        nodeMap.get(A).setDist(0L);
        Queue<Node> notVisited = new PriorityQueue<>(N);
        notVisited.add(nodeMap.get(A));

        while (!notVisited.isEmpty()) {
            Node currentNode = notVisited.poll();
            Long passedDist = currentNode.getDist();
            if(passedDist > nodeMap.get(currentNode.getVertex()).getDist()){
                continue;
            }
            for (Integer neighborID : currentNode.adjacentNodes.keySet()) {
                Integer dist = currentNode.adjacentNodes.get(neighborID);
                Node neighborNode = nodeMap.get(neighborID);
                if (passedDist+dist < neighborNode.getDist()) {
                    neighborNode.setDist(passedDist + dist);
                    notVisited.add(neighborNode);
                }
            }
        }

        Long ans = nodeMap.get(B).getDist();
        pw.println(ans == Long.MAX_VALUE ? -1 : ans);


        br.close();
        pw.close();
    }

    static class Node implements Comparable<Node> {
        Integer vertex;
        Long dist;
        Map<Integer, Integer> adjacentNodes = new HashMap<>();

        public Node(Integer vertex, Long dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(dist, o.dist);
        }

        @Override
        public String toString() {
            return vertex +
                    " - dist=" + dist;
        }

        public Integer getVertex() {
            return vertex;
        }

        public Long getDist() {
            return dist;
        }

        public void setDist(Long dist) {
            this.dist = dist;
        }

        public Map<Integer, Integer> getAdjacentNodes() {
            return adjacentNodes;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node node)) return false;
            return Objects.equals(vertex, node.vertex);
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex);
        }
    }
}
