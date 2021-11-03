package com.db.edu;

import com.db.edu.connection.Proxy;
import com.db.edu.connection.Skeleton;
import com.db.edu.exception.ServerException;
import com.db.edu.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Listener extends Thread{
    private static Logger log = LoggerFactory.getLogger(Skeleton.class);

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
            } catch (ServerException e) {
                log.error("Connection timeout.");
                return;
            }
        }
    }
}
