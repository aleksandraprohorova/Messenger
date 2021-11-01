package com.db.edu;

import java.io.*;

public class Messager {
    String line;
    DataOutputStream output;
    DataInputStream input;
    boolean flag = true;

    Messager(String line, DataInputStream input, DataOutputStream output) {
        this.line = line;
        this.input = input;
        this.output = output;
    }

    public void adress() throws IOException {
        while (flag) {
            output.writeUTF(line);
            output.flush();
            System.out.print(input.readUTF());
        }
    }
}
