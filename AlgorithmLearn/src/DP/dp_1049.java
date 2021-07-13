package DP;

public class dp_1049 {
    /**
     * 1049. 最后一块石头的重量 II
     * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
     *
     * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     *
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
     */

    /**
     *问题转换
     * x==y  变成x-y或者y-x
     * x!=y   y-x
     * 也就是最后的差值  肯定为+或者-的组合  类似目标和
     * 我们定义sum为数组和  neg为-的和 那么+的和为sum-neg
     * 最后的结果（+和-混合）(sum-neg)-neg    =sum-2*neg=答案
     * 要使得答案为最小可能的重量
     * 也就是要求neg在<=2/num的最大值
     * 也就转换为
     * 背包体积为sum/2   各个石头 体积和价值均为stonesi
     * 在不超过最大体积的前提下  能放的最大重量或者说是价值
     *
     * dp[i][j]表示  第i个物品 剩余容量为j时候  通过放或不放来获得true
     * dp[i][j]=dp[i-1][j]||dp[i-1][j-stones[i]]
     * 最后一行dp中为true的最大的j即为neg  也就是满足小于sum/2的最大的体积
     */
    public int lastStoneWeightII(int[] stones) {
        int len=stones.length;
        int sum=0;
        for (int stone:stones) {
            sum+=stone;
        }
        boolean dp[][]=new boolean[len][sum/2+1];
        //初始化
        if (stones[0]<=sum/2)
            dp[0][stones[0]]=true;
        dp[0][0]=true;

        for (int i=1;i<len;i++){
            for (int j=0;j<=sum/2;j++){
                dp[i][j]=dp[i-1][j];
                if (j>=stones[i])
                    dp[i][j]=dp[i][j]||dp[i-1][j-stones[i]];
            }
        }
        //todo 重点！因为我们不是要找刚好满  如果刚好满直接看dp[len-1][sum/2]是否有true就可以知道是不是能够刚好满
        for (int j=sum/2;;j--){
            if (dp[len-1][j])
                return sum-2*j;
        }
    }

    /**
     * 空间优化
     * @param stones
     * @return
     */
    public int lastStoneWeightII1(int[] stones) {
        int len=stones.length;
        int sum=0;
        for (int stone:stones) {
            sum+=stone;
        }
        boolean dp[]=new boolean[sum/2+1];
        //初始化
        if (stones[0]<=sum/2)
            dp[stones[0]]=true;
        dp[0]=true;

        for (int i=1;i<len;i++){
            for (int j=sum/2;j>=stones[i];j--){
                dp[j]=dp[j]||dp[j-stones[i]];
            }
        }
        for (int j=sum/2;;j--){
            if (dp[j])
                return sum-2*j;
        }
    }
}
