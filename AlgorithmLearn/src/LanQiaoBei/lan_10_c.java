package LanQiaoBei;

public class lan_10_c {
    /**
     * 给定数列 1, 1, 1, 3, 5, 9, 17, …，
     * 从第 4 项开始，每项都是前 3 项的和。求 第 20190324 项的最后 4 位数字
     *
     * 取余操作:%1000 取后三位 %10000 后四位
     * 整除操作:/1000%10 取千位数 /100%10 取百位  整除之后的个位数就是对应位数
     */
    //会溢出
    public static String getLastNum(){
        long num[]=new long[20190324];
        num[0]=1;
        num[1]=1;
        num[2]=1;
        for (int i=3;i<num.length;i++){
            num[i]=num[i-1]+num[i-2]+num[i-3];
        }
        String s=Long.toString(num[20190323]);
        int length=s.length();
        return s.substring(length-4,length);
    }

    public static String getLast(){
        long num[]=new long[4];
        num[0]=1;
        num[1]=1;
        num[2]=1;
        //因为会溢出 我们只要后四位  每次相加后取余获得后四位
        for (int i=3;i<20190324;i++){
            num[3]=num[0]+num[1]+num[2];
            num[3]=num[3]%10000;

            num[0]=num[1];
            num[1]=num[2];
            num[2]=num[3];
        }
        return Long.toString(num[3]);
    }

    public static void main(String[] args) {
        System.out.println(getLast());
    }
}
