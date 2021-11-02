package com.db.edu.connection;

import com.db.edu.controller.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Acceptor {
    public int port;

    private final ExecutorService pool = Executors.newFixedThreadPool(10);
    private ServerSocket serversocket;

    private List<Sceleton> clients = new ArrayList<>();

    private Controller controller;

    public Acceptor(Controller controller, int port) {
        this.controller = controller;
        this.port = port;
    }

    public void accept() {

        try {
            if (serversocket != null) serversocket.close();
            serversocket = new ServerSocket(port);
            System.out.println("Created ServerSocket");

            while (true) {
                final Socket connection = serversocket.accept();
                System.out.println("Accepted new connection");
                Sceleton client = new Sceleton(connection, controller, clients);
                clients.add(client);
                pool.execute(client);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
