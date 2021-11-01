package com.db.edu;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            message = new DataMessage(message.getIdentifier(), dateFormat.format(new Date(System.currentTimeMillis())), message.getBody());
            filehandler.write(message);
            List<Message> list = new ArrayList<>();
            list.add(message);
            return list;
        }
        return new ArrayList<>();
    }
}

