package com.db.edu;

import com.db.edu.client.Proxy;
import com.db.edu.connection.Connector;

import java.io.*;

public class Application {

    public static void main(String[] argc) {
        Proxy proxy = new Proxy(new Connector());
        MessageCreator creator = new MessageCreator();
        Message message = new DataMessage("id", "data", "message");//creator.createMessage("id", "data", "/snd text body");
        try {
            proxy.send(message);
            System.out.println("Sended");
            proxy.receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
