package com.example.myapplication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPHelper {
    private String hostname;
    private int port;

    public TCPHelper(String hostname, int port) {
        this.port = port;
        this.hostname = hostname;
    }

    public String sendAndRetrieve(String message) throws IOException {
        Socket clientSocket = new Socket(hostname, port);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // communication
        outToServer.writeBytes(message + '\n');
        String result = inFromServer.readLine();

        clientSocket.close();

        return result;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
