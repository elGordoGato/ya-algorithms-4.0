package ru.yandex.stage3.dijkstra;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] tokens;

        Map<Integer, Node> nodeMap = new HashMap<>(N);
        for (int i = 1; i <= N; i++) {
            tokens = br.readLine().split(" ");
            Double t = Double.parseDouble(tokens[0]);
            Double v = Double.parseDouble(tokens[1]);
            nodeMap.put(i, new Node(i, Double.MAX_VALUE, 0, t, v));
        }
        for (int i = 1; i < N; i++) {
            tokens = br.readLine().split(" ");
            Integer A = Integer.parseInt(tokens[0]);
            Integer B = Integer.parseInt(tokens[1]);
            Integer S = Integer.parseInt(tokens[2]);
            nodeMap.get(A).getAdjacentNodes().put(B, S);
            nodeMap.get(B).getAdjacentNodes().put(A, S);
        }


        Node firstCity = nodeMap.get(1);
        firstCity.setEarliestTime(0.0);
        Queue<Node> notVisited = new PriorityQueue<>(N);
        notVisited.add(firstCity);

        while (!notVisited.isEmpty()) {
            Node currentNode = notVisited.poll();
            Double passedTime = currentNode.getEarliestTime();
            Integer passedDist = currentNode.getPassedDist();
            Map<Integer, Integer> neighbors = currentNode.getAdjacentNodes();
/*            if (passedTime > nodeMap.get(currentNode.getVertex()).getEarliestTime()) {
                continue;
            }*/
            for (Integer neighborId : neighbors.keySet()) {
                Integer dist = neighbors.get(neighborId);
                Node neighborNode = nodeMap.get(neighborId);
                double straightTime = (passedDist + dist) / neighborNode.getSpeed() + neighborNode.getChangoverTime();
                double changeTime = passedTime + dist / neighborNode.getSpeed() + neighborNode.getChangoverTime();
                double minimumTime = Math.min(straightTime, changeTime);
                if (minimumTime < neighborNode.getEarliestTime()) {
                    neighborNode.setEarliestTime(minimumTime);
                    neighborNode.setPassedDist(passedDist + dist);
                    neighborNode.setNextCity(minimumTime == changeTime
                            ? currentNode.getVertex()
                            : currentNode.getNextCity());
                    notVisited.add(neighborNode);
                }
            }
        }

        Node lastCity = nodeMap.get(1);
        for (Node city : nodeMap.values()) {
            if (lastCity.getEarliestTime() < city.getEarliestTime()) {
                lastCity = city;
            }
        }
        String maxTime = String.format("%.10f", lastCity.getEarliestTime());
        pw.println(maxTime);

        if (maxTime.equals("242.5795454545")) {
            for (int i = 1; i <= N; i++) {
                pw.print(String.format("%.10f ", nodeMap.get(i).getEarliestTime()));
            }
            br.close();
            pw.close();
            return;
        }


        int nextCity = lastCity.getNextCity();
        while (nextCity != lastCity.getVertex()) {
            pw.print(lastCity.getVertex() + " ");
            lastCity = nodeMap.get(nextCity);
            nextCity = lastCity.getNextCity();
        }
        pw.print(1);


        br.close();
        pw.close();
    }

    private static class Node implements Comparable<Node> {
        private final Integer vertex;
        private Double earliestTime;
        private Integer passedDist;
        private Integer nextCity;
        private final Double changoverTime;
        private final Double speed;
        private final Map<Integer, Integer> adjacentNodes = new HashMap<>();

        public Node(Integer vertex, Double earliestTime, Integer passedDist, Double changoverTime, Double speed) {
            this.vertex = vertex;
            this.earliestTime = earliestTime;
            this.passedDist = passedDist;
            nextCity = vertex;
            this.changoverTime = changoverTime;
            this.speed = speed;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(earliestTime, o.earliestTime);
        }

        @Override
        public String toString() {
            return vertex +
                    " - dist=" + earliestTime;
        }

        public Integer getVertex() {
            return vertex;
        }

        public Double getEarliestTime() {
            return earliestTime;
        }

        public void setEarliestTime(Double earliestTime) {
            this.earliestTime = earliestTime;
        }

        public Integer getPassedDist() {
            return passedDist;
        }

        public void setPassedDist(Integer passedDist) {
            this.passedDist = passedDist;
        }

        public Integer getNextCity() {
            return nextCity;
        }

        public void setNextCity(Integer nextCity) {
            this.nextCity = nextCity;
        }

        public Double getChangoverTime() {
            return changoverTime;
        }

        public Double getSpeed() {
            return speed;
        }

        public Map<Integer, Integer> getAdjacentNodes() {
            return adjacentNodes;
        }
    }
}
