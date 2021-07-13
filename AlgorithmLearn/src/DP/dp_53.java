package DP;

public class dp_53 {
    /**
     * 53. 最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     */

    /**
     *dp[i]表示 以第i个数为结尾的最大的连续子序列和
     * 转移方程为
     *dp[i]=max(dp[i-1]+nums[i],nums[i])
     *比较我来做结尾还是做开头序列和更大
     *
     * 其实最后的结果应该是遍历 看一下到底谁最大
     * 但是可以每次遍历的时候同时比较
     * 空间优化很明显了
     */
    public int maxSubArray(int[] nums) {
        int n=nums.length;
        int dp=nums[0],max=nums[0];
        for (int i=1;i<n;i++){
            dp=Math.max(dp+nums[i],nums[i]);
            max=Math.max(max,dp);
        }

        return max;
    }
}
