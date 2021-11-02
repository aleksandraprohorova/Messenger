package com.db.edu;

import com.db.edu.connection.Proxy;
import com.db.edu.connection.Connector;
import com.db.edu.creater.MessageCreater;
import com.db.edu.message.Message;

import java.io.*;

public class Client {
    public static void main(String[] argc) {

        Proxy proxy = new Proxy(new Connector());
        MessageCreater creator = new MessageCreater();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Listener listener = new Listener(proxy);
        listener.start();
        while (true) {
            try {
                if (listener.hasMessage()) {
                    Message answer = listener.getMessage();
                    System.out.println(answer.getReturnMessage());
                    listener.resetMessage();
                }
                if (reader.ready()) {
                    Message message = creator.createMessage("id", "data", reader.readLine());
                    proxy.send(message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
