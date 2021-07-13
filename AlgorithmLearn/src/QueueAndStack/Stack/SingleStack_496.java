package QueueAndStack.Stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SingleStack_496 {
    /**
     * 496. 下一个更大元素 I
     * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
     *
     * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
     *
     * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
     *
     * 解决方法：
     * 单调栈
     *
     * 时间复杂度 O(N+M)
     * 空间复杂度(N)
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //对nums2进行检索 找出每一个元素对应的下一个比他大的值
        Deque<Integer> stack=new LinkedList<>();//存索引
        Map<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<nums2.length;i++){
            while (!stack.isEmpty()&&nums2[i]>nums2[stack.peek()]){
                //栈非空 且 当前值大于栈顶值
                int index=stack.pop();//出栈
                map.put(nums2[index],nums2[i]);
            }
            //栈为空 或者当前值小于栈顶值  将索引入栈
            stack.push(i);
        }
        int ans[]=new int[nums1.length];
        for (int i=0;i<nums1.length;i++){
            ans[i]=map.getOrDefault(nums1[i],-1);
        }
        return ans;
    }
}
