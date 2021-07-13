package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.List;

public class Stack_dfs_216 {
    /**
     * 216. 组合总和 III
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     *
     * 说明：
     *
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1:
     *
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 示例 2:
     *
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     */

    List<Integer> path=new ArrayList<>();
    List<List<Integer>> ans=new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(k,n,1);
        return ans;
    }

    public void dfs(int k,int n,int index){
        if (path.size()==k){
            //够长度了  判断n是否等于0
            if (n==0)
                ans.add(new ArrayList<>(path));
        }
        for (int i=index;i<=9;i++){
            if (n-i<0){
                //n是target
                //目标值小于该值没有遍历的需要了
                return;//continue也可以return更彻底
            }
            path.add(i);
            dfs(k,n-i,i+1);
            path.remove(path.size()-1);
        }
    }
}
