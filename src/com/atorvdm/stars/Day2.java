package com.atorvdm.stars;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Maksim Strutinskiy.
 */
public class Day2 {
    private static final int START = 1;
    private static final int SIZE = 3;
    private static final int SIZE_ADVANCED = 5;
    private static final int MIDDLE = 5;
    public static final int EMPTY = -1;

    public static void main(String[] args) {
        List<String> input = InputReader.readLines("src/com/atorvdm/stars/input/input2.txt");

        basicCodeTyper(input);

        diamondCodeTyper(input);
    }

    private static void basicCodeTyper(List<String> input) {
        CodeTyper codeTyper = basicCodeTyper(SIZE, MIDDLE, START);
        if (codeTyper == null) return;
        List<Integer> result = new LinkedList<>();
        input.forEach(code -> result.add(codeTyper.type(code)));
        result.forEach(System.out::print);
        System.out.println();
    }

    private static void diamondCodeTyper(List<String> input) {
        CodeTyper codeTyper = diamondCodeTyper(SIZE_ADVANCED, MIDDLE, START);
        if (codeTyper == null) return;
        List<Integer> result = new LinkedList<>();
        input.forEach(code -> result.add(codeTyper.type(code)));
        result.forEach(number -> {
            String output = "";
            switch (number) {
                case 10: output = "A"; break;
                case 11: output = "B"; break;
                case 12: output = "C"; break;
                case 13: output = "D"; break;
                    default: output = number + "";
            }
            System.out.print(output);
        });
        System.out.println();
    }

    private static CodeTyper basicCodeTyper(int size, int middle, int start) {
        int[][] keypad = new int[size][size];

        int startX = EMPTY, startY = EMPTY;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                keypad[i][j] = i * size + j + start;
                if (keypad[i][j] == middle) {
                    startX = i;
                    startY = j;
                }
            }
        }

        if (startX < 0 || startY < 0) {
            System.err.println("Middle wasn't found!");
            return null;
        }

        return new CodeTyper(keypad, startX, startY);
    }

    private static CodeTyper diamondCodeTyper(int size, int middle, int start) {
        int[][] keypad = new int[size][size];

        for (int[] row : keypad)
            Arrays.fill(row, EMPTY);

        int startX = EMPTY, startY = EMPTY;
        int counter = start;
        for (int i = 0; i < size; i++) {
            int amount = size > 2 * i? diamondTop(i) : diamondBottom(i, size);
            int from = (size - amount) / 2;
            int to = size - from - 1;
            for (int j = 0; j < size; j++) {
                if (j >= from && j <= to) {
                    keypad[i][j] = counter++;
                    if (keypad[i][j] == middle) {
                        startX = i;
                        startY = j;
                    }
                }
            }
        }

        if (startX < 0 || startY < 0) {
            System.err.println("Middle wasn't found!");
            return null;
        }

        return new CodeTyper(keypad, startX, startY);
    }

    private static int diamondTop(int i) {
        return i * 2 + 1;
    }

    private static int diamondBottom(int i, int size) {
        return 2 * size - diamondTop(i);
    }

    private static class CodeTyper {
        private int[][] keypad;
        private int x, y;

        public CodeTyper(int[][] keypad, int startX, int startY) {
            this.keypad = keypad;
            this.x = startX;
            this.y = startY;
        }

        public int type(String code) {
            char[] charArray = code.toCharArray();
            for (char c: charArray) {
                move(c);
            }
            return press();
        }

        private void move(char c) {
            switch (c) {
                case 'U':
                    if (x > 0 && keypad[x - 1][y] != EMPTY)
                        x--;
                    break;

                case 'D':
                    if (x < (keypad.length - 1) && keypad[x + 1][y] != EMPTY)
                        x++;
                    break;

                case 'L':
                    if (y > 0 && keypad[x][y - 1] != EMPTY)
                        y--;
                    break;

                case 'R':
                    if (y < (keypad.length - 1) && keypad[x][y + 1] != EMPTY)
                        y++;
                    break;
            }
        }

        private int press() {
            return keypad[x][y];
        }
    }
}
