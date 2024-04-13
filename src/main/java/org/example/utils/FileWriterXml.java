package org.example.utils;

import org.example.model.Sonnet;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterXml {
    public static void writeSonnetFile(String path, Sonnet sonnet) {
        try (FileWriter fw = new FileWriter(path, true)) {
            sonnet.getLines().stream().forEach(elem -> {
                try {
                    fw.write(elem.getLine());
                    fw.write("\n");
                } catch (IOException e) {
                    System.out.println("Errors");
                }
            });
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }
}
