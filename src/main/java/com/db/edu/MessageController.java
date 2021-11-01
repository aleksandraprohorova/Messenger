package com.db.edu;

import com.db.edu.messager.CmdMessage;
import com.db.edu.messager.DataMessage;
import com.db.edu.messager.Message;

import java.util.ArrayList;

public class MessageController implements Controller {

    private Sceleton sceleton;
    private FileHandler filehandler;

    @Override
    public void parse(Message message) {
        if (message instanceof CmdMessage) {
            ArrayList<Message> Messages = filehandler.readAll();
            for (Message m : Messages) {
                sceleton.sendAll(m);
            }
        } else if (message instanceof DataMessage) {
            filehandler.write(message);
            sceleton.sendAll(message);
        }
    }
}
