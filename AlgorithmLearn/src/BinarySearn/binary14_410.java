package BinarySearn;

public class binary14_410 {
    /**
     * 410. 分割数组的最大值
     * 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
     *
     * 设计一个算法使得这 m 个子数组各自和的最大值最小。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [7,2,5,10,8], m = 2
     * 输出：18
     * 解释：
     * 一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
     * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
     * 示例 2：
     *
     * 输入：nums = [1,2,3,4,5], m = 2
     * 输出：9
     * 示例 3：
     *
     * 输入：nums = [1,4,4], m = 3
     * 输出：4
     */
    public int splitArray(int[] nums, int m) {
        /**
         * 1.根据最大子数组和确认分割次数
         * 2.当分割次数等于m的时候还得继续缩小最大子数组的和来达到题目要求
         * 3.分割次数越多 最大值越小
         */
        int sum=0,max=0;
        for (int num:nums) {
            max=Math.max(max,num);
            sum+=num;
        }

        //左边界是数组中的最大值，右边界是数组和
        int left=max,right=sum;

        while(left<right){
            int mid=left+(right-left)/2;

            int spilits=spilit(nums,mid);
            if (spilits>m){
                //分割次数大于指定分割次数  那么最大值小了  要变大
                left=mid+1;
            }else{
                //怎么理解不断搜索会趋向于更小的最大值呢
                //当满足条件=m时一直是right在移动
                right=mid;
            }
        }
        return left;
}
    public static int spilit(int[] nums, int maxIntervalSum){
        //对数组进行分割 返回分割次数 默认一次
        int spilit=1;
        int curIntervalSum=0;//临时区间和
        for (int num:nums) {
            if (curIntervalSum+num>maxIntervalSum){
                //如果+下一个数大于限制的最大区间和  此时cur清零 并且进行一次分割
                curIntervalSum=0;
                spilit++;
            }
            curIntervalSum+=num;
        }
        return spilit;
    }
}
