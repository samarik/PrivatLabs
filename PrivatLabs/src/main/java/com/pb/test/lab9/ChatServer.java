package com.pb.test.lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {

    HashMap clientOutputStreams;

    public class ClientHandler implements Runnable {

        BufferedReader reader;
        Socket sock;

        public ClientHandler(Socket clientSocket) {
            try {
                sock = clientSocket;
                InputStreamReader isReader;
                isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        public void run() {
            String message;
            try {
                String login = reader.readLine();
                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                if (clientOutputStreams.containsKey(login)) {
                    writer.print("Error!!! пользователь с логином " + login + " уже в сети !!!");
                    writer.flush();
                    writer.close();
                    reader.close();
                    sock.close();
                    return;
                }
                clientOutputStreams.put(login, writer);
                tellEveryone(login + " в сети !!!");
                while ((message = reader.readLine()) != null) {
                    System.out.println("read" + message);
                    tellEveryone(message);
                }
            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        new ChatServer().go();

    }

    public void go() {
        clientOutputStreams = new HashMap();
        try {
            ServerSocket serverSock = new ServerSocket(5000);
            while (true) {
                Socket clientSocket = serverSock.accept();
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("Установлено соединение !");
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tellEveryone(String message) {
        Iterator it = clientOutputStreams.values().iterator();
        while (it.hasNext()) {
            PrintWriter writer = (PrintWriter) it.next();
            writer.println(message);
            writer.flush();

        }
    }

}
