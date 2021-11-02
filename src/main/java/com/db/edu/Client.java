package com.db.edu;

import com.db.edu.connection.Proxy;
import com.db.edu.connection.Connector;
import com.db.edu.creater.MessageCreater;
import com.db.edu.message.Message;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] argc) {

        Proxy proxy = new Proxy(new Connector());
        MessageCreater creator = new MessageCreater();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Listener listener = new Listener(proxy);
        listener.start();
        Long timeMillis =  System.currentTimeMillis();
        String messageSourceName = "messageBuffer" + timeMillis.toString() + ".txt";
        createMessageSource(messageSourceName);
        createInputConsole();

        while (true) {
//            try {
                if (listener.hasMessage()) {
                    Message answer = listener.getMessage();
                    System.out.println(answer.getIdentifier() + " " + answer.getDateValue() + " " + answer.getBody());
                    listener.resetMessage();
                }
                sendNewMessagesAvailable(proxy, creator, messageSourceName);

//                if (reader.ready()) {
//                    Message message = creator.createMessage("id", "data", reader.readLine());
//                    proxy.send(message);
//                }

//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    private static void createMessageSource(String messageSourceName) {
        Path path = Path.of(messageSourceName);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendNewMessagesAvailable(Proxy proxy, MessageCreater creator, String messageSourceName) {
        List<String> newMessagePrintedList = getNewMessagePrintedList(messageSourceName);
        if (!newMessagePrintedList.isEmpty()) {
            while(!newMessagePrintedList.isEmpty()) {
                Message message = creator.createMessage("id", "data", newMessagePrintedList.remove(0));
                try {
                    proxy.send(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static List<String> getNewMessagePrintedList(String messageSourceName) {
        try {
            System.setIn(new FileInputStream(messageSourceName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);

        List<String> newMessagePrintedList = new ArrayList<>();
        if (scanner.hasNextLine()) {
            while (scanner.hasNextLine()) {
                newMessagePrintedList.add(scanner.nextLine());
            }

            PrintWriter writer = null;
            try {
                writer = new PrintWriter(messageSourceName);
                writer.print("");
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return newMessagePrintedList;
    }

    private static void createInputConsole() {
        try {
            Runtime.getRuntime().exec("cmd /c start runjava.bat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
