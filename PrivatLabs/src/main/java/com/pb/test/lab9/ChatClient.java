package com.pb.test.lab9;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ChatClient {

    JFrame frame;
    JTextArea incoming;
    JTextField outgoing;
    JTextField login;
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;
    String currentLogin;

    public static void main(String[] args) {
        new ChatClient().login();
    }

    public void login() {
        frame = new JFrame("Chat Client - Введите логин");
        login = new JTextField(30);
        JButton loginButton = new JButton("Войти");
        JPanel mainPanel = new JPanel();
        mainPanel.add(login);
        mainPanel.add(loginButton);
        loginButton.addActionListener(new LoginButtonListener());
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(600, 200);
        frame.setVisible(true);

    }

    public void go() {
        frame = new JFrame("Chat Client - " + currentLogin);
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(15, 50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(true);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Отправить");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(600, 400);
        frame.setVisible(true);

    }

    private void setUpNetworking(String login) {
        try {
            sock = new Socket("127.0.0.1", 5000);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("Канал передачи данных создан");
            writer.println(login);
            writer.flush();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Ошибка! Серверное приложение не запущено!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public class SendButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            writer.println(currentLogin + ": " + outgoing.getText());
            writer.flush();
            outgoing.setText("");
            outgoing.requestFocus();

        }
    }

    public class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            currentLogin = login.getText();
            if (currentLogin.equals("")){
                JOptionPane.showMessageDialog(frame, "Логин не указан!", "!!! ", JOptionPane.WARNING_MESSAGE);
                return;
            }
            setUpNetworking(login.getText());
            String massage;
            try {
                massage = reader.readLine();
                System.out.println("read " + massage);
                if (massage.indexOf("Error") > -1) {
                    JOptionPane.showMessageDialog(frame, massage, "!!! ", JOptionPane.WARNING_MESSAGE);
                    reader.close();
                    writer.close();
                    sock.close();
                    return;
                }

            } catch (IOException ex) {
                Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.dispose();
            go();

        }
    }

    public class IncomingReader implements Runnable {

        public void run() {
            String massage;
            try {
                while ((massage = reader.readLine()) != null) {
                    System.out.println("read " + massage);
                    incoming.append(massage + "\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
