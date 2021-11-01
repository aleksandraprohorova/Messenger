package com.db.edu;

import java.util.Date;

public class MessageCreator {

    public Message createMessage(String identifier, Date dateValue, String inputText) {

        if (inputText.startsWith("/snd <")) {
            return new DataMessage(identifier, dateValue, inputText.substring(inputText.indexOf("<")+1, inputText.indexOf(">")));
        } else if (inputText.equals("/hist")) {
            return new CmdMessage(identifier, dateValue, "hist" );

        }

        return new DataMessage(identifier, dateValue, "Wrong message!");
    }
}