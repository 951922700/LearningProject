package DP;

public class dp_518 {
    /**
     * 518. 零钱兑换 II
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     *
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     *
     * 假设每一种面额的硬币有无限个。
     *
     * 题目数据保证结果符合 32 位带符号整数。
     */
    public int change(int amount, int[] coins) {
        /**
         * 例子 coins 1,2    amount 3
         * 组成2的组合数为1   2组成2个数(在dp[i-coins]上+一个coins使其为dp[i])
         * 1 和 2 的组合数再相加就是  组成2的一个组成总数
         * 所以dp[i]=dp[i]+dp[i-coins]为组合数  这个dp[i]实际上就是上一个为i的coin的组合个数
         */
        int len=coins.length;
        int dp[]=new int[amount+1];
        dp[0]=1;//不选就是一种组合
        for (int i=0;i<len;i++){
            for (int j=coins[i];j<=amount;j++){
                dp[j]+=dp[j-coins[i]];
            }
        }
        return dp[amount];
    }
}
