package com.db.edu.message;

import com.db.edu.creater.FileCreater;

import java.io.IOException;
import java.nio.file.Files;

public class HistMessage implements Message {

    private String identifier;
    private String dateValue;
    private String commandMessage;
    private String returnMessage;

    public HistMessage(String identifier, String dateValue, String commandMessage) {
        this.identifier = identifier;
        this.commandMessage = commandMessage;
        this.dateValue = dateValue;
    }

    @Override
    public String getBody() {
        return this.commandMessage;
    }

    @Override
    public String getDateValue() {
        return this.dateValue;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public String getReturnMessage() {
        return this.returnMessage;
    }

    @Override
    public void makeReturnMessage(FileCreater fileCreater){
        StringBuffer makeString = new StringBuffer();
        try {
            Files.lines(fileCreater.getFile().toPath()).forEach((e) -> {
                makeString.append(e).append(System.lineSeparator());
            });
        } catch (IOException exception){
            exception.getCause();
        }
        returnMessage = makeString.toString();
    }
}
