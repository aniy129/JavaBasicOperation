package csk.bases.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleIO {
    public static void main(String[] args) throws IOException {
        readChar();
        readStr();
        read();
    }

    //@Test
    public static void readChar() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        char c;
        System.out.println("输入字符, 按下 'q' 键退出。");
        do {
            c = (char) bufferedReader.read();
            System.out.println(c);
        } while (c != 'q');
    }

    public static void readStr() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String strings;
        System.out.println("Enter lines of text.");
        System.out.println("Enter 'end' to quit.");
        do {
            strings = bufferedReader.readLine();
            System.out.println(strings);
        } while (!strings.equals("end"));
    }

    /**
     * @Author: Aniy on 2018/9/30 14:24
     * @methodParameters: []
     * @methodReturnType: void
     * @Description: 使用Scanner读取输入
     */
    public static void read() {
        System.out.println("Please enter a strings:");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            String str = scanner.next();
            System.out.println(str);
        }
        scanner.close();
    }
}
