package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stack_dfs_39 {
    /**
     * 39. 组合总和
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的数字可以无限制重复被选取。
     *
     * 说明：
     *
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1：
     *
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为：
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * 示例 2：
     *
     * 输入：candidates = [2,3,5], target = 8,
     * 所求解集为：
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     *
     */
    List<List<Integer>> ans=new ArrayList<>();
    List<Integer> path=new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates,0,target);
        return ans;
    }

    /**
     * 重点：
     * 组合  可重复 但不看顺序
     * 以target为顶点 不断-元素  直到<=0
     * 但是减第二次节点不能再用前面的节点  比如  2  3  1    第一次以2开头的包括重复的组合  在第二次3开头的时候就不需要有2了
     * @param candidates
     * @param target
     */
    public void dfs(int[] candidates,int begin,int target){
        /*if (target<0)
            return;*/
        if (target==0)
            ans.add(new ArrayList<>(path));
        //理解begin含义
        for (int i=begin;i<candidates.length;i++){
            if (target-candidates[i]<0)
                return;
            path.add(candidates[i]);
            dfs(candidates,i,target-candidates[i]);
            //回溯
            path.remove(path.size()-1);
        }
    }
}
