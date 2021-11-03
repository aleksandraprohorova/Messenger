package com.db.edu.connection;

import com.db.edu.controller.Controller;
import com.db.edu.exception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Acceptor {
    private static Logger log = LoggerFactory.getLogger(Skeleton.class);

    private final int port;
    private final int clientsNumber;

    private ServerSocket serverSocket;

    private final Controller controller;


    public Acceptor(Controller controller, int port, int clientsNumber) {
        this.controller = controller;
        this.port = port;
        this.clientsNumber = clientsNumber;
    }

    public void accept() {
        try {
            serverSocket = new ServerSocket(port);
            log.info("Created ServerSocket");
        } catch (IOException e) {
            String errorMessage = "Failed to create new ServerSocket";
            log.error(errorMessage, e);
            throw new ServerException(errorMessage);
        }
        final ExecutorService pool = Executors.newFixedThreadPool(clientsNumber);
        Socket connection = null;
        try {
            while (true) {
                connection = serverSocket.accept();
                log.info("Accepted new connection");
                Skeleton client = new Skeleton(connection, controller);
                pool.execute(client);
            }
        } catch (IOException e) {
            String errorMessage = "Failed to create new ClientSocket";
            log.error(errorMessage, e);
            throw new ServerException(errorMessage);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    log.info("Closed connection.");
                }
                if (serverSocket != null){
                    serverSocket.close();
                    log.info("Closed socket.");
                }
                pool.shutdown();
            } catch (IOException e) {
                log.error("Failed to close ServerSocket", e);
            }
        }

    }

    public int getPort() {
        return port;
    }
}
