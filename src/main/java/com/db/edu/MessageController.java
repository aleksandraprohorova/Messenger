package com.db.edu;

import com.db.edu.messager.CmdMessage;
import com.db.edu.messager.DataMessage;
import com.db.edu.messager.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageController implements Controller {

    private FileHandler filehandler;

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
