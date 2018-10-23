package csk.proxy.cglib;

public class GeneralClass {
    public String Say(String str){
        System.out.println( "hello "+str);
        return "cglib hello "+str;
    }
}
