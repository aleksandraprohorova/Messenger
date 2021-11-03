package com.db.edu.creater;

import com.db.edu.message.HistMessage;
import com.db.edu.message.SndMessage;
import com.db.edu.message.Message;

public class MessageCreater {

    public  Message createMessage(String identifier, String dateValue, String InputText) {
        String trimmedInputText = InputText.trim();
        if (trimmedInputText.contains("/snd")) {
            return getSndMessage(identifier, dateValue, trimmedInputText);
        } else if (trimmedInputText.contains("/hist")) {
            return new HistMessage(identifier, dateValue, "hist" );
        } //else if (trimmedInputText.contains("/chroom")) {
           // return new ChroomMessage(identifier)
        //}

        return new SndMessage(identifier, dateValue, "Wrong message!");
    }

    private SndMessage getSndMessage(String identifier, String dateValue, String trimmedInputText) {
        if (trimmedInputText.length() < 5) {
            return new SndMessage(identifier, dateValue, "Message can't be empty");
        } else if (trimmedInputText.length() > 149) {
            return new SndMessage(identifier, dateValue, "Message can't be longer than 150 symbols.");
        } else if (trimmedInputText.contains("<") && trimmedInputText.contains(">")) {
            return new SndMessage(identifier, dateValue, trimmedInputText.substring(trimmedInputText.indexOf("<") + 1, trimmedInputText.indexOf(">")));
        } else{
            return new SndMessage(identifier, dateValue, "Wrong message!");
        }
    }
}