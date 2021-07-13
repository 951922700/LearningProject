package ArrayAndString.String;

public class String02 {

    /**
     * 最长回文子串
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * 示例 1：
     *
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     *
     * 输入: "cbbd"
     * 输出: "bb"
     *
     */


    //1.暴力匹配
    /*public ArrayAndString.String longestPalindrome(ArrayAndString.String s) {
        int len=s.length();
        //如果为空或者没有长度为1返回自身
        if (len<2) return s;

        //s.charAt()会首先检验越界，所以先把字符串转换成字符数组
        char[] charArray=s.toCharArray();

        int maxLen=1;//回文串最大长度
        int begin=0;//起始位置

        for (int i=0;i<len-1;i++){//i只能到倒数第二个
            for (int j=i+1;j<len;j++){
                if(j-i+1>maxLen&&validPalindrome(charArray,i,j)){
                    //当前串长度大于最大串长度且为回文串更新maxlen以及初始位置
                    maxLen=j-i+1;
                    begin=i;
                }
            }
        }
        return s.substring(begin,begin+maxLen);
    }

    public boolean validPalindrome(char [] charArray,int left,int right){
        //当字符串长度为奇数时  中间那个就不用判断了  也就是left==right那个不用判断
        while (left<right){
            if (charArray[left]!=charArray[right]) return false;
            left++;
            right--;
        }
        return true;
    }*/

    //动态规划
/*    public ArrayAndString.String longestPalindrome(ArrayAndString.String s) {
        int len=s.length();
        if(len<2) return s;

        int maxLen=1;
        int begin=0;

        char[] charArray=s.toCharArray();

        boolean[][] dp=new boolean[len][len];
        //一列一列进行循环
        for (int j=1;j<len;j++){
            for (int i=0;i<j;i++){
                if (charArray[i]!=charArray[j]) dp[i][j]=false;
                else{
                    if (j-i<3) dp[i][j]=true;//j-i<3  是j-1-(i+1)+1<2  当只有两个长度或者三个长度的子串的时候会满足
                    else dp[i][j]=dp[i+1][j-1];//使得这里具有无后效性
                }

                if (dp[i][j]&&j-i+1>maxLen){
                    maxLen=j-i+1;
                    begin=i;
                }
            }
        }
        return s.substring(begin,begin+maxLen);
    }*/


    //中心扩散  时间n平方  空间1
    public String longestPalindrome(String s) {
        int len=s.length();
        if (len<2) return s;

        int maxLen=1;
        String res=s.substring(0,1);//一个字符也是一个回文串

        for (int i=0;i<len-1;i++){
            String str1=centerSpread(s,i,i);
            String str2=centerSpread(s,i,i+1);
            String temp=str1.length()>str2.length()?str1:str2;
            if (temp.length()>maxLen){
                maxLen=temp.length();
                res=temp;
            }
        }
        return res;
    }
    private String centerSpread(String s, int left, int right) {
        //left==right 以字符为中点
        //right=left+1  以间隙为中点   排除首尾
        int len=s.length();
        while(left>=0&&right<len){
            if (s.charAt(left)==s.charAt(right)){
                left--;
                right++;
            }else break;
        }
        return s.substring(left+1,right);//循环出来时处于不等于的位置  所以i j位置不可取  往里缩
    }
    public static void main(String[] args) {
        String02 string02=new String02();
        System.out.println(string02.longestPalindrome("bb"));
    }
}
