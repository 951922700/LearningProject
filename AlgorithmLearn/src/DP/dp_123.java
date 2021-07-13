package DP;

public class dp_123 {
    /**
     * 123. 买卖股票的最佳时机 III
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     *
     *
     * 示例 1:
     *
     * 输入：prices = [3,3,5,0,0,3,1,4]
     * 输出：6
     * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
     */
    /**
     *某一天的股票有几种状态
     * 1.啥也没买
     * 2.第一次买
     * 3.第一次买卖
     * 4.第一次买卖后，进行的第二次买
     * 5.第二次买卖
     * 状态转移
     * 第一种状态不需要记录
     * buy1=max(buy1`,-prices)  第一次买，要么是前一天就买了  要么是今天买的  实际上第一次比较的时候  buy`等于第1天买入时的股票利润 -prices则是自己的  所以比较谁的买入利润更低
     * sell1=max(sell1`,buy1`+prices)  第一次卖 前一天就卖了  前天买的状态下（不一定是前天买）今天卖
     * buy2=max(buy2`,sell1`=prices)  同上
     * sell2=max(sell2`,buy2`+prices) 同上
     *
     * 空间优化为
     * buy1=max(buy1,-prices)
     * sell1=max(sell1,buy+prices)
     * buy2=max(buy2,sell1=prices)
     * sell2=max(sell2,buy2+prices)
     * 对于sell1的时候使用buy1
     * 实际上buy1 比buy1`多考虑了一个第i股票买入的情况
     * 而sell1是对第i的卖出  同一只股票买入卖出没有利益
     * 所以sell1依靠
     */
    public static int maxProfit(int[] prices) {
        int n=prices.length;
        int buy1[]=new int[n];
        int sell1[]=new int[n];
        int buy2[]=new int[n];
        int sell2[]=new int[n];

        //初始化
        buy1[0]=-prices[0]; sell1[0]=0;
        buy2[0]=-prices[0]; sell2[0]=0;
        System.out.println(""+buy1[0]+" "+sell1[0]+" "+buy2[0]+" "+sell2[0]);
        for (int i=1;i<n;i++){
            buy1[i]=Math.max(buy1[i-1],-prices[i]);
            sell1[i]=Math.max(sell1[i-1],buy1[i-1]+prices[i]);
            buy2[i]=Math.max(buy2[i-1],sell1[i-1]-prices[i]);
            sell2[i]=Math.max(sell2[i-1],buy2[i-1]+prices[i]);
            System.out.println(""+buy1[i]+" "+sell1[i]+" "+buy2[i]+" "+sell2[i]);
        }
        return sell2[n-1];
    }

    public static int maxProfit1(int[] prices) {
        int n=prices.length;
        int buy1=0;
        int sell1=0;
        int buy2=0;
        int sell2=0;

        //初始化
        buy1=-prices[0]; sell1=0;
        buy2=-prices[0]; sell2=0;
        System.out.println(""+buy1+" "+sell1+" "+buy2+" "+sell2);
        for (int i=1;i<n;i++){
            buy1=Math.max(buy1,-prices[i]);
            /**
             * 对于这里使用buy1  这样理解  如果是用之前的buy1那么没有什么理解难度
             * 但是这里用的是与当日卖出相比较之后的结果
             * 其实假设 buy1`是-2  而prices[i]=-1
             * 那么两种情况下
             * 1.用之前的buy1  那肯定是-2+1根之前的sell1比
             * 2.用比较过的buy1 此时buy1的值必定是-1   那就是-1+1和sell1比 此时不赚钱
             * 那么-2+1的收益肯定是比0还要低的
             * 最重要的一点是sell1和sell2不可能为0以下  也就意味着这个收益为0（同一天买入卖出）并不影响结果
             */
            sell1=Math.max(sell1,buy1+prices[i]);
            buy2=Math.max(buy2,sell1-prices[i]);
            sell2=Math.max(sell2,buy2+prices[i]);
            System.out.println(""+buy1+" "+sell1+" "+buy2+" "+sell2);
        }
        return sell2;
    }

    public static void main(String[] args) {
        maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4});
        System.out.println();
        maxProfit1(new int[]{3, 3, 5, 0, 0, 3, 1, 4});
    }
}
