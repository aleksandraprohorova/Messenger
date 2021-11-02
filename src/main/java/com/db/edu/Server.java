package com.db.edu;

import com.db.edu.connection.Proxy;
import com.db.edu.controller.Controller;
import com.db.edu.controller.MessageController;
import com.db.edu.message.Message;

import java.io.IOException;

public class Server {
    static public void main(String[] args) {
           Acceptor acceptor = new Acceptor(new MessageController(), 9999);
           acceptor.accept();
    }
}
