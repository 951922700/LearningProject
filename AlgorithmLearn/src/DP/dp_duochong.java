package DP;

import java.util.Scanner;

public class dp_duochong {
    /**
     * 有N种物品和一个容量为V的背包，每种物品都有无限件可用。第i种物品的数量为s[i]，
     * 体积是v[i]，价值是w[i]。求解将哪些物品装入背包可使体积总和不超过背包容量，
     * 且价值总和最大，输出最大价值。
     *
     * 重点是物品有限个数
     * 所以直接用第三层循环（k为物品个数）
     * 分别计算放0-k的时候的最大价值
     * @param args
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int V = sc.nextInt();
        int[] v = new int[N];
        int[] w = new int[N];
        int[] s = new int[N];
        for (int i = 0; i < N; i++){
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
            s[i] = sc.nextInt();
        }
        System.out.println(maxValue(N, V, v, w, s));
    }
    private static int maxValue(int N, int V, int[] v, int[] w, int[] s){
        if (N == 0 || V == 0) return 0;
        int[] dp = new int[V+1];
        for (int i = 0; i < N; i++){ // 第一重循环从0开始递增
            for (int j = V; j >= 0; j--){ //第二重循环从V开始递减
                for (int k = 0; k <= s[i]; k++){ //第三重循环从0开始递增
                    if (j >= k * v[i]) { // 判断容量是否足够
                        dp[j] = Math.max(dp[j], dp[j - k * v[i]] + k * w[i]);
                    }
                }
            }
        }
        return dp[V];
    }

}
