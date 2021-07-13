package ArrayAndString.String;

import java.util.*;

public class String03 {
    //疑问点有多个空格如何分离字符
    //语言特性
/*    public ArrayAndString.String reverseWords(ArrayAndString.String s) {
        //删除头尾空白符号
        s=s.trim();
        List<ArrayAndString.String> list= Arrays.asList(s.split("\\s+"));//正则匹配多个空格
        Collections.reverse(list);
        return ArrayAndString.String.join(" ",list);
    }*/

    //双端队列
    public String reverseWords(String s) {
        int len=s.length();
        int left=0,right=len-1;

        while(left<=right&&s.charAt(left)==' ') left++;


        while(left<=right&&s.charAt(right)==' ') right--;

        Deque<String> d=new ArrayDeque<>();
        StringBuilder word=new StringBuilder();

        while(left<=right){
            char c=s.charAt(left);
            //有单词且到了空格处   如果碰到连续空格就单词长度为0  两个if都不满足跳到下一次循环
            if(word.length()!=0&&c==' '){
                d.offerFirst(word.toString());
                word.setLength(0);
            }else if(c!=' '){
                //不等于空符号 加进builder
                word.append(c);
            }
            left++;
        }
        d.offerFirst(word.toString());
        return String.join(" ",d);
    }

    public static void main(String[] args) {

    }
}
