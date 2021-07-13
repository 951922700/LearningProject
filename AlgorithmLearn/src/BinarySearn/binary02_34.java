package BinarySearn;

public class binary02_34 {
    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     *
     * 进阶：
     *
     * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     *
     *
     * 示例 1：
     *
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * 示例 2：
     *
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * 示例 3：
     *
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     *
     *
     * 提示：
     *
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * nums 是一个非递减数组
     * -109 <= target <= 109
     */

    public int[] searchRange(int[] nums, int target) {
        int first=0,last=0;
        int len=nums.length;
        if (len==0)
            return new int[]{-1,-1};
        first=findFirstPosition(nums,target);
        if (first==-1)
            return new int[]{-1,-1};
        last=findLastPosition(nums,target);

        return new int[]{first,last};
    }

    //找出第一个位置 让右边指针往左边靠
    public static int findFirstPosition(int[] nums, int target){
        int n=nums.length;
        int left=0,right=n-1;
        while(left<right){
            int mid=left+(right-left)/2;
            //中心值小于目标值 中心值不是解 下一个搜索区间是[mid+1,right]
            if(nums[mid]<target)
                left=mid+1;
            //中心值等于目标值  让右边的指针动
            else if(nums[mid]==target)
                right=mid;
            //中心值大于目标值  中心值不是解 下一个搜索区间是[left,mid-1]
            else
                right=mid-1;
        }
        if (nums[left]==target)
        return left;

        return -1;
    }

    //找出最后一个位置让左指针往右边靠
    public static int findLastPosition(int[] nums, int target){
        int n=nums.length;
        int left=0,right=n-1;
        while(left<right){
            int mid=left+(right-left+1)/2;
            //中心值小于目标值 中心值不是解 下一个搜索区间是[mid,right]
            if(nums[mid]<target)
                left=mid+1;
                //中心值等于目标值  让左边的指针动
            else if(nums[mid]==target)
                left=mid;
                //中心值大于目标值  中心值不是解 下一个搜索区间是[left,mid-1]
            else
                right=mid-1;
        }
        if (nums[left]==target)
            return left;

        return -1;
    }
    /**
     * 重点理解中心点取值
     * 每一轮区间被划分成 2 部分，理解 区间划分 决定中间数取法（ 无需记忆，需要练习 + 理解 ），在调试的过程中理解 区间和中间数划分的配对关系：
     * 划分 [left, mid] 与 [mid + 1, right] ，mid 被分到左边，对应 int mid = left + (right - left) / 2;；
     * 划分 [left, mid - 1] 与 [mid, right] ，mid 被分到右边，对应 int mid = left + (right - left + 1) / 2;。
     *
     * 上面findLastPositon想要left=mid  那么这个时候就要用到第二个分区间的方法  否则可能陷入死循环
     */
}
