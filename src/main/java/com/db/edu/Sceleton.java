package com.db.edu;

import com.db.edu.controller.Controller;
import com.db.edu.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sceleton implements Runnable {

    final private Socket connection;

    protected ObjectInputStream input;
    protected ObjectOutputStream output;
    private Controller controller;

    public Sceleton(Socket connection, Controller controller) {
        this.connection = connection;
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            input = new ObjectInputStream((connection.getInputStream()));
            output = new ObjectOutputStream((connection.getOutputStream()));
            System.out.println("Streams created for " + Thread.currentThread().getName());

            while (true) {
                Message message = receive();
                controller.execute(message);
                send(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Message message) throws IOException {
        output.writeObject(message);
        output.flush();
    }

    public Message receive() throws IOException {
        try {
            return (Message) input.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
