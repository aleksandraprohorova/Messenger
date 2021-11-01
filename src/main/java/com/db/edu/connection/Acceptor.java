package com.db.edu.connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Acceptor extends ObjectConnection {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private ServerSocket serversocket;

    @Override
    public void connect() {
        try {
            if (serversocket != null) serversocket.close();
            serversocket = new ServerSocket(9999);
            final Socket connection = serversocket.accept();
            input = new ObjectInputStream(new BufferedInputStream(connection.getInputStream()));
            output = new ObjectOutputStream(new BufferedOutputStream(connection.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
