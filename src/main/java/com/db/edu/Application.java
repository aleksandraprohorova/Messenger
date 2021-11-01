package com.db.edu;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;

public class Application {
    public static void main(String[] argc) {
        try{
            final Socket socket = new Socket("127.0.0.1", 250);
            final DataInputStream input = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            final DataOutputStream out = new DataOutputStream(
                    new BufferedOutputStream(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

          final Messager messageTrade = new Messager (reader.readLine(), input, out);
          messageTrade.adress();
        } catch (IOException exception){
            exception.getCause();
        }
    }
}
