package csk.bases.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 8990);
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            String text = "发条消息玩玩……\r\nhello world";
            System.out.println("我的发送数据；"+text);
            printWriter.println(text);
            socket.getSendBufferSize();
            String readLine = bufferedReader.readLine();
            System.out.println("接受数据："+readLine);

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
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        Thread.sleep(5000);
        socket.close();
    }
}
