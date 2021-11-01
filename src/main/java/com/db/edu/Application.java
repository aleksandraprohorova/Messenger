package com.db.edu;

import com.db.edu.client.Proxy;
import com.db.edu.connection.Connector;

import java.io.*;
import java.util.Scanner;

public class Application {

    public static void main(String[] argc) {
        Proxy proxy = new Proxy(new Connector());
        MessageCreator creator = new MessageCreator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Message message = creator.createMessage("id", "data", scanner.nextLine());
            try {
                proxy.send(message);
                Message answer = proxy.receive();
                System.out.println(answer.getIdentifier() + " " + answer.getDateValue() + " " + answer.getBody());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
