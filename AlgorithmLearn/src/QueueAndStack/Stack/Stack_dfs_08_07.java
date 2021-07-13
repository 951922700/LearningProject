package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stack_dfs_08_07 {
    /**
     * 面试题 08.07. 无重复字符串的排列组合
     * 无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
     *
     * 示例1:
     *
     *  输入：S = "qwe"
     *  输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
     * 示例2:
     *
     *  输入：S = "ab"
     *  输出：["ab", "ba"]
     */
    //List<String> path=new ArrayList<>();
    List<String> ans=new ArrayList<>();
    StringBuilder path=new StringBuilder();

    public String[] permutation(String S) {
        boolean[] used=new boolean[S.length()];
        char[] chs=S.toCharArray();
        dfs(chs,0,used);
        return ans.toArray(new String[ans.size()]);
    }

    public void dfs(char[] chs,int index,boolean[] used){
        if (chs.length==index){
            //ans.add(String.join("",path));
            ans.add(path.toString());
            return;
        }
        for (int i=0;i<chs.length;i++){
            if (!used[i]){
                //没用过放入路径中
                used[i]=true;
                path.append(chs[i]);
                dfs(chs,index+1,used);
                path.deleteCharAt(path.length()-1);
                used[i]=false;//回溯
            }
        }
    }
}
