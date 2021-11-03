package com.db.edu;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.lineSeparator;

public class ConsoleInput {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        final File folder = new File(".");
        String messageBufferName = getMessageBufferName(folder);

        while (true) {
            String line = reader.readLine();
            try (
                    FileOutputStream stream = new FileOutputStream(messageBufferName);
                    OutputStreamWriter writer = new OutputStreamWriter(stream, StandardCharsets.UTF_16)) {
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
