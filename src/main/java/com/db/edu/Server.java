package com.db.edu;

import com.db.edu.client.Proxy;
import com.db.edu.connection.Connector;

import java.io.IOException;
import java.util.List;

public class Server {
    static public void main(String[] args) {
        Proxy proxy = new Proxy(new Connector());
        Controller controller = new MessageController();

        while (true) {
            try {
                Message message = (Message) proxy.receive();
                List<Message> messages = controller.execute(message);
                for (Message m : messages) {
                    proxy.send(m);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
