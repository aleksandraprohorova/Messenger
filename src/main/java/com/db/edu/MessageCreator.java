package com.db.edu;

public class MessageCreator {

    public static Message createMessage(String inputText) {

        if (inputText.contains("/snd")) {
            return new DataMessage();
        } else if (inputText.contains("/hist")) {


        }

        else
        {


        }
        return null;
    }
}