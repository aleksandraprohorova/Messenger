package com.db.edu.connection;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface Connection {

    public ObjectOutputStream getOutput();
    public ObjectInputStream getInput();

    void connect();
}
