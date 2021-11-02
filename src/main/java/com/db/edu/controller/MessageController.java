package com.db.edu.controller;

import com.db.edu.controller.Controller;
import com.db.edu.creater.FileCreater;
import com.db.edu.message.Message;

import java.io.IOException;

public class MessageController implements Controller {
    private FileCreater filehandler;

    public MessageController() {
        try {
            filehandler = new FileCreater();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(Message message) {
        message.makeReturnMessage(filehandler);
    }
}

