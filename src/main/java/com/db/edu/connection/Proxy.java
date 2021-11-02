package com.db.edu.connection;

import com.db.edu.message.Message;
import com.db.edu.connection.Connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Proxy {
    private final Connection connector;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    public Proxy(Connection connector) {
        this.connector = connector;
        connector.connect();
    }

    public void send(Message message) throws IOException {
        int count = 0;
        int maxTries = 10;
        while (true) {
            try {
                output = connector.getOutput();
                output.writeObject(message);
                output.flush();
                return;
            } catch (NullPointerException | IOException e) {
                connector.connect();
                if (++count == maxTries) throw e;
            }
        }
    }

    public Message receive() throws IOException {
        int count = 0;
        int maxTries = 10;
        while (true) {
            try {
                ObjectInputStream input = connector.getInput();
                return (Message) input.readObject();
            } catch (NullPointerException | IOException | ClassNotFoundException e) {
                connector.connect();
                if (++count == maxTries) try {
                    throw e;
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        }
    }
}
