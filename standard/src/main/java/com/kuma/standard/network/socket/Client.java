package com.kuma.standard.network.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author kuma 2021-02-12
 */
public class Client {
    public static void main(String[] args) {
        String msg = "Client Data";
        try (Socket socket = new Socket("localhost", 8080);
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            pw.println(msg);
            pw.flush();
            for (String line; (line = reader.readLine()) != null; ) {
                System.out.println("Result from Server: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
