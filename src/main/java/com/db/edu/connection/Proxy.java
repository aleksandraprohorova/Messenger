package com.db.edu.connection;

import com.db.edu.exception.ServerException;
import com.db.edu.message.Message;
import com.db.edu.connection.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Proxy {
    private final Connection connector;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    private static Logger log = LoggerFactory.getLogger(Skeleton.class);

    public Proxy(Connection connector) {
        this.connector = connector;
        connector.connect();
    }

    public void send(Message message) throws ServerException {
        int count = 0;
        int maxTries = 3;
        while (true) {
            try {
                output = connector.getOutput();
                output.writeObject(message);
                output.flush();
                return;
            } catch (NullPointerException | IOException e) {
                log.error("Try to connect again");
                connector.connect();
                if (++count == maxTries) {
                    throw new ServerException("Connection timeout.");
                };
            }
        }
    }

    public Message receive() throws ServerException {
        int count = 0;
        int maxTries = 3;
        while (true) {
            try {
                ObjectInputStream input = connector.getInput();
                return (Message) input.readObject();
            } catch (NullPointerException | IOException | ClassNotFoundException e) {
                log.error(e.getMessage());
                connector.connect();
                if (++count == maxTries) {
                    throw new ServerException("Connection timeout.");
                }
            }
        }
    }
}
