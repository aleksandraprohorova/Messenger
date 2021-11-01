package com.db.edu.connection;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class ObjectConnection implements Connection {

    protected ObjectInputStream input;
    protected ObjectOutputStream output;

    public ObjectOutputStream getOutput() {
        return output;
    }

    public ObjectInputStream getInput() {
        return input;
    }

}
