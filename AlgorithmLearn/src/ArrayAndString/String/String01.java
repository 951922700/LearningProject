package ArrayAndString.String;

public class String01 {

    /**
     * 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     * 示例 1:
     *
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * 示例 2:
     *
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     * 说明:
     *
     * 所有输入只包含小写字母 a-z 。
     *
     */

    //1.横向比较
    public String longestCommonPrefix(String[] strs) {
        if (strs==null||strs.length==0)
            return "";
        String prefix=strs[0];
        for (int i=1;i<strs.length;i++){
            prefix=longestCommonPrefix(prefix,strs[i]);
            if (prefix.length()==0) break;
        }

        return  prefix;
    }

    //返回两个字符串的公共前缀
    public String longestCommonPrefix(String str1,String str2) {
        int length=Math.min(str1.length(),str2.length());

        //公共位置索引
        int index=0;

        //相同索引加加
        while(index<length&&str1.charAt(index)==str2.charAt(index)){
            index++;
        }

        return str1.substring(0,index);//index所在位置不被剪切
    }

    //纵向比较
    public String longestCommonPrefix2(String[] strs) {
        if (strs==null||strs.length==0)
            return "";

        int length=strs[0].length();//第一个字符长度
        int count=strs.length;//字符数

        for (int i=0;i<length;i++){
            char c=strs[0].charAt(i);
            //当只有一个数组的时候第二个for循环不执行
            for (int j=1;j<count;j++){
                if(i==strs[j].length()||c!=strs[j].charAt(i))
                    return strs[0].substring(0,i);
            }
        }
        //只有一个
        return strs[0];
    }


    public static void main(String[] args) {
        String a="abc";
        System.out.println(a.substring(0,2));
    }
}
