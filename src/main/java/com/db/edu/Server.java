package com.db.edu;

import com.db.edu.connection.Proxy;
import com.db.edu.connection.Acceptor;
import com.db.edu.controller.Controller;
import com.db.edu.controller.MessageController;
import com.db.edu.message.Message;

import java.io.IOException;

public class Server {
    static public void main(String[] args) {
        Proxy proxy = new Proxy(new Acceptor(9999));
        Controller controller = new MessageController();

        while (true) {
            try {
                Message message = proxy.receive();
                controller.execute(message);
                proxy.send(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
