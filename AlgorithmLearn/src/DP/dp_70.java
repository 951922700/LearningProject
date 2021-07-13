package DP;

public class dp_70 {
    /**
     * 爬楼梯
     * 每次爬一台阶或者两台阶
     * n阶
     * 求组合次数
     * 顺序不同也算
     */
    public int climbStairs(int n) {
        int nums[]=new int[]{1,2};
        //外循环 n
        //内循环nums[] 保证结尾能够不同
        int dp[]=new int[n+1];
        dp[0]=1;
        for (int i=1;i<=n;i++){
            for (int j=0;j<nums.length;j++){
                if (i>=nums[j])
                    dp[i]+=dp[i-nums[j]];
            }
        }
        return dp[n];
    }
}
