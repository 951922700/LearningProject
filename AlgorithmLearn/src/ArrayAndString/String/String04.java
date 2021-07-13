package ArrayAndString.String;

public class String04 {
    /**
     * 实现 strStr()
     * 实现 strStr() 函数。
     *
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     *
     * 示例 1:
     *
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * 示例 2:
     *
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     * 说明:
     *
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     *
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     *
     *
     * Java
     *
     */

    //语言特性
/*    public int strStr(ArrayAndString.String haystack, ArrayAndString.String needle) {
        if (needle.equals("")) return 0;
        int i = haystack.indexOf(needle);
        return i;
    }*/

    //滑动窗口
/*    public int strStr(ArrayAndString.String haystack, ArrayAndString.String needle) {
       int L=needle.length(),n=haystack.length();

       for (int start=0;start<n-L+1;start++){
           //needle为空的时候  substring(0,0)也返回空  这个时候判断通过
           if (haystack.substring(start,start+L).equals(needle))
               return start;
       }
       return -1;
    }*/

    //双指针
    public int strStr(String haystack, String needle) {
         int L=needle.length(),n=haystack.length();
         if (L==0) return 0;

         int pn=0;
         while(pn<n-L+1){
             //这一步可能让pn直接超越到达n-L+1
             while(pn<n-L+1&&haystack.charAt(pn)!=needle.charAt(0)) pn++;

             int  pL=0;
             int currLen=0;
             //所以这里必须控制pn<n
             while(pL<L&&pn<n&&haystack.charAt(pn)==needle.charAt(pL)){
                 pL++;
                 pn++;
                 currLen++;
             }
             if (currLen==L) return pn-L;

             pn=pn-currLen+1;
         }
         return -1;
    }
    public static void main(String[] args) {
        String04 str=new String04();
        System.out.println(str.strStr("asd","e"));
    }
}
