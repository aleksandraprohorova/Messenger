package com.db.edu.connection;

import com.db.edu.controller.Controller;
import com.db.edu.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Skeleton implements Runnable {

    private static Logger log = LoggerFactory.getLogger(Skeleton.class);

    private final Socket connection;
    private final Controller controller;

    private ObjectInputStream input;
    private ObjectOutputStream output;

    public Skeleton(Socket connection, Controller controller) {
        this.connection = connection;
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            input = new ObjectInputStream((connection.getInputStream()));
            output = new ObjectOutputStream((connection.getOutputStream()));
            log.info("Client connected at " + Thread.currentThread().getName());
        } catch (IOException e) {
            log.error("Can not create Input/Output streams", e);
        }
        try {
            while (true) {
                Message message = receive();
                controller.execute(message);
                send(message);
            }
        } catch (IOException e) {
            log.info("ClientSocket stop working at " + Thread.currentThread().getName());
        } finally {
            try {
                input.close();
                output.close();
                connection.close();
            } catch (IOException e) {
                log.error("ClientSocket can not close", e);
            }
        }
    }

    public void send(Message message) throws IOException {
        output.writeObject(message);
        output.flush();
    }

    public Message receive() throws IOException {
        try {
            Message message = (Message) input.readObject();
            log.info("Got message {}", message);
            return message;
        } catch (ClassNotFoundException e) {
            log.error("Can not read Message from ObjectStream", e);
        }
        return null;
    }
}
