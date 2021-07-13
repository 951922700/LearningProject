package ArrayAndString.String;

/**
 * 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0。
 *
 *  
 *
 * 示例：
 *
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 */
public class String10 {
    //暴力
/*    public int minSubArrayLen(int s, int[] nums) {
        int len=1;
        int minLen=0;
        for (int i=0;i<nums.length;i++){
            //当有一个值>=s
            if (nums[i]>=s) return 1;
            int temp=nums[i];
            for (int j=i+1;j<nums.length;j++){
                temp+=nums[j];
                len++;
                //第一次满足即为答案
                if (temp>=s) {
                    if (minLen==0) minLen=len;//防止第一次执行的时候是0最小
                            else minLen=Math.min(len,minLen);
                    len=1;
                    break;
                }
            }
            len=1;
        }
        return minLen;
    }*/

    //前缀和+二分法
   /* public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }*/

   //双指针 时间O(n) 空间O(1)
    public int minSubArrayLen(int s, int[] nums) {
        int n=nums.length;
        if (n==0) return 0;

        int ans=Integer.MAX_VALUE;
        int sum=0;
        int start=0;
        int end=0;

        while (end<n){
            sum+=nums[end];
            while(sum>=s){
                ans=Math.min(ans,end-start+1);
                sum-=nums[start];
                start++;
            }
            end++;
        }
        return ans==Integer.MAX_VALUE?0:ans;
    }
    public static void main(String[] args) {
        String10 string10=new String10();
        string10.minSubArrayLen(7,new int[]{2,3,1,2,4,3});
    }
}
