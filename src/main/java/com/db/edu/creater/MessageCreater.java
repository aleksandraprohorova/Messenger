package com.db.edu.creater;

import com.db.edu.message.HistMessage;
import com.db.edu.message.SndMessage;
import com.db.edu.message.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            String s="";
            Pattern p = Pattern.compile("<.*?>");
            Matcher m = p.matcher(trimmedInputText);
            while(m.find())
                s = s + m.group().subSequence(1, m.group().length()-1) +" ";
            return new SndMessage(identifier, dateValue, s.trim());
        } else{
            return new SndMessage(identifier, dateValue, "Wrong message!");
        }
    }
}