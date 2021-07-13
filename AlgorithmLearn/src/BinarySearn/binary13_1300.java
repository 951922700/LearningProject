package BinarySearn;

import java.util.Collections;

public class binary13_1300 {
    /**
     * 1300. 转变数组后最接近目标值的数组和
     * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。
     *
     * 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
     *
     * 请注意，答案不一定是 arr 中的数字。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr = [4,9,3], target = 10
     * 输出：3
     * 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
     * 示例 2：
     *
     * 输入：arr = [2,3,5], target = 10
     * 输出：5
     * 示例 3：
     *
     * 输入：arr = [60864,25176,27249,21296,20204], target = 56803
     * 输出：11361
     *
     *
     * 提示：
     *
     * 1 <= arr.length <= 10^4
     * 1 <= arr[i], target <= 10^5
     */

    //分析   value选的越大 那么改变之后的数组和就会越大
    public int findBestValue(int[] arr, int target) {
        //首先找到大于target的第一个值  然后最后对比value 和value-1哪个最接近
        int left=0,right=0;
        //right应该为数组中的最大值
        for (int num:arr) {
            right=Math.max(num,right);
        }

        while(left<right){
            int mid=left+(right-left)/2;

            //获取修改之后的总和
            int sum=getChangedSum(arr,mid);
            //找到大于target的 最接近target的整数value
            if (sum<target){
                //小于目标值一定不是，在右区间
                left=mid+1;
            }else{
                right=mid;
            }
        }

        //比较value和value-1 哪个更接近target
        int sum1=getChangedSum(arr,left);
        int sum2=getChangedSum(arr,left-1);

        return Math.abs(sum1-target)<Math.abs(sum2-target)?left:left-1;
    }

    public static int getChangedSum(int[] arr, int value){
        int sum=0;
        for (int num:arr) {
            //所有大于value的都要变成value
            sum+=Math.min(num,value);
        }
        return sum;
    }
}
