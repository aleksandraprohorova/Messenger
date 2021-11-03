package com.db.edu;

import com.db.edu.connection.Acceptor;
import com.db.edu.controller.MessageController;

public class Server {
    private static final int PORT = 9999;
    private static final int NUMBER_OF_POSSIBLE_CLIENT_CONNECTIONS = 1000;
    static public void main(String[] args) {
           Acceptor acceptor = new Acceptor(new MessageController(), PORT, NUMBER_OF_POSSIBLE_CLIENT_CONNECTIONS);
           acceptor.accept();
    }
}
