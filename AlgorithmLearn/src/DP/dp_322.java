package DP;

import java.util.Arrays;

public class dp_322 {
    /**
     * 322. 零钱兑换
     * 给定不同面额的硬币 coins 和一个总金额 amount。
     * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     *
     * 你可以认为每种硬币的数量是无限的。
     */
    public int coinChange(int[] coins, int amount) {
        int len=coins.length;
        int max=amount+1;
       /* if (amount==0)
            return 0;*/
        int dp[]=new int[amount+1];
        Arrays.fill(dp,max);
        dp[0]=0;//数值为0的时候 可以不放

        //自底向上
        for (int i=1;i<=amount;i++) {
            for (int j=0;j<len;j++){
                //剩余容量大于硬币容量
                if (i>=coins[j])
                    dp[i]=Math.min(dp[i],dp[i-coins[j]]+1);
            }
        }

        //当全部为硬币1的时候  最大值就是amount个1  所以大于amount就是不可能的
        return dp[amount]>amount?-1:dp[amount];
    }

    /**
     *正常思路
     * 前面错误的点在于  自己给dp[0]所在的行进行了初始化  其实没有必要
     * 因为我们每次都是可以用之前走过的来  所以我们也是从dp[0]开始进行遍历
     * 只需要设置第一个为0  其他为一个最大值即可
     * N为coins长度  S为amount
     * 时间 O(SN)
     * 空间 O(S)
     */
    public int coinChange1(int[] coins, int amount) {
        int max=amount+1;
        int dp[]=new int[amount+1];
        Arrays.fill(dp,max);
        dp[0]=0;//钱为0的时候 不用硬币
        for (int i=0;i<coins.length;i++){
            for (int j=coins[i];j<=amount;j++){
                dp[j]=Math.min(dp[j],dp[j-coins[i]]+1);
            }
        }

        return dp[amount]>amount?-1:dp[amount];
    }
}
