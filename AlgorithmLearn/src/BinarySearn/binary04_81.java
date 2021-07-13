package BinarySearn;

public class binary04_81 {
    /**
     * 81. 搜索旋转排序数组 II
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
     *
     * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
     *
     * 示例 1:
     *
     * 输入: nums = [2,5,6,0,0,1,2], target = 0
     * 输出: true
     * 示例 2:
     *
     * 输入: nums = [2,5,6,0,0,1,2], target = 3
     * 输出: false
     */
    /**
     * 对比前面一题需要多判断一种情况
     * 第一类
     * 10111 和 11101这种。此种情况下 nums[start] == nums[mid]，
     * 有重复的一般都是无法判断哪个区间是有序的这个时候根据情况判断然后让右边界-1就好
     * 分不清到底是前面有序还是后面有序，此时 start++ 即可。相当于去掉一个重复的干扰项。
     */
    public boolean search(int[] nums, int target) {
        int len=nums.length;
        int left=0,right=len-1;

        while(left<right){
            int mid=left+(right-left+1)/2;//注意统一中间数取法
            if (nums[mid]<nums[right]){
                //两个区间都是有序的
                if (nums[mid]<=target&&target<=nums[right]){
                    left=mid;
                }else{
                    right=mid-1;
                }
            }else if(nums[mid]>nums[right]){
                //左区间有序
                if (nums[left]<=target&&target<=nums[mid-1]){
                    right=mid-1;
                }else{
                    left=mid;
                }
            }else{
                //相等，因为无法判断哪个区间有序
                //必须有
                if (nums[mid]==target)
                return true;
                right--;
            }
        }
        return nums[left]==target;
    }
}
