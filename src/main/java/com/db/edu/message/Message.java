package com.db.edu.message;

import com.db.edu.creater.FileCreater;

import java.io.Serializable;

public interface Message extends Serializable {
    String getBody();
    Date getDateValue();
    String getIdentifier();
    String getReturnMessage();
    void makeReturnMessage(FileCreater fileCreater);
}