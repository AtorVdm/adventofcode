package com.atorvdm.stars;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Maksim Strutinskiy.
 */
public class Star1 {
    public static final String DELIMITER = ", ";
    public static final String LEFT = "L";
    public static final String RIGHT = "R";

    public static void main(String[] args) {
        StringBuilder inputSB = new StringBuilder();
        try {
//            input = new Scanner(new URL("http://adventofcode.com/2016/day/1/input").openStream(), "UTF-8")
//                    .useDelimiter(DELIMITER).next();
            Files.lines(Paths.get("src/com/atorvdm/stars/input/input1.txt"))
                    .forEach(s -> inputSB.append(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String input = inputSB.toString();
        if (input == null || input.isEmpty()) {
            System.err.println("Input is null or empty!");
            return;
        }

        String[] commands = input.split(DELIMITER);

        PathProcessor pp = new PathProcessor(0, 0, PathProcessor.Direction.UP);

        for (String command : commands) {
            pp.move(command);
        }

        System.out.println(pp.distanceFrom(0, 0));
    }

    private static class PathProcessor {
        private int x, y;
        private Direction d;

        public PathProcessor(int x, int y, Direction d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        public int distanceFrom(int x, int y) {
            return Math.abs((this.x - x) + (this.y - y));
        }

        public void move(String command) {
            String direction = command.substring(0, 1);
            String stepsString = command.substring(1, command.length());
            int steps = Integer.parseUnsignedInt(stepsString);
            if (direction.equals(LEFT)) {
                turnLeft();
                walk(steps);
            } else if (direction.equals(RIGHT)) {
                turnRight();
                walk(steps);
            } else {
                System.err.println("Input command " + command + " is invalid.");
            }
        }

        private void walk(int steps) {
            switch (d) {
                case UP: y += steps; break;
                case RIGHT: x += steps; break;
                case DOWN: y -= steps; break;
                case LEFT:  x -= steps; break;
            }
        }

        private void turnLeft() {
            switch (d) {
                case UP: d = Direction.LEFT; break;
                case RIGHT: d = Direction.UP; break;
                case DOWN: d = Direction.RIGHT; break;
                case LEFT: d = Direction.DOWN; break;
            }
        }

        private void turnRight() {
            switch (d) {
                case UP: d = Direction.RIGHT; break;
                case RIGHT: d = Direction.DOWN; break;
                case DOWN: d = Direction.LEFT; break;
                case LEFT: d = Direction.UP; break;
            }
        }

        public enum Direction {
            UP, DOWN, LEFT, RIGHT
        }
    }
}
