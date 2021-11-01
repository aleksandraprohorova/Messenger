package com.db.edu;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class FileHandler {

    private final File file = new File("messages.txt");

    FileHandler() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public ArrayList<Message> readAll() {
        ArrayList<Message> messages = new ArrayList<Message>();

        try {
            Files.lines(file.toPath()).forEach((e) -> {
                        String[] arrayMessage = e.split(" ");
                        messages.add(new DataMessage(arrayMessage[1], arrayMessage[0], arrayMessage[2]));
                    }
            );
        } catch (IOException e) {
            //TODO log error
            e.printStackTrace();
        }
        return messages;
    }

    public void write(Message message) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(getLineToWrite(message));
            writer.flush();
        } catch (IOException e) {
            //TODO log error
            e.printStackTrace();
        }
    }

    private String getLineToWrite(Message message) {
        return message.getIdentifier() + " " + message.getDateValue() + " " + message.getBody();
    }
}