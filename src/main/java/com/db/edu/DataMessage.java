package com.db.edu;

public class DataMessage implements Message{

    private Integer identifier;
    private String dataMessage;



    @Override
    public String getBody() {
        return this.dataMessage;
    }

    @Override
    public Integer getIdentifier() {
        return this.identifier;
    }
}
