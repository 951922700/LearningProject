package DP;

import java.util.Arrays;

public class dp_188 {
    /**
     * 188. 买卖股票的最佳时机 IV
     * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */

    /**
     *分析：
     * 状态
     * buy[i][j]  第i支股票 在交易j次时  持有股票的最大利润
     * sell[i][j] 第i支股票  在交易j次时  不持有股票
     *
     * buy[i][j]=max(buy[i-1][j],sell[i-1][j]-prices[i])
     * sell[i][j]=max(sell[i-1][j],buy[i-1][j-1]+prices[i])
     *
     * 初始化
     * buy[0][0]=-prices[0]  没进行交易时  持有一只股票
     * 其他buy[0][j]设置一个较小的值
     * sell[0][0]=0
     * 其他sell[0][j]设置一个较小的值
     *
     * 当j=0的时候 没有进行任何交易
     * sell[i][0]肯定等于0
     * 所以j从1开始循环
     * 对于交易次数k  prices最多交易次数为n/2向下取整
     * 所以这个交易次数取k 和n/2最小值
     *
     * 因为是交易次数最大为k
     * 所以取sell[n-1][j]中的最大值
     */
    public int maxProfit(int k, int[] prices) {
        if (prices.length==0)
            return 0;
        int n=prices.length;
        int len=Math.min(k,n/2);

        int buy[][]=new int[n][len+1];
        int sell[][]=new int[n][len+1];

        buy[0][0]=-prices[0];
        sell[0][0]=0;

        for (int i=1;i<=len;i++)
            buy[0][i]=sell[0][i]=Integer.MIN_VALUE/2;//比如第一支股票的时候不可能交易为1或以上  因为不能自己交易

        for (int i=1;i<n;i++){
            buy[i][0]=Math.max(buy[i-1][0],-prices[i]);
            for (int j=1;j<=len;j++){
                buy[i][j]=Math.max(buy[i-1][j],sell[i-1][j]-prices[i]);//如果这里牵扯到那些不合法的  那么这个肯定也是不合法的
                sell[i][j]=Math.max(sell[i-1][j],buy[i-1][j-1]+prices[i]);
            }
        }

        return Arrays.stream(sell[n-1]).max().getAsInt();
    }

    /**
     * 空间优化
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit1(int k, int[] prices) {
        if (prices.length==0)
            return 0;
        int n=prices.length;
        int len=Math.min(k,n/2);

        int buy[]=new int[len+1];
        int sell[]=new int[len+1];

        buy[0]=-prices[0];
        sell[0]=0;

        for (int i=1;i<=len;i++)
            buy[i]=sell[i]=Integer.MIN_VALUE/2;//比如第一支股票的时候不可能交易为1或以上  因为不能自己交易

        for (int i=1;i<n;i++){
            buy[0]=Math.max(buy[0],-prices[i]);
            for (int j=1;j<=len;j++){
                int buyj=Math.max(buy[j],sell[j]-prices[i]);//如果这里牵扯到那些不合法的  那么这个肯定也是不合法的
                int sellj=Math.max(sell[j],buy[j-1]+prices[i]);
                buy[j]=buyj;
                sell[j]=sellj;
            }
        }

        return Arrays.stream(sell).max().getAsInt();
    }
}
