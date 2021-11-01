package com.db.edu.connection;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Connector extends ObjectConnection {

    public void connect() {
        try {
            Socket socket = new Socket(InetAddress.getLoopbackAddress(), 9999);
            output = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
