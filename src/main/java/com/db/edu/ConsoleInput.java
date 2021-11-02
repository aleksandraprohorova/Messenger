package com.db.edu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.lineSeparator;

public class ConsoleInput {

    public static void main(String[] args) {
        System.out.println("test");
        Scanner scanner = new Scanner(System.in);

        final File folder = new File(".");
        String messageBufferName = getMessageBufferName(folder);

        while (true) {
            String line = scanner.nextLine();
            try(FileWriter writer = new FileWriter(messageBufferName, true))
            {
                writer.write(line + lineSeparator());
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    private static String getMessageBufferName(final File folder) {
        List<String> files = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile()) {
                String fileName = fileEntry.toString();
                if (fileName.contains("messageBuffer")) {
                    files.add(fileName);
                }
            }
        }

        Collections.sort(files);
        System.out.println(files.get(files.size() - 1));
        return files.get(files.size() - 1);
    }
}
