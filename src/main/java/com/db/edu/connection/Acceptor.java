package com.db.edu.connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Acceptor extends ObjectConnection {
    private ServerSocket serversocket;

    @Override
    public void connect() {
        try {
            if (serversocket != null) serversocket.close();
            serversocket = new ServerSocket(9999);
            System.out.println("Created Socket");
            final Socket connection = serversocket.accept();
            System.out.println("Accepted");
            input = new ObjectInputStream((connection.getInputStream()));
            output = new ObjectOutputStream((connection.getOutputStream()));
            System.out.println("Streams created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
