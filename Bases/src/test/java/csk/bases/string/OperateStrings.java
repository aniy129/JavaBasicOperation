package csk.bases.string;

import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperateStrings {

    /**
     * @Author: Aniy on 2018/9/29 9:41
     * @methodParameters: []
     * @methodReturnType: void
     * @Description: 正则表达式
     */
    @Test
    public  void regex() {
        String pattern = "[\\s\\S]+?(\\.|\\?)";
        URL url = this.getClass().getClassLoader().getResource("text.txt");
        System.out.println(url.getFile());
        String content = readToString(url.getFile());
        Pattern pattern1 = Pattern.compile(pattern);
        Matcher matcher = pattern1.matcher(content);
//        boolean matches = matcher.matches();
//        System.out.println(matches);
        while (matcher.find()) {
            System.out.println(matcher.group().replace("\r\n", " "));
        }

    }

    public String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
}
