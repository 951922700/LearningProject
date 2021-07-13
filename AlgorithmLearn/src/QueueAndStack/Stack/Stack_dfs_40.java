package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stack_dfs_40 {
    /**
     * 40. 组合总和 II
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的每个数字在每个组合中只能使用一次。
     *
     * 说明：
     *
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1:
     *
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     * 示例 2:
     *
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 所求解集为:
     * [
     *   [1,2,2],
     *   [5]
     * ]
     */
    List<List<Integer>> ans=new ArrayList<>();
    List<Integer> path=new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates,0,target);
        return ans;
    }

    /**
     *重点：有重复  每个数只能用一次
     * 如何保证每一层不会出现相同的数
     */
    public void dfs(int[] candidates,int begin,int target){
        /*if (target<0)
            return;*/
        if (target==0)
            ans.add(new ArrayList<>(path));
        //理解begin含义
        for (int i=begin;i<candidates.length;i++){
            //因为数组已经排序 这个结果小于0的话后面都小于0没必要执行
            if (target-candidates[i]<0)
                return;
            //如果在这一层当中 这个数和上一次递归的数一样 剪枝  因为结果只会是真子集 i>break 保证i-1有意义
            if (i>begin&&candidates[i]==candidates[i-1])
                continue;;
            path.add(candidates[i]);
            dfs(candidates,i+1,target-candidates[i]);
            //回溯
            path.remove(path.size()-1);
        }
    }
}
