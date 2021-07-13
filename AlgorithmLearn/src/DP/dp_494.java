package DP;

public class dp_494 {
    /**
     * 494. 目标和
     * 给你一个整数数组 nums 和一个整数 target 。
     *
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     *
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     * O(NV)
     */
    public int findTargetSumWays(int[] nums, int target) {
        int len=nums.length;
        int sum=0;//最大总和
        for (int num:nums) {
            sum+=num;
        }
        //如果target大于我们单纯相加得到的sum  则不可能得到
        if (Math.abs(target)>Math.abs(sum))
            return 0;
        int t=sum*2+1;//因为有+1  有-1  所以这个范围应该是两倍的sum+一个0的范围  -2 -1 0 -1 -2
        int dp[][]=new int[len][t];

        //初始化 加减  sum其实是偏移量 指向0的位置
        dp[0][sum+nums[0]]+=1;
        dp[0][sum-nums[0]]+=1;

        for (int i=1;i<len;i++){
            for (int j=-sum;j<=sum;j++){
                //注意边界问题  因为这里是从0到t-1  所以某个点进行加减可能越界
                if (j+nums[i]>sum)
                    dp[i][j+sum]=dp[i-1][sum+j-nums[i]];
                else if(j-nums[i]<-sum)
                    dp[i][j+sum]=dp[i-1][sum+j+nums[i]];
                else
                    dp[i][j+sum]=dp[i-1][j-nums[i]+sum]+dp[i-1][j+nums[i]+sum];
            }
        }
        return dp[len-1][sum+target];
    }

    /**
     * 转换思路
     * 数组和（全部+）为sum  1  2  3  4    +1 -2 +3 +4     sum=10  neg=2  8  8-2=6为方案值
     * 假设在一个方案中  -的数的总和为neg  那么+的数的总和就应该为sum-neg
     * 那么一个方案结果为(sum-neg)-neg
     * 令这个方案值为target   target=sum-2*neg   ->  neg=(sum-target)/2
     * 首先  sum肯定要大于target  否则没办法实现(全+)
     * 所以 sum-target>0  且sum-target为偶数
     *
     * 然后以neg为目标数  每次做减法
     * dp[i][j]表示 第i个数 值为j的方案
     * dp[i][j]=dp[i-1][j]+dp[i-1][j-nums[i]];
     *
     * 时间复杂度 O(len*(sum-target))
     * 空间O(len*(sum-target))
     */
    public int findTargetSumWays1(int[] nums, int target) {
        int len=nums.length;
        int sum=0;
        for (int num:nums) {
            sum+=num;
        }
        int diff=sum-target;//差值
        //不能为负数 和奇数
        if (diff<0||(diff&1)==1)
            return 0;

        int neg=diff/2;
        int dp[][]=new int[len][neg+1];

        //初始化
        //与题解的初始化不同  题解dp[i][j]是前i-1个的方案情况 而我的是i的情况 比较符合一般思维
        //这里注意neg与nums[0]都为0的情况  比如 0 0 0 1  target=1
        if (nums[0]<=neg)//大于neg的话就不是方案了
            dp[0][nums[0]]+=1;
        dp[0][0]+=1;//0的时候啥也不选也是一种方案

        for (int i=1;i<len;i++){
            for (int j=0;j<=neg;j++){
                dp[i][j]=dp[i-1][j];
                if (j-nums[i]>=0)
                    dp[i][j]=dp[i][j]+dp[i-1][j-nums[i]];
            }
        }

        return dp[len-1][neg];
    }

    /**
     * 空间优化
     */
    public int findTargetSumWays2(int[] nums, int target) {
        int len=nums.length;
        int sum=0;
        for (int num:nums) {
            sum+=num;
        }
        int diff=sum-target;//差值
        //不能为负数 和奇数
        if (diff<0||(diff&1)==1)
            return 0;

        int neg=diff/2;
        int dp[]=new int[neg+1];

        //初始化
        //与题解的初始化不同  题解dp[i][j]是前i-1个的方案情况 而我的是i的情况 比较符合一般思维
        if (nums[0]<=neg)//大于neg的话就不是方案了
            dp[nums[0]]+=1;
        dp[0]+=1;//0的时候啥也不选也是一种方案

        for (int i=1;i<len;i++){
            for (int j=neg;j>=nums[i];j--){
               //j>=nums[i]  剩余大小要大于等于才放得下
                dp[j]=dp[j]+dp[j-nums[i]];
            }
        }

        return dp[neg];
    }
}
