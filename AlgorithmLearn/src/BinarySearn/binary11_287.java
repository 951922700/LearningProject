package BinarySearn;

import java.util.HashSet;

public class binary11_287 {
    /**
     * 287. 寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
     *
     * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,3,4,2,2]
     * 输出：2
     * 示例 2：
     *
     * 输入：nums = [3,1,3,4,2]
     * 输出：3
     * 示例 3：
     *
     * 输入：nums = [1,1]
     * 输出：1
     * 示例 4：
     *
     * 输入：nums = [1,1,2]
     * 输出：1
     *
     *
     * 提示：
     *
     * 2 <= n <= 3 * 104
     * nums.length == n + 1
     * 1 <= nums[i] <= n
     * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
     * //todo 看看其他解法
     */

    public int findDuplicate(int[] nums) {
        HashSet<Integer> set=new HashSet<Integer>();
        for (int temp:nums) {
            if(set.contains(temp)){
                return temp;
            }
            set.add(temp);
        }
        return -1;
    }

    //二分法  n+1 个数  1~n之间 找重复数 抽屉原理
    public static int findDuplicate1(int[] nums) {
        int left=1,right=nums.length-1;
        while(left<right){
            int mid=left+(right-left)/2;

            int num=0;
            //统计小于等于mid的个数
            for (int temp:nums) {
                if (temp<=mid)
                    num++;
            }

            if (num>mid){
                //num大于mid 一定有重复的且mid在左区间1~mid
                right=mid;
            }else{
                left=mid+1;
            }
        }

       return left;
    }
}
