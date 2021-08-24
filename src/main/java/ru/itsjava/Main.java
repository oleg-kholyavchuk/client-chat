package ru.itsjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {

    public final static int PORT = 8081;
    public final static String HOST = "localhost";

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(HOST, PORT);

        if (socket.isConnected()){
            PrintWriter serverWrite = new PrintWriter(socket.getOutputStream());
            serverWrite.println("Hi from client");
            serverWrite.flush();

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true){
                System.out.println(serverReader.readLine());
            }
        }



    }
}
