package LanQiaoBei;

import java.util.HashSet;
import java.util.Set;

public class lan_10_b {
    /**
     * 一个字符串的非空子串是指字符串中长度至少为1 的连续的一段字符组成
     * 的串。例如，字符串aaab 有非空子串a, b, aa, ab, aaa, aab, aaab，一共7 个。
     * 注意在计算时，只算本质不同的串的个数。
     * 请问，字符串0100110001010001 有多少个不同的非空子串？
     *
     * 这是一道结果填空的题，你只需要算出结果后提交即可。本题的结果为一
     * 个整数，在提交答案时只填写这个整数，填写多余的内容将无法得分。
     * 注意边界
     */
    public static int getNums(String s){
        Set<String> exist=new HashSet<>();
        int num=0;
        for (int i=1;i<=s.length();i++){
            for (int j=0;j<=s.length()-i;j++){
                String cur=s.substring(j,j+i);//subString 截取endindex前面的
                if (!exist.contains(cur)){
                    num++;
                    exist.add(cur);
                }
            }
        }
        return num;
    }


    public static void main(String[] args) {
        String a="0100110001010001";
        System.out.println(getNums(a));
    }
}
