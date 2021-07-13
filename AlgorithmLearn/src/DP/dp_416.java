package DP;

import java.util.Arrays;

public class dp_416 {
    /**
     * 416. 分割等和子集
     * 给你一个 只包含正整数 的 非空 数组 nums 。
     * 请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * 分析：
     * 转换成01背包问题
     * 找出数组和的一半  所以和是奇数不行、偶数行
     */
    /**
     * 二维数组解法
     * 状态转换方程  dp[i][j]=dp[i-1][j]||dp[i-1][j-nums[i]]
     * 两种  ：第一种 不放，看i-1之前的能不能刚好放完  第二种  放 看i-1之前的 然后体积减一部分是否能放完   两个之中有一个满足就位true
     * 时间：ONC
     * 空间：ONC
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int len=nums.length;
        int sum=0;
        for (int num:nums) {
            sum+=num;
        }

        if ((sum&1)==1)//为奇数
            return false;

        int target=sum/2;
        boolean dp[][]=new boolean[len][target+1];
        //初始化第一行数组  第一个物品只有在体积刚好为他的时候才能刚好放的下
        if (nums[0]<=target)
            dp[0][nums[0]]=true;

        for (int i=1;i<len;i++){
            for (int j=0;j<=target;j++){
                dp[i][j]=dp[i-1][j];
               /* if (nums[i]==j){
                    dp[i][j]=true;
                    continue;
                }*/
                if (nums[i]<=j)
                    dp[i][j]=dp[i-1][j]||dp[i-1][j-nums[i]];
            }
        }

       /* for (int i = 0; i <len ; i++) {
            System.out.println();
            for (int j = 0; j <=target ; j++) {
                System.out.print(dp[i][j]+" ");
            }
        }*/
        return dp[len-1][target];
    }

    /**
     * 空间优化
     * @param nums
     * @return
     */
    public boolean canPartition1(int[] nums) {
        int len=nums.length;
        int sum=0;
        for (int num:nums) {
            sum+=num;
        }

        if ((sum&1)==1)//为奇数
            return false;

        int target=sum/2;
        boolean dp[]=new boolean[target+1];
        //初始化第一行数组  第一个物品只有在体积刚好为他的时候才能刚好放的下
        if (nums[0]<=target)
            dp[nums[0]]=true;

        for (int i=1;i<len;i++){
            for (int j=target;nums[i]<=j;j--){
               dp[j]=dp[j]||dp[j-nums[i]];
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums=new int[]{1,5,11,15};
        canPartition(nums);
    }
}
