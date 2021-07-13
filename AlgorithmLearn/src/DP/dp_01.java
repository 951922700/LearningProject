package DP;

import java.util.Scanner;

public class dp_01 {
    /**
     * 01背包基本问题
     * 有N件物品和一个容量为V的背包。第i件物品的体积是c[i]，价值是w[i]。
     * 求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大
     * 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
     *
     * 第 i 件物品的体积是 vi，价值是 wi。
     *
     * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
     * 输出最大价值。
     *
     * 输入格式
     * 第一行两个整数，N，V，用空格隔开，分别表示物品数量和背包容积。
     *
     * 接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 件物品的体积和价值。
     *
     * 输出格式
     * 输出一个整数，表示最大价值。
     *
     * 数据范围
     * 0<N,V≤1000
     * 0<vi,wi≤1000
     * 输入样例
     * 4 5
     * 1 2
     * 2 4
     * 3 4
     * 4 5
     * 输出样例：
     * 8
     */

    /**
     * 二维数组
     * dp[i][j]=表示前i件物品在容量为j的情况下 最大总价值
     * 转换方程为
     * dp[i][j]=max(dp[i-1][j],dp[i-1][j-c[i]]+w[i])
     * 其中
     * 一种情况是i不放，那么总价值就直接为前i件
     * i放  那么总价值为dp[i][j-c[i]]再加上i的价值
     */
    public static void dp1(){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();//物品个数
        int V=sc.nextInt();//背包总体积

        int v[]=new int[N+1];//i物品体积
        int w[]=new int[N+1];//i物品价值

        if (N==0||V==0)
            return;

        for (int i=1;i<=N;i++){
            v[i]=sc.nextInt();
            w[i]=sc.nextInt();
        }
        int dp[][]=new int[N+1][V+1];
        for (int i=1;i<=N;i++)
            for (int j=0;j<=V;j++){
                dp[i][j]=dp[i-1][j];
                if (j>=v[i])//防止负数
                dp[i][j]=Math.max(dp[i][j],dp[i-1][j-v[i]]+w[i]);
            }

        System.out.println(dp[N][V]);
    }

    /**
     * 空间优化
     * 一维数组
     * 首先为什么内循环是从V到0
     * dp[i]=max(dp[i-1],dp[i-v[i]+w[i]])
     * 这里要保证dp[i-v[i]]是第i-1物品的值
     * 假设从从0到V
     * 那么因为i-v[i]肯定会比i小(这里i是体积)所以当我们后面求值需要用到前面的时候 会用到第i个物品的 而不是第i-1个物品的
     *
     * 对于题目的两种问法
     * 第一 背包不一定要装满  都为0
     * 第二 恰好装满   这个时候dp初始化第一个为0  后面为无穷大
     * 如何理解？
     * 初始化的时候，对于二维数组的时候dp[i][j]这个i是-1，也就是什么都不放 对应的最大价值  而dp[0]=0 就是0空间  放0
     * 而其他的则无解(因为要恰好)
     */
    public static void dp2(){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();//物品个数
        int V=sc.nextInt();//背包总体积

        int v[]=new int[N+1];//i物品体积
        int w[]=new int[N+1];//i物品价值

        if (N==0||V==0)
            return;

        for (int i=1;i<=N;i++){
            v[i]=sc.nextInt();
            w[i]=sc.nextInt();
        }
        int dp[]=new int[V+1];
        for (int i=1;i<=N;i++)
            for (int j=V;j>=v[i];j--){//这里的j>=v[i]是优化  如果这个体积j  小于v[i]说明这个物品放不下了，可以跳过
                dp[j]=Math.max(dp[j],dp[j-v[i]]+w[i]);
            }

        System.out.println(dp[V]);
    }
    public static void main(String[] args) {
        //dp1();
        dp2();
    }
}
