package com.db.edu.creater;

import com.db.edu.message.HistMessage;
import com.db.edu.message.SndMessage;
import com.db.edu.message.Message;

public class MessageCreater {

    public static Message createMessage(String identifier, String dateValue, String inputText) {

        if (inputText.contains("/snd")) {
            return new SndMessage(identifier, dateValue, inputText.substring(inputText.indexOf("<")+1, inputText.indexOf(">")));
        } else if (inputText.contains("/hist")) {
            return new HistMessage(identifier, dateValue, "hist" );
        } //else if (inputText.contains("/chroom")) {
           // return new ChroomMessage(identifier)
        //}

        return new SndMessage(identifier, dateValue, "Wrong message!");
    }
}