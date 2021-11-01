package com.db.edu.connection;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Connector extends ObjectConnection {

    @Override
    public void connect() {
        try {
            Socket socket = new Socket(InetAddress.getLoopbackAddress(), 9999);
            System.out.println("Connected");
            output = new ObjectOutputStream((socket.getOutputStream()));
            input = new ObjectInputStream((socket.getInputStream()));
            System.out.println("Streams created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
