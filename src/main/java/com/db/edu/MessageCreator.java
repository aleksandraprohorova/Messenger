package com.db.edu;

public class MessageCreator {

    public static Message createMessage(String identifier, String dateValue, String inputText) {

        if (inputText.contains("/snd")) {
            return new DataMessage(identifier, dateValue, inputText.substring(inputText.indexOf("<")+1, inputText.indexOf(">")));
        } else if (inputText.contains("/hist")) {
            return new CmdMessage(identifier, dateValue, "hist" );

        }

        return new DataMessage(identifier, dateValue, "Wrong message!");
    }
}