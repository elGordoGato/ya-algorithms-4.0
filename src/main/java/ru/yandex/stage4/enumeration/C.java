package ru.yandex.stage4.enumeration;

import java.util.Scanner;

public class C {
    public static double[][] laplacian(double[][] adjacency) {
        // Получаем размер матрицы
        int n = adjacency.length;
        // Создаем матрицу для хранения лапласиана
        double[][] laplacian = new double[n][n];
        // Вычисляем лапласиан по формуле L = D - A, где D - диагональная матрица степеней вершин, A - матрица смежности
        for (int i = 0; i < n; i++) {
            // Считаем степень i-й вершины
            double degree = 0;
            for (int j = 0; j < n; j++) {
                degree += adjacency[i][j];
            }
            // Заполняем элементы лапласиана
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    // Диагональный элемент равен степени вершины
                    laplacian[i][j] = degree;
                } else {
                    // Недиагональный элемент равен отрицательному весу ребра
                    laplacian[i][j] = -adjacency[i][j];
                }
            }
        }
        // Возвращаем лапласиан
        return laplacian;
    }

    // Метод для нахождения собственного вектора, соответствующего второму наименьшему собственному значению матрицы
    // Используем метод обратной итерации с сдвигом
    public static double[] eigenvector(double[][] matrix) {
        // Получаем размер матрицы
        int n = matrix.length;
        // Выбираем сдвиг, близкий к ожидаемому собственному значению (например, 0.5)
        double shift = 0.5;
        // Вычитаем из матрицы сдвиг, умноженный на единичную матрицу
        for (int i = 0; i < n; i++) {
            matrix[i][i] -= shift;
        }
        // Выбираем случайный вектор для начального приближения
        double[] vector = new double[n];
        for (int i = 0; i < n; i++) {
            vector[i] = Math.random();
        }
        // Задаем точность и максимальное число итераций
        double epsilon = 1e-6;
        int maxIterations = 100;
        // Повторяем итерации, пока не достигнем сходимости или не превысим лимит
        for (int k = 0; k < maxIterations; k++) {
            // Решаем систему линейных уравнений matrix * x = vector
            // Используем метод Гаусса с выбором главного элемента
            double[] x = new double[n];
            // Копируем матрицу и вектор, чтобы не изменять исходные данные
            double[][] a = new double[n][n];
            double[] b = new double[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = matrix[i][j];
                }
                b[i] = vector[i];
            }
            // Прямой ход - исключаем переменные
            for (int i = 0; i < n; i++) {
                // Ищем главный элемент в i-м столбце
                int maxRow = i;
                for (int j = i + 1; j < n; j++) {
                    if (Math.abs(a[j][i]) > Math.abs(a[maxRow][i])) {
                        maxRow = j;
                    }
                }
                // Меняем местами i-ю и maxRow-ю строки
                double[] temp = a[i];
                a[i] = a[maxRow];
                a[maxRow] = temp;
                double t = b[i];
                b[i] = b[maxRow];
                b[maxRow] = t;
                // Вычитаем i-ю строку из всех нижних строк, умножая на соответствующий коэффициент
                for (int j = i + 1; j < n; j++) {
                    double c = a[j][i] / a[i][i];
                    for (int l = i; l < n; l++) {
                        a[j][l] -= c * a[i][l];
                    }
                    b[j] -= c * b[i];
                }
            }
            // Обратный ход - находим решение
            for (int i = n - 1; i >= 0; i--) {
                // Выражаем i-ю переменную через последующие
                double sum = 0;
                for (int j = i + 1; j < n; j++) {
                    sum += a[i][j] * x[j];
                }
                // Учитываем свободный член
                x[i] = (b[i] - sum) / a[i][i];
            }
            // Нормируем решение, чтобы его длина была равна 1
            double length = 0;
            for (int i = 0; i < n; i++) {
                length += x[i] * x[i];
            }
            length = Math.sqrt(length);
            for (int i = 0; i < n; i++) {
                x[i] /= length;
            }
            // Проверяем, насколько решение отличается от предыдущего приближения
            double diff = 0;
            for (int i = 0; i < n; i++) {
                diff += Math.abs(x[i] - vector[i]);
            }
            // Если разность меньше заданной точности, то останавливаемся
            if (diff < epsilon) {
                break;
            }
            // Иначе обновляем приближение
            vector = x;
        }
        // Возвращаем найденный собственный вектор
        return vector;
    }


    // Метод для разбиения графа на две доли по знакам собственного вектора
    public static int[] partition(double[] vector) {
        // Получаем размер вектора
        int n = vector.length;
        // Создаем массив для хранения номеров долей для каждой вершины
        int[] parts = new int[n];
        // Перебираем все компоненты вектора
        for (int i = 0; i < n; i++) {
            // Если компонента положительна, то присваиваем вершине номер доли 1
            if (vector[i] > 0) {
                parts[i] = 1;
            } else {
                // Иначе присваиваем вершине номер доли 2
                parts[i] = 2;
            }
        }
        // Возвращаем массив номеров долей
        return parts;
    }

    // Метод для подсчета суммарного веса ребер между долями
    public static int weight(int[] parts, double[][] adjacency) {
        // Получаем размер матрицы смежности
        int n = adjacency.length;
        // Создаем переменную для хранения суммарного веса
        int sum = 0;
        // Перебираем все ребра графа
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Если ребро соединяет вершины из разных долей, то прибавляем его вес к сумме
                if (parts[i] != parts[j]) {
                    sum += adjacency[i][j];
                }
            }
        }
        // Возвращаем суммарный вес
        return sum;
    }

    public static void main(String[] args) {
        // Создаем объект класса Scanner для считывания ввода
        Scanner scanner = new Scanner(System.in);
        // Считываем число N
        int N = scanner.nextInt();
        // Создаем матрицу для хранения матрицы смежности
        double[][] adjacency = new double[N][N];
        // Считываем матрицу смежности
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                adjacency[i][j] = scanner.nextDouble();
            }
        }
        // Вычисляем лапласиан графа
        double[][] laplacian = laplacian(adjacency);
        // Находим собственный вектор, соответствующий второму наименьшему собственному значению лапласиана
        double[] vector = eigenvector(laplacian);
        // Разбиваем граф на две доли по знакам собственного вектора
        int[] parts = partition(vector);
        // Подсчитываем суммарный вес ребер между долями
        int sum = weight(parts, adjacency);
        // Выводим суммарный вес ребер
        System.out.println(sum);
        // Выводим номера долей для каждой вершины
        for (int i = 0; i < N; i++) {
            System.out.print(parts[i] + " ");
        }
        System.out.println();
    }

}
