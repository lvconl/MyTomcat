package edu.mytomcatv1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lvconl
 * 类说明：第一版本tomcat服务器
 * */
public class Server {
    private static final String WEB_ROOT = System.getProperty("user.dir") + "\\" + "WebContent";
    private static String url = "";
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            serverSocket = new ServerSocket(8080);
            while (true) {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                parse(inputStream);
                makeResponse(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void parse(InputStream inputStream) throws IOException {
        StringBuilder content = new StringBuilder(2048);
        byte[] buffer = new byte[2048];
        int i = -1;
        i = inputStream.read(buffer);
        for (int j = 0; j < i; j++) {
            content.append((char)buffer[j]);
        }
        parseUrl(content.toString());
    }
    private static void parseUrl(String content) {
        int indexOne, indexTwo;
        indexOne = content.indexOf(" ");
        if (indexOne != -1) {
            indexTwo = content.indexOf(" ", indexOne + 1);
            if (indexTwo > indexOne) {
                url = content.substring(indexOne + 2, indexTwo);
            }
        }
    }
    private static void makeResponse(OutputStream outputStream) {
        byte[] bytes = new byte[2048];
        FileInputStream fileInputStream = null;
        try {
            File file = new File(WEB_ROOT, url);
            if (file.exists()) {
                outputStream.write("HTTP/1.1 200 OK\n".getBytes());
                outputStream.write("Server:apache-Coyote/1.1\n".getBytes());
                outputStream.write("Content-Type:text/html;charset=utf-8\n".getBytes());
                outputStream.write("\n".getBytes());
                fileInputStream = new FileInputStream(file);
                int ch = fileInputStream.read(bytes);
                while (ch != -1) {
                    outputStream.write(bytes, 0 , ch);
                    ch = fileInputStream.read(bytes);
                }
            } else {
                outputStream.write("HTTP/1.1 404 NOT FOUND\n".getBytes());
                outputStream.write("Server:Apache-Coyote/1.1\n".getBytes());
                outputStream.write("Content-Type:text/html;charset=utf-8\n".getBytes());
                outputStream.write("\n".getBytes());
                String errorMessage = "404 Not Found";
                outputStream.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
