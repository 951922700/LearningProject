package LanQiaoBei;

import java.util.Scanner;
import java.util.TreeSet;

public class lanqiaobei_date {
    /**
     * 日期问题
     * 时间限制：1.0s 内存限制：256.0MB
     * 问题描述
     * 　　小明正在整理一批历史文献。这些历史文献中出现了很多日期。小明知道这些日期都在1960年1月1日至2059年12月31日。令小明头疼的是，这些日期采用的格式非常不统一，有采用年/月/日的，有采用月/日/年的，还有采用日/月/年的。更加麻烦的是，年份也都省略了前两位，使得文献上的一个日期，存在很多可能的日期与其对应。
     *
     * 比如02/03/04，可能是2002年03月04日、2004年02月03日或2004年03月02日。
     *
     * 给出一个文献上的日期，你能帮助小明判断有哪些可能的日期对其对应吗？
     * 输入格式
     * 　　一个日期，格式是"AA/BB/CC"。 (0 <= A, B, C <= 9)
     * 输出格式
     * 　　输出若干个不相同的日期，每个日期一行，格式是"yyyy-MM-dd"。多个日期按从早到晚排列。
     * 样例输入
     * 02/03/04
     * 样例输出
     * 2002-03-04
     * 2004-02-03
     * 2004-03-02
     */

    TreeSet<Integer> set=new TreeSet<>();
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        String date=scan.nextLine();
        String dates[]=date.split("/");
    }

    public void judgeDate(String yy,String mm,String dd){
        int y=Integer.parseInt(yy);
        int m=Integer.parseInt(mm);
        int d=Integer.parseInt(dd);
        if (y>=0&&y<=59){
            y+=2000;
        }else{
            y+=1900;
        }

        //是闰年 2月  有29日
        if (isR(y)&&m==2&&d>=0&&d<=29);
            //set.add()
    }

    /**
     * 判断是否是闰年
     */
    public boolean isR(int yy){
        return (yy%4==0&&yy%100!=0)||yy%400==0;
    }
}
