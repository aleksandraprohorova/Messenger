package com.db.edu.creater;

import com.db.edu.message.Message;

import java.io.*;

public class FileCreater {

    private final File file = new File("messages.txt");

    public FileCreater() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public File getFile() {
        return this.file;
    }

    public void write(Message message) {
        try {
            Writer writer = new OutputStreamWriter(new FileOutputStream(file, true));
            writer.write(getLineToWrite(message));
            writer.flush();
        } catch (IOException e) {
            //TODO log error
            e.printStackTrace();
        }
    }

    public String getLineToWrite(Message message) {
        return message.getIdentifier() + " " + message.getDateValue() + " "
                + message.getBody() + System.lineSeparator();
    }
}