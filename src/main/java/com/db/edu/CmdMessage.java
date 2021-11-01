package com.db.edu;

public class CmdMessage implements Message{

    private Integer identifier;
    private String commandMessage;

    @Override
    public String getBody() {
        return this.commandMessage;
    }

    @Override
    public Integer getIdentifier() {
        return this.identifier;
    }


}
