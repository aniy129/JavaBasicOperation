package csk.bases.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket sc = new ServerSocket(8990);
        System.out.println("打开端口8990");
        Socket accept;
        while (true) {
            accept = sc.accept();
            final Socket finalAccept = accept;
            new Thread(() -> {
                BufferedReader bufferedReader = null;
                PrintWriter printWriter = null;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(finalAccept.getInputStream()));
                    printWriter = new PrintWriter(finalAccept.getOutputStream(), true);
                    while (true) {
                        String text = bufferedReader.readLine();
                        if (text == null) {
                            finalAccept.close();
                            break;
                        } else {
                            System.out.println(text);
                            printWriter.println("服务器发送：" + text);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    if (printWriter != null) {
                        printWriter.close();
                    }
                    if (finalAccept != null) {
                        try {
                            finalAccept.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
