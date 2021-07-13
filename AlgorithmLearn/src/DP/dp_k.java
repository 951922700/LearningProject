package DP;

public class dp_k {
    /**
     * 如果一个自然数N的K进制表示中任意的相邻的两位都不是相邻的数字，那么我们就说这个数是K好数。
     * 求L位K进制数中K好数的数目。例如K = 4，L = 2的时候，所有K好数为11、13、20、22、30、31、33 共7个。由于这个数目很大，请你输出它对1000000007取模后的值。
     *
     * 输入格式：输入包含两个正整数，K和L。
     *
     * 输入样例：4 2
     *
     * 输出样例:7
     *
     * 数据规模与约定:对于30%的数据，KL <= 106；对于50%的数据，K <= 16， L <= 10；对于100%的数据，1 <= K,L <= 100。
     */

    public static void main(String[] args) {
        int K=4,L=2;
        int dp[][]=new int[L+1][K+1];//dp[i][j]  i长度 j开头
        for (int i=0;i<K;i++){
            //长度为1 i开头  个位是0也算一个k好数
            dp[1][i]=1;
        }
        //从长度为2开始遍历
        for(int i=2;i<=L;i++){
            for(int j=0;j<K;j++){
                for(int m=0;m<K;m++){
                    //当j数字和i-1位的首数字m不相邻时
                    //便可以将 j数字和该i-1位的数字组成新的i位k好数
                    //该i位的k好数的个数增加了该i-1位k好数的个数
                    if(m!=j-1&&m!=j+1){
                        dp[i][j]+=dp[i-1][m];
                        //当数量超过就取余
                        if(dp[i][j]>=1000000007){
                            dp[i][j]%=1000000007;
                        }

                    }
                }
            }
        }

        //求和
        int sum=0;
        for (int i=1;i<K;i++){
            //将K开头的所有L长的次数总和
            sum+=dp[L][i];
            sum%=1000000007;
        }
        System.out.println(sum);
    }
}
