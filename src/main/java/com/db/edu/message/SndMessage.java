package com.db.edu.message;

import com.db.edu.creater.FileCreater;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SndMessage implements Message {

    private String identifier;
    private String dateValue;
    private String dataMessage;
    private String returnMessage;

    public SndMessage(String identifier, String dateValue, String dataMessage) {
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

    @Override
    public String getReturnMessage() {
        return this.returnMessage;
    }

    @Override
    public void makeReturnMessage(FileCreater fileCreater){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Message message;
        message = new SndMessage(this.getIdentifier(), dateFormat.format(new Date(System.currentTimeMillis())), this.getBody());
        fileCreater.write(message);
        returnMessage = fileCreater.getLineToWrite(message);
    }
}
