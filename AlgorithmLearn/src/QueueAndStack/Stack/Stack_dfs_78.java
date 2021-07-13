package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.List;

public class Stack_dfs_78 {
    /**
     * 78. 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * 示例 2：
     *
     * 输入：nums = [0]
     * 输出：[[],[0]]
     */
    List<List<Integer>> ans=new ArrayList<>();
    List<Integer> path=new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums,0);
        return ans;
    }

    public void dfs(int[] nums,int begin){
        ans.add(new ArrayList<>(path));
        //分配完了，没必要再进一次dfs  执行这个之前把最长的子集add了
        if (begin==nums.length)
            return;
        for (int i=begin;i<nums.length;i++){
            path.add(nums[i]);
            dfs(nums,i+1);
            path.remove(path.size()-1);
        }
    }
}
