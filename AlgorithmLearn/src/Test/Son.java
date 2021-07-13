package Test;

import java.util.HashMap;
import java.util.Map;

public class Son extends Father {
    public void test(Map map){
        System.out.println("子类被执行。。。。。");
    }

    public static void main(String[] args) {
        Son son=new Son();
        son.test(new HashMap());
    }
}
