package ru.yandex.stage4.enumeration;

import java.util.ArrayList;
import java.util.List;

public class E {

    // создаём массивы для хранения типов скобок
    private static final char[] OPEN = {'(', '['};
    private static final char[] CLOSE = {')', ']'};

    public static void main(String[] args) {
        // считываем число n
        int n = 3;
        // выводим все правильные скобочные последовательности длины n
        System.out.println(generateParenthesis(n));
    }

    // создаём метод для проверки, является ли скобка открывающей
    private static boolean isOpen(char c) {
        for (char o : OPEN) {
            if (c == o) {
                return true;
            }
        }
        return false;
    }

    // создаём метод для проверки, является ли скобка закрывающей
    private static boolean isClose(char c) {
        for (char cl : CLOSE) {
            if (c == cl) {
                return true;
            }
        }
        return false;
    }

    // создаём метод для проверки, соответствует ли закрывающая скобка открывающей
    private static boolean match(char open, char close) {
        return (open == '(' && close == ')') || (open == '[' && close == ']');
    }

    // создаём метод для генерации правильных скобочных последовательностей
    public static List<String> generateParenthesis(int n) {
        // создаём список для хранения ответов
        List<String> result = new ArrayList<>();
        // создаём массив для хранения текущей последовательности
        char[] current = new char[n];
        // создаём массив для хранения количества открывающих скобок каждого типа
        int[] openCount = new int[OPEN.length];
        // вызываем вспомогательный метод для рекурсивной генерации
        generate(result, current, openCount, 0);
        // возвращаем список ответов
        return result;
    }

    // создаём вспомогательный метод для рекурсивной генерации
    private static void generate(List<String> result, char[] current, int[] openCount, int index) {
        // если индекс равен длине последовательности, то добавляем её в список ответов
        if (index == current.length) {
            result.add(new String(current));
            return;
        }
        // перебираем все типы открывающих скобок
        for (int i = 0; i < OPEN.length; i++) {
            // если количество открывающих скобок данного типа меньше половины длины последовательности
            if (openCount[i] < current.length / 2) {
                // добавляем открывающую скобку в текущую последовательность
                current[index] = OPEN[i];
                // увеличиваем количество открывающих скобок данного типа
                openCount[i]++;
                // рекурсивно вызываем метод для следующего индекса
                generate(result, current, openCount, index + 1);
                // восстанавливаем количество открывающих скобок данного типа
                openCount[i]--;
            }
        }
        // перебираем все типы закрывающих скобок
        for (int i = 0; i < CLOSE.length; i++) {
            // если индекс больше нуля и последняя открывающая скобка в текущей последовательности соответствует закрывающей скобке данного типа
            if (index > 0 && isOpen(current[index - 1]) && match(current[index - 1], CLOSE[i])) {
                // добавляем закрывающую скобку в текущую последовательность
                current[index] = CLOSE[i];
                // рекурсивно вызываем метод для следующего индекса
                generate(result, current, openCount, index + 1);
            }
        }
    }

    // создаём метод для тестирования

}


