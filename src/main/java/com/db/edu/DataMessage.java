package com.db.edu;

import java.util.Date;

public class DataMessage implements Message{

    private String identifier;
    private String dateValue;
    private String dataMessage;

    public DataMessage(String identifier, String dateValue, String dataMessage) {
        this.identifier = identifier;
        this.dataMessage = dataMessage;
        this.dateValue = dateValue;
    }

    @Override
    public String getBody() {
        return this.dataMessage;
    }

    @Override
    public String getDateValue() {
        return this.dateValue;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }
}
