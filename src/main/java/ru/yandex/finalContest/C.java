package ru.yandex.finalContest;

import java.io.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class C {
    public static final int MAX_WEIGHT = 3000000; // 3 тонны в граммах
    public static final int MAX_TIME = 1440; // 24 часа в минутах

    // Создаем класс Edge для хранения информации о дороге
    public static class Edge {
        int to; // Номер перекрестка, куда ведет дорога
        int time; // Время, затрачиваемое на проезд по дороге
        int weight; // Максимальный вес, который можно провозить по дороге

        // Конструктор класса Edge
        public Edge(int to, int time, int weight) {
            this.to = to;
            this.time = time;
            this.weight = weight;
        }
    }

    // Создаем метод main
    public static void main(String[] args) {
        // Создаем объект Scanner для считывания ввода
        Scanner sc = new Scanner(System.in);

        // Вводим N и M
        int N = sc.nextInt();
        int M = sc.nextInt();

        // Создаем список смежности для хранения графа
        ArrayList<Edge>[] graph = new ArrayList[N];

        // Инициализируем список смежности
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        // Вводим информацию о дорогах и добавляем их в граф
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt() - 1; // Номер первого перекрестка
            int b = sc.nextInt() - 1; // Номер второго перекрестка
            int t = sc.nextInt(); // Время проезда по дороге
            int w = sc.nextInt(); // Максимальный вес по дороге
            // Добавляем дорогу в обе стороны
            graph[a].add(new Edge(b, t, w));
            graph[b].add(new Edge(a, t, w));
        }

        // Создаем массив для хранения кратчайшего времени до каждого перекрестка
        int[] dist = new int[N];

        // Инициализируем массив бесконечностями
        for (int i = 0; i < N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        // Устанавливаем расстояние до старой квартиры равным нулю
        dist[0] = 0;

        // Создаем приоритетную очередь для хранения пар (расстояние, вершина)
        PriorityQueue<AbstractMap.SimpleEntry<Integer, Integer>> pq = new PriorityQueue<>((p1, p2) -> p1.getKey() - p2.getKey());

        // Добавляем в очередь пару (0, 0)
        pq.add(new AbstractMap.SimpleEntry<>(0, 0));

        // Пока очередь не пуста, повторяем следующее
        while (!pq.isEmpty()) {
            // Извлекаем из очереди пару с минимальным расстоянием
            AbstractMap.SimpleEntry<Integer, Integer> pair = pq.poll();
            int d = pair.getKey(); // Расстояние до текущей вершины
            int v = pair.getValue(); // Номер текущей вершины

            // Если расстояние до текущей вершины больше, чем в массиве, то пропускаем ее
            if (d > dist[v]) continue;

            // Для каждой смежной вершины, повторяем следующее
            for (Edge edge : graph[v]) {
                int u = edge.to; // Номер смежной вершины
                int t = edge.time; // Время проезда по дороге
                int w = edge.weight; // Максимальный вес по дороге

                // Если можно улучшить расстояние до смежной вершины, то обновляем его и добавляем в очередь
                if (dist[v] + t < dist[u]) {
                    dist[u] = dist[v] + t;
                    pq.add(new AbstractMap.SimpleEntry<>(dist[u], u));
                }
            }
        }

        // Если расстояние до новой квартиры больше, чем максимальное время, то выводим 0
        if (dist[N - 1] > MAX_TIME) {
            System.out.println(0);
            return;
        }

        // Иначе, ищем минимальное ограничение по весу на кратчайшем пути
        int minWeight = Integer.MAX_VALUE;

        // Для каждой дороги на кратчайшем пути, повторяем следующее
        for (int i = N - 1; i != 0;) {
            // Для каждой смежной вершины, повторяем следующее
            for (Edge edge : graph[i]) {
                int u = edge.to; // Номер смежной вершины
                int t = edge.time; // Время проезда по дороге
                int w = edge.weight; // Максимальный вес по дороге

                // Если смежная вершина лежит на кратчайшем пути, то обновляем минимальный вес и переходим к ней
                if (dist[i] - t == dist[u]) {
                    minWeight = Math.min(minWeight, w);
                    i = u;
                    break;
                }
            }
        }

        // Выводим количество кружек, равное разности минимального веса и веса фуры, деленной на вес одной кружки
        System.out.println((minWeight - MAX_WEIGHT) / 100);
    }
}

