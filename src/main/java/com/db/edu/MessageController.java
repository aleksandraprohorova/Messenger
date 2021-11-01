package com.db.edu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageController implements Controller {

    private FileHandler filehandler;

    MessageController() {
        try {
            filehandler = new FileHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> execute(Message message) {
        if (message instanceof CmdMessage) {
            return filehandler.readAll();
        } else if (message instanceof DataMessage) {
            filehandler.write(message);
            List<Message> list = new ArrayList<>();
            //TODO list.updateDate(new Date(System.currentTimeMillis()));
            list.add(message);
            return list;
        }
        return new ArrayList<>();
    }
}

