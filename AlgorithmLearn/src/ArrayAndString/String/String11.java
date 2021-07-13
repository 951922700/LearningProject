package ArrayAndString.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 反转字符串中的单词 III
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 *  
 *
 * 示例：
 *
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 *
 */
public class String11 {
    public String reverseWords(String s) {
        String[] list=s.split(" ");
        StringBuilder[] sb=new StringBuilder[list.length];
        for (int i=0;i<sb.length;i++)
            sb[i]=new StringBuilder(list[i]);
        //单个字符翻转
        for (int i=0;i<sb.length;i++)
            sb[i]=sb[i].reverse();
        return String.join(" ",sb);
    }

    public static void main(String[] args) {
        String11 string11=new String11();
        System.out.println(string11.reverseWords("Let's take LeetCode contest"));
    }
}
