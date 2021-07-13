package ArrayAndString.String;

/**
 * 两数之和 II - 输入有序数组
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 *
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 */
public class String07 {
    //暴力拆解
/*    public int[] twoSum(int[] numbers, int target) {
        int index1,index2;
        for (index1=0;index1<numbers.length;index1++)
            for (index2=index1+1;index2<numbers.length;index2++){
                if (numbers[index1]+numbers[index2]==target) return new int[]{index1+1,index2+1};
            }
        return new int[2];
    }*/

    //二分查找
/*    public int[] twoSum(int[] numbers, int target) {
        for (int index1=0;index1<numbers.length;index1++){
            int index2=index1+1,last=numbers.length-1;
            while (index2<=last){
                int mid=(index2-last)/2+last;
                if (target-numbers[index1]==numbers[mid]) return new int[]{index1+1,mid+1};
                else if(target-numbers[index1]>numbers[mid]) index2=mid+1;
                else last=mid-1;
            }
        }
        return new int[2];
    }*/
    //双指针
    public int[] twoSum(int[] numbers, int target) {
        int left=0,right=numbers.length-1;
        while(left<right){
            if (numbers[left]+numbers[right]==target) return new int[]{left+1,right+1};
            else if (numbers[left]+numbers[right]>target) right--;
            else left++;
        }
        return new int[2];
    }
}
