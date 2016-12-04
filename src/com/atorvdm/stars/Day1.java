package com.atorvdm.stars;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksim Strutinskiy.
 */
public class Day1 {
    public static final String DELIMITER = ", ";
    public static final String LEFT = "L";
    public static final String RIGHT = "R";

    public static void main(String[] args) {
        String input = InputReader.readFrom("src/com/atorvdm/stars/input/input1.txt");
        if (input.isEmpty()) {
            System.err.println("Input is null or empty!");
            return;
        }

        String[] commands = input.split(DELIMITER);

        PathProcessor pp = new PathProcessor(0, 0, Direction.UP);

        for (String command : commands) {
            pp.move(command);
        }


        System.out.println(pp.distanceFrom(0, 0));

        Point fip = pp.getFirstIntersectionPoint();
        if (fip != null)
            System.out.println(Math.abs(fip.x) + Math.abs(fip.y));
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private static class PathProcessor {
        private int x, y;
        private Direction d;
        private List<Point> points;
        private Point firstIntersectionPoint = null;

        public PathProcessor(int x, int y, Direction d) {
            this.x = x;
            this.y = y;
            this.d = d;
            points = new ArrayList<>();
        }

        public int distanceFrom(int x, int y) {
            return Math.abs((this.x - x)) + Math.abs((this.y - y));
        }

        public void move(String command) {
            String direction = command.substring(0, 1);
            String stepsString = command.substring(1, command.length());
            int steps = Integer.parseUnsignedInt(stepsString);
            if (direction.equals(LEFT)) {
                turnLeft();
            } else if (direction.equals(RIGHT)) {
                turnRight();
            } else {
                System.err.println("Input command " + command + " is invalid.");
                return;
            }
            walk(steps);
        }

        public Point getFirstIntersectionPoint() {
            return firstIntersectionPoint;
        }

        private void walk(int steps) {
            switch (d) {
                case UP: walkUp(steps); break;
                case RIGHT: walkRight(steps); break;
                case DOWN: walkDown(steps); break;
                case LEFT: walkLeft(steps); break;
            }
        }

        private void walkUp(int steps) {
            int old = y;
            y += steps;
            for (int i = old; i < y; i++) {
                addPoint(x, i);
            }
        }

        private void walkDown(int steps) {
            int old = y;
            y -= steps;
            for (int i = old; i > y; i--) {
                addPoint(x, i);
            }
        }

        private void walkRight(int steps) {
            int old = x;
            x += steps;
            for (int i = old; i < x; i++) {
                addPoint(i, y);
            }
        }

        private void walkLeft(int steps) {
            int old = x;
            x -= steps;
            for (int i = old; i > x; i--) {
                addPoint(i, y);
            }
        }

        private void addPoint(int x, int y) {
            if (firstIntersectionPoint != null) return;

            Point point = new Point(x, y);
            if (points.contains(point)) {
                firstIntersectionPoint = point;
            } else {
                points.add(point);
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
    }
}
