package com.db.edu;

import com.db.edu.messager.DataMessage;
import com.db.edu.messager.Message;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class FileHandler {

    private final File file = new File("messages.txt");

    FileHandler() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    // format: data id text
    public ArrayList<Message> readAll() {
        ArrayList<Message> messages = new ArrayList<Message>();

        try {
            Files.lines(file.toPath()).forEach((e) -> {
                String[] arrayMessage = e.split(" ");
                messages.add(new DataMessage(arrayMessage[0], arrayMessage[1], arrayMessage[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    });

}

    public void write(Message message) {
        Files.writeString(file.toPath(), message.getBody());
    }