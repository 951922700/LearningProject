package QueueAndStack.Stack;

public class stack07 {
    private int method=0;
    public int findTargetSumWays(int[] nums, int S) {
        dfs(0,nums,S,0);
        return method;
    }
    public void dfs(int sum,int[] nums,int s,int index){
        int sumAdd=sum+nums[index];
        int sumSubtract=sum-nums[index];
        //如果到了最后一个数且数组和满足s 方法加1
        if (index==nums.length-1){//有可能+0 -0
            if (sumAdd==s){
                method++;
            }
            if (sumSubtract==s){
                method++;
            }
            return ;
        }
        dfs(sumAdd,nums,s,index+1);
        dfs(sumSubtract,nums,s,index+1);
    }

    //动态规划
    public static int findTargetSumWays1(int[] nums, int s) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        // 绝对值范围超过了sum的绝对值范围则无法得到
        if (Math.abs(s) > Math.abs(sum)) return 0;

        int len = nums.length;
        // - 0 +
        int t = sum * 2 + 1;
        int[][] dp = new int[len][t];
        // 初始化
      /*  if (nums[0] == 0) {
            dp[0][sum] = 2;
        } else {
            dp[0][sum + nums[0]] = 1;
            dp[0][sum - nums[0]] = 1;
        }*/
        dp[0][sum + nums[0]]++;
        dp[0][sum - nums[0]]++;

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < t; j++) {
                // 边界
                int l = (j - nums[i]) >= 0 ? j - nums[i] : 0;
                int r = (j + nums[i]) < t ? j + nums[i] : 0;
                dp[i][j] = dp[i - 1][l] + dp[i - 1][r];
            }
        }
        return dp[len - 1][sum + s];
    }




    public static void main(String[] args) {
        stack07 stack07=new stack07();
        System.out.println(stack07.findTargetSumWays(new int[]{1, 1, 1, 1, 1},3));
    }
}
