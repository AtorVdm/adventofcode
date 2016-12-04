package com.atorvdm.stars;

import java.util.List;

/**
 * Created by Maksim Strutinskiy.
 */
public class Star3 {
    public static void main(String[] args) {
        List<String> input = InputReader.readLines("src/com/atorvdm/stars/input/input3.txt");

        TriangleValidator triangleValidator = new TriangleValidator(0);

        input.forEach(line -> triangleValidator.validate(line));

        System.out.println(triangleValidator.getValidNumber());
    }

    private static class TriangleValidator {
        private int validNumber;

        public TriangleValidator(int validNumber) {
            this.validNumber = validNumber;
        }

        public void validate(String line) {
            final int MAX = line.startsWith(" ")? 4: 3;
            String[] sides = line.split(" +");
            if (sides.length == MAX) {
                int leg1 = Integer.parseUnsignedInt(sides[MAX - 3]);
                int leg2 = Integer.parseUnsignedInt(sides[MAX - 2]);
                int hyp = Integer.parseUnsignedInt(sides[MAX - 1]);

                if (leg1 + leg2 > hyp && leg1 + hyp > leg2 && leg2 + hyp > leg1)
                    validNumber++;
            } else {
                System.err.println("Number or sides is invalid!");
            }
        }

        public int getValidNumber() {
            return validNumber;
        }
    }
}
