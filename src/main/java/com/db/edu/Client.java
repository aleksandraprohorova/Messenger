package com.db.edu;

import com.db.edu.connection.Proxy;
import com.db.edu.connection.Connector;
import com.db.edu.creater.MessageCreater;
import com.db.edu.message.HistMessage;
import com.db.edu.message.Message;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class Client {
    public static void main(String[] argc) {

        Proxy proxy = new Proxy(new Connector());
        MessageCreater creator = new MessageCreater();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Listener listener = new Listener(proxy);
        listener.start();
        String outputHistory = "";
        boolean checkHistory = false;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = creator.createMessage("id", "data", "/hist");
                try {
                    proxy.send(message);
                } catch (IOException e) {
                    e.printStackTrace();
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
                if (reader.ready()) {
                    Message message = creator.createMessage("id", "data", reader.readLine());
                    if (message instanceof HistMessage) {
                        checkHistory = true;
                    }
                    proxy.send(message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
