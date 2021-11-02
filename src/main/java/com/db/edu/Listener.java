package com.db.edu;

import com.db.edu.connection.Proxy;
import com.db.edu.message.Message;

import java.io.IOException;

public class Listener extends Thread{

    public Message getMessage() {
        return messageBuffer;
    }

    public void resetMessage() {
        messageBuffer = null;
    }

    private Message messageBuffer;
    private Proxy proxy;

    public Listener(Proxy proxy) {
        this.proxy = proxy;
    }

    public boolean hasMessage() {
        return messageBuffer != null;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                messageBuffer = proxy.receive();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
