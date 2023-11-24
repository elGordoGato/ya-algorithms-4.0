package ru.yandex.stage3.dijkstra;

import java.io.*;
import java.util.*;

public class E {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        String[] tokens = br.readLine().split(" ");

        int A = Integer.parseInt(tokens[0]);
        int B = Integer.parseInt(tokens[1]);

        int K = Integer.parseInt(br.readLine());



        Map<Integer, Node> nodeMap = new HashMap<>(N);
        for (int i = 0; i < K; i++) {
            tokens = br.readLine().split(" ");
            Integer a = Integer.parseInt(tokens[0]);
            Integer aDep = Integer.parseInt(tokens[1]);
            Integer b = Integer.parseInt(tokens[2]);
            Integer bArr = Integer.parseInt(tokens[3]);
            Node nodeA = nodeMap.getOrDefault(a, new Node(a, Integer.MAX_VALUE));
            nodeA.getAdjacentNodes().add(List.of(b, aDep, bArr));
            nodeMap.put(a, nodeA);
            Node nodeB = nodeMap.getOrDefault(b, new Node(b, Integer.MAX_VALUE));
            nodeMap.put(b, nodeB);
        }

        if(!(nodeMap.containsKey(A) && nodeMap.containsKey(B))){
            pw.println("-1");
            br.close();
            pw.close();
            return;
        }

        nodeMap.get(A).setEarliestTime(0);
        Queue<Node> notVisited = new PriorityQueue<>(N);
        notVisited.add(nodeMap.get(A));

        while (!notVisited.isEmpty()) {
            Node currentNode = notVisited.poll();
            Integer passedTime = currentNode.getEarliestTime();
            if(passedTime > nodeMap.get(currentNode.getVertex()).getEarliestTime()){
                continue;
            }
            for (List<Integer> neighborData : currentNode.adjacentNodes) {
                Integer dep = neighborData.get(1);
                Integer arr = neighborData.get(2);
                Node neighborNode = nodeMap.get(neighborData.get(0));
                if (dep>=passedTime && arr < neighborNode.getEarliestTime()) {
                    neighborNode.setEarliestTime(arr);
                    notVisited.add(neighborNode);
                }
            }
        }

        Integer ans = nodeMap.get(B).getEarliestTime();
        pw.println(ans == Integer.MAX_VALUE ? -1 : ans);


        br.close();
        pw.close();
    }

    private static class Node implements Comparable<Node> {
        Integer vertex;
        Integer earliestTime;
        List<List<Integer>> adjacentNodes = new ArrayList<>();

        public Node(Integer vertex, Integer dist) {
            this.vertex = vertex;
            this.earliestTime = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(earliestTime, o.earliestTime);
        }

        @Override
        public String toString() {
            return vertex +
                    " - dist=" + earliestTime;
        }

        public Integer getVertex() {
            return vertex;
        }

        public Integer getEarliestTime() {
            return earliestTime;
        }

        public void setEarliestTime(Integer earliestTime) {
            this.earliestTime = earliestTime;
        }

        public List<List<Integer>> getAdjacentNodes() {
            return adjacentNodes;
        }
    }
}
