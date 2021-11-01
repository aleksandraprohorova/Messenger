package com.db.edu;

import java.util.Date;

public class CmdMessage implements Message {

    private String identifier;
    private String dateValue;
    private String commandMessage;

    public CmdMessage(String identifier, String dateValue, String commandMessage) {
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
}
