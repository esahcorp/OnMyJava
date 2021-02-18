package com.kuma.standard.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟服务端
 *
 * @author kuma 2021-02-12
 */
public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8080)) {
            // 阻塞等待
            System.out.println("Waiting for accept...");
            try (Socket socket = server.accept()) {
                // Closing this socket will also close the socket's InputStream and OutputStream.
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                    try (PrintWriter pw = new PrintWriter(out, true)) {
                        pw.println("Hello! Enter BYE to exit.");
                        for (String line; (line = reader.readLine()) != null; ) {
                            System.out.println("Received from client: " + line);

                            // 返回数据
                            pw.println("Echo: " + line);
                            if ("BYE".equals(line.trim())) {
                                break;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            // new ss
            e.printStackTrace();
        }
    }
}
