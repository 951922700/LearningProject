package DP;

public class dp_377 {
    /**
     * 377. 组合总和 Ⅳ
     * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
     *
     * 题目数据保证答案符合 32 位整数范围。
     * 这个和硬币2区别在于顺序不同也算一种
     * 所以两层for循环
     * 第一层循环1->target
     * 第二层循环 nums
     * 第二层循环nums  就好比
     * 我在求nums=1,2  dp[3]也就是target为3时我放1作为结尾和放2作为结尾的情况
     * 所以会有重复情况(脑补下一次进入循环 又考虑放1和2)
     *
     * 而在第一层循环是nums
     * 第二层循环是1->target的时候
     * 每次算的是不同target下 我放对应nums的情况
     * 所以nums的这个顺序是固定的  不会重复的
     */
    public int combinationSum4(int[] nums, int target) {
        int len=nums.length;
        int dp[]=new int[target+1];
        dp[0]=1;
        for (int i=1;i<=target;i++){
            for (int j=0;j<len;j++){
                if (i>=nums[j])
                    dp[i]+=dp[i-nums[j]];
            }
        }
        return dp[target];
    }
}
