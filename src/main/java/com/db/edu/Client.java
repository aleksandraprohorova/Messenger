package com.db.edu;

import com.db.edu.connection.Proxy;
import com.db.edu.connection.Connector;
import com.db.edu.creater.MessageCreater;
import com.db.edu.message.Message;

import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] argc) {
        Proxy proxy = new Proxy(new Connector());
        MessageCreater creator = new MessageCreater();
        Scanner scanner = new Scanner(System.in);
        Message message, answer;

        while (true) {
            message = creator.createMessage("id", "data", scanner.nextLine());
            try {
                proxy.send(message);
                answer = proxy.receive();
                System.out.print(answer.getReturnMessage());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
