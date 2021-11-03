package com.db.edu;

import com.db.edu.connection.Proxy;
import com.db.edu.connection.Connector;
import com.db.edu.creater.MessageCreater;
import com.db.edu.message.HistMessage;
import com.db.edu.message.Message;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.lang.System.lineSeparator;

public class Client {
    public static void main(String[] argc) {
        Proxy proxy = new Proxy(new Connector());
        MessageCreater creator = new MessageCreater();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Listener listener = new Listener(proxy);
        listener.start();
        Long timeMillis = System.currentTimeMillis();
        String messageSourceName = "messageBuffer" + timeMillis.toString() + ".txt";
        createMessageSource(messageSourceName);
        createInputConsole();

        String outputHistory = "";
        boolean checkHistory = false;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = creator.createMessage("id", "data", "/hist");
                try {
                    proxy.send(message);
                } catch (IOException e) {
                   System.out.println("Connection timeout");
                }
            }
        };

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 5 * 1000);
        while (true) {
            try {
                if (listener.hasMessage()) {
                    Message answer = listener.getMessage();
                    if (answer instanceof HistMessage) {
                        String answerString = answer.getReturnMessage();
                        int answerLength = answerString.length();

                        if (checkHistory) {
                            System.out.println(answerString);
                            checkHistory = false;
                        } else {
                            if (!outputHistory.equals(answer.getReturnMessage())) {
                                System.out.println(answerString.substring(outputHistory.length(), answerLength - 1));
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

        private static void createMessageSource(String messageSourceName){
            Path path = Path.of(messageSourceName);
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
            System.out.println("test");
            Scanner scanner = new Scanner(System.in);

            final File folder = new File(".");
            String messageBufferName = getMessageBufferName(folder);

            while (true) {
                String line = scanner.nextLine();
                try(FileWriter writer = new FileWriter(messageBufferName, true))
                {
                    writer.write(line + lineSeparator());
                    writer.flush();
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }

    private static String getMessageBufferName(final File folder) {
        List<String> files = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile()) {
                String fileName = fileEntry.toString();
                if (fileName.contains("messageBuffer")) {
                    files.add(fileName);
                }
            }
        }

        Collections.sort(files);
        System.out.println(files.get(files.size() - 1));
        return files.get(files.size() - 1);
    }
}
