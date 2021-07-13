package BinarySearn;

public class binary03_33 {
    /**
     * 33. 搜索旋转排序数组
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     *
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     *
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的索引，否则返回 -1 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     * 示例 2：
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     * 示例 3：
     *
     * 输入：nums = [1], target = 0
     * 输出：-1
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 5000
     * -10^4 <= nums[i] <= 10^4
     * nums 中的每个值都 独一无二
     * nums 肯定会在某个点上旋转
     * -10^4 <= target <= 10^4
     */

    //利用局部单调性，逐步缩小搜索区间   解题思路就是利用中心点和右边界比较  然后得到两个区间 然后按情况改动左右指针
    //但是要注意中心点取值一致的问题
    public int search(int[] nums, int target) {
        int n=nums.length;
        int left=0,right=n-1;
        if (n==0)
            return -1;

        while(left<right){
            int mid=left+(right-left+1)/2;//取右边中心点
            //如果中点小于右边界 右边有序
            if (nums[mid]<nums[right]){
                if (nums[mid]<=target&&target<=nums[right]){
                    left=mid;
                }else
                    right=mid-1;
            }else{
                //如果中心点大于右边界   那么中心点左边的区间一定是有序的
                if (nums[left]<=target&&target<=nums[mid-1]){
                    right=mid-1;
                }else{
                    left=mid;
                }
            }
        }
        if (nums[left]==target)
            return left;
        return -1;
    }
}
