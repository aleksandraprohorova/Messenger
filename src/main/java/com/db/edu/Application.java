package com.db.edu;

import com.db.edu.client.Proxy;
import com.db.edu.connection.Connector;

import java.io.*;
import java.util.Scanner;

public class Application {

    static private String id = "id";

    public static void main(String[] argc) {
        Proxy proxy = new Proxy(new Connector());
        MessageCreator creator = new MessageCreator();
        Scanner scanner = new Scanner(System.in);
        Listener listener = new Listener(proxy);
        while (true) {
            try {
                if (listener.hasMessage()) {
                    Message answer = listener.getMessage();
                    System.out.println(answer.getIdentifier() + " " + answer.getDateValue() + " " + answer.getBody());
                    listener.resetMessage();
                }
                if (scanner.hasNextLine()) {
                    Message message = creator.createMessage(id, "data", scanner.nextLine());
                    proxy.send(message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
