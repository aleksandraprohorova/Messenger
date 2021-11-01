package com.db.edu.messager;

public class DataMessage implements Message {

    private Integer identifier;
    private String dataMessage;

    DataMessage(String name){
        this.dataMessage = name;
    }

    @Override
    public String getBody() {
        return this.dataMessage;
    }

    @Override
    public Integer getIdentifier() {
        return this.identifier;
    }
}
