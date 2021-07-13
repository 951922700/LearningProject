package BinarySearn;

public class binary09_4 {
    /**
     * 4. 寻找两个正序数组的中位数
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     * 示例 2：
     *
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     * 示例 3：
     *
     * 输入：nums1 = [0,0], nums2 = [0,0]
     * 输出：0.00000
     * 示例 4：
     *
     * 输入：nums1 = [], nums2 = [1]
     * 输出：1.00000
     * 示例 5：
     *
     * 输入：nums1 = [2], nums2 = []
     * 输出：2.00000
     *
     *
     * 提示：
     *
     * nums1.length == m
     * nums2.length == n
     * 0 <= m <= 1000
     * 0 <= n <= 1000
     * 1 <= m + n <= 2000
     * -106 <= nums1[i], nums2[i] <= 106
     *
     * todo 还有O(log(m+n))解法
     */

    //合并数组再找中心值  如何高效合并
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1=nums1.length,len2=nums2.length;
        int len=len1+len2;
        int nums[]=new int[len];
        int i=0,j=0,k=0;//i:n1下标 j:n2下标 k:新数组下标
        while(i<len1&&j<len2){
            //小的先放
            if (nums1[i]<nums2[j]){
                nums[k++]=nums1[i++];
            }else{
                nums[k++]=nums2[j++];
            }
        }
        //退出循环必然是长的还有剩
        while(i<len1)
            nums[k++]=nums1[i++];
        while(j<len2)
            nums[k++]=nums2[j++];

        //此时数组合并并有序
        if (nums.length%2==0){
            //是偶数  则去中间两位/2
            return (nums[len/2]+nums[len/2-1])/2.0;
        }else
            return nums[len/2];
    }

    public static void main(String[] args) {
        int []nums1=new int[]{1,2};
        int []nums2=new int[]{3,4};
        System.out.println(findMedianSortedArrays(nums1,nums2));
    }
}
