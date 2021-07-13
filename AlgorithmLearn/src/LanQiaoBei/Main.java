package LanQiaoBei;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
    /**
     * 知识点：
     * 1.质数（素数）  质数是指在大于1的自然数中，除了1和它本身以外不再有其他因数的自然数
     * 2.约数  能整除别人
     * 3.自然数 包括0
     * 4.偶数奇数  0是特殊偶数
     * 5.闰年 阳历有闰日（即2月有29日）能被4整除不能被100整除或者能被400整除  2月份29天
     * 6.每个月的天数 1 3 5 7 8 10 腊 （31天） 剩余除了2月都是30天
     * 7.如何向上取整 5/2=3  (5+2-1)/2=3
     * 8.Arrays.copyOf(array,index);将array 0-index-1的复制出来
     * 9.类实现comparator<Type>接口 实现compareTo方法 return 1表示要交换两个值 -1表示不交换 升序可以写成o1-o2   反之o2-o1
     * 10.new Comparator<String>() {
     *       @Override
     *      public int compare(String o1, String o2) {
     *
     *           return o1.compareTo(o2);
     *      }
     * 11. s.charAt(i)-'0'保证字符到整型的正常
     * 12.Character.isDigit
     * 13.Character.isUpperCase Character.toLowerCase
     * 14.map.getOrDefault没有对应key就默认
     * 15.Arrays.fill数组填充
     * 16."".contains()方法能看是否包含某个子串
     * 17.str.split("\\s+|\\.")
     * 18.(int)Math.random()*(right-left+1)+left
     */
    public static void main(String[] args) {
      /* char a='1';
       int s=a-'0';
        System.out.println(s);
        System.out.println(lcm(4,6));*/
      /*int i=1;
      while ((--i)!=0){
          System.out.println(i);
      }*/
      String s="abc ac ac s. aa";
      String strs[]=s.split("\\s+|\\.");
        System.out.println("a");
    }

    /**
     * subString 的使用
     * 截取到第二个参数的下标前面
     */
    public static void subStringTest(){
        String s="1234";
        System.out.println(s.substring(0,3));
        System.out.println(""+100+"10"+"1");
    }

    /**
     * 求最大公约数
     * 辗转相除法
     */
    public static int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }

    /**
     * 最小公倍数
     * lcm(a,b)=|a,b|/gcd(a,b);
     */
    public static int lcm(int a,int b){
        return a*b/gcd(a,b);
    }

    /**
     * 进制转换
     */
    public static void numChange(int num){
        System.out.println(num + "的十进制到二进制:" + Integer.toBinaryString(num));
        System.out.println(num + "的十进制到八进制:" + Integer.toOctalString(num));
        System.out.println(num+ "的十进制到十六进制:" + Integer.toHexString(num));
        // 十进制到其它进制
        // xxx的xx进制是xxx
        System.out.println(Integer.toString(100, 10));
        System.out.println(Integer.toString(100, 2));
        System.out.println("-----------------------");

        // 其它进制到十进制
        // xx进制的xx是xx
        System.out.println(Integer.parseInt("100", 10));
        System.out.println(Integer.parseInt("100", 2));
        System.out.println(Integer.parseInt("100", 8));
        System.out.println(Integer.parseInt("100", 16));
        System.out.println(Integer.parseInt("100", 23));
    }

    /**
     * 读取文件
     */
    public void readFile() throws Exception{
        BufferedReader bf=new BufferedReader(new InputStreamReader(new FileInputStream("ans.txt")));
        bf.readLine();
        //没有的时候会返回null
    }
}
