package com.db.edu.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Connector extends ObjectConnection {
    private final int PORT = 9999;
    private static Logger log = LoggerFactory.getLogger(Skeleton.class);



    @Override
    public void connect() {
        try {
            Socket socket = new Socket(InetAddress.getLoopbackAddress(), PORT);
            log.info("Connected to the Server");
            output = new ObjectOutputStream((socket.getOutputStream()));
            input = new ObjectInputStream((socket.getInputStream()));
        } catch (IOException e) {
            log.error("Connection with server lost.");
        }
    }
}
