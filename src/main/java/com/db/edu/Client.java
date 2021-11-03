package com.db.edu;

import com.db.edu.connection.Proxy;
import com.db.edu.connection.Connector;
import com.db.edu.creater.MessageCreater;
import com.db.edu.exception.ServerException;
import com.db.edu.message.HistMessage;
import com.db.edu.message.Message;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Client {
    public static void main(String[] argc) throws IOException {
        Proxy proxy = new Proxy(new Connector());
        MessageCreater creator = new MessageCreater();
        Listener listener = new Listener(proxy);
        listener.start();
        Long timeMillis = System.currentTimeMillis();
        String messageSourceName = "messageBuffer" + timeMillis.toString() + ".txt";
        createMessageSource(messageSourceName);

        String outputHistory = "";
        boolean checkHistory = false;
        final boolean[] serverIsAlive = {true};

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = creator.createMessage("id", "data", "/hist");
                try {
                    proxy.send(message);
                } catch (ServerException e) {
                   serverIsAlive[0] = false;
                   cancel();
                }
            }
        };


        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 5 * 1000);
        while (serverIsAlive[0]){
            final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

            while (true) {
                try {
                    if (listener.hasMessage()) {
                        Message answer = listener.getMessage();
                        if (answer instanceof HistMessage) {
                            String answerString = answer.getReturnMessage();
                            int answerLength = answerString.length();

                            if (checkHistory) {
                                out.write(answerString);
                                out.flush();
                                checkHistory = false;
                            } else {
                                if (!outputHistory.equals(answer.getReturnMessage())) {
                                    out.write(answerString.substring(outputHistory.length(), answerLength - 1));
                                    out.flush();
                                    outputHistory = answer.getReturnMessage();
                                }
                            }
                        }
                        listener.resetMessage();
                    }
                    sendNewMessagesAvailable(proxy, creator, messageSourceName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
    }


        private static void createMessageSource(String messageSourceName){
            Path path = Paths.get(messageSourceName);
            if (!Files.exists(path)) {
                try {
                    Files.createFile(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private static void sendNewMessagesAvailable(Proxy proxy, MessageCreater creator, String messageSourceName){
            List<String> newMessagePrintedList = getNewMessagePrintedList(messageSourceName);
            if (!newMessagePrintedList.isEmpty()) {
                while (!newMessagePrintedList.isEmpty()) {
                    Message message = creator.createMessage("id", "data", newMessagePrintedList.remove(0));
                    try {
                        proxy.send(message);
                    } catch (ServerException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private static List<String> getNewMessagePrintedList(String messageSourceName) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(new FileInputStream(messageSourceName), "UTF-16");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

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
            scanner.close();
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
