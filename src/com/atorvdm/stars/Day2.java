package com.atorvdm.stars;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Maksim Strutinskiy.
 */
public class Day2 {
    private static final int START = 1;
    private static final int SIZE = 3;
    private static final int MIDDLE = 5;

    public static void main(String[] args) {
        List<String> input = InputReader.readLines("src/com/atorvdm/stars/input/input2.txt");

        int[][] keypad = new int[SIZE][SIZE];
        int startX = -1, startY = -1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                keypad[i][j] = i * SIZE + j + START;
                if (keypad[i][j] == MIDDLE) {
                    startX = i;
                    startY = j;
                }
            }
        }

        if (startX < 0 || startY < 0) {
            System.err.println("Middle wasn't found!");
            return;
        }

        CodeTyper codeTyper = new CodeTyper(keypad, startX, startY);
        List<Integer> result = new LinkedList<>();
        input.forEach(code -> result.add(codeTyper.type(code)));
        result.forEach(number -> System.out.print(number));
        System.out.println();
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
                case 'U': x = x > 0? x - 1: x; break;
                case 'D': x = x < (SIZE - 1)? x + 1: x; break;
                case 'L': y = y > 0? y - 1: y; break;
                case 'R': y = y < (SIZE - 1)? y + 1: y; break;
            }
        }

        private int press() {
            return keypad[x][y];
        }
    }
}
