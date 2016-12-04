package com.atorvdm.stars;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Maksim Strutinskiy.
 */
public class InputReader {
    public static String readFrom(String path) {
        StringBuilder inputSB = new StringBuilder();
        readLines(path).forEach(s -> inputSB.append(s));
        return inputSB.toString();
    }

    public static List<String> readLines(String path) {
        List<String> inputList = new LinkedList<>();
        try {
            Files.lines(Paths.get(path))
                    .forEach(s -> inputList.add(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }
}
