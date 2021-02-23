package com.kuma.standard.network.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 用多线程支持并发请求
 *
 * @author kuma 2021-02-18
 */
public class ThreadEchoServer {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8080)) {
            System.out.println("Waiting for accept...");
            int count = 1;
            while (true) {
                // 不能用 try-with-resource，否则子线程执行时 Socket 已经关闭了
                Socket incoming = server.accept();
                System.out.println("Receiving a request: " + count);
                ThreadEchoHandler handler = new ThreadEchoHandler(incoming, count);
                new Thread(handler).start();
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadEchoHandler implements Runnable {

    private final Socket incoming;
    private final int number;

    ThreadEchoHandler(Socket incoming, int number) {
        this.incoming = incoming;
        this.number = number;
    }

    @Override
    public void run() {
        try (InputStream in = incoming.getInputStream();
             OutputStream out = incoming.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             PrintWriter writer = new PrintWriter(out, true)) {
            writer.println("Hello! Enter BYE to exit.");
            for (String line; (line = reader.readLine()) != null; ) {
                System.out.println("Receive from client: " + line);
                writer.println("Echo: " + line);
                if ("BYE".equals(line.trim())) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
