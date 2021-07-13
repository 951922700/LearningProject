package DP;

public class dp_309 {
    /**
     * 309. 最佳买卖股票时机含冷冻期
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     *
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     *
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     */

    /**
     *状态
     * buy 持有股票
     * sell 没有股票
     * buy[i]=max(buy[i-1],sell[i-2]-prices[i]) 前一天就买入  或者前天卖出 今天买入
     * sell[i]=max(sell[i-1],buy[i-1]+prices[i]) 前一天就卖出，或者今天买入
     *
     * buy[0]=-prices[0]
     * sell[0]=0
     *
     * buy[1]=max(-prices[0],-prices[1]) 第二天买入 肯定是要么第一天就买入  要么第二天才买入
     * sell[1]=max(0,buy[0]+prices[1])  第二天卖出
     *
     * 时间O(N)
     * 空间O(N)
     */
    public int maxProfit(int[] prices) {
        int n=prices.length;
        if (n<2)
            return 0;
        int buy[]=new int[n];
        int sell[]=new int[n];

        //初始化
        buy[0]=-prices[0];
        sell[0]=0;
        buy[1]=Math.max(buy[0],-prices[1]);
        sell[1]=Math.max(sell[0],buy[0]+prices[1]);

        for (int i=2;i<n;i++){
            buy[i]=Math.max(buy[i-1],sell[i-2]-prices[i]);
            sell[i]=Math.max(sell[i-1],buy[i-1]+prices[i]);
        }

        return sell[n-1];
    }
}
