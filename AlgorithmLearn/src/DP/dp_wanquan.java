package DP;

import java.util.Scanner;

public class dp_wanquan {
    /**
     * 完全背包
     * 有N种物品和一个容量为V的背包，每种物品都有无限件可用。第i种物品的体积是v[i]，
     * 价值是w[i]。求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大
     */
    /**
     * 空间优化
     * 顺序遍历   保证每次用的dp都是同一行的  也就是可以重复选
     * @param args
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int V = sc.nextInt();
        int[] v = new int[N];
        int[] w = new int[N];
        for (int i = 0; i < N; i++){
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        System.out.println(maxValue(N, V, v, w));
    }
    private static int maxValue(int N, int V, int[] v, int[] w){
        if (N == 0 || V == 0) return 0;
        int[] dp = new int[V+1];
        for (int i = 0; i < N; i++){ // 外循环从0开始递增
            for (int j = v[i]; j<= V; j++){ //内循环从v[i]开始递增
                dp[j] = Math.max(dp[j], dp[j-v[i]] + w[i]);
            }
        }
        return dp[V];
    }

}
