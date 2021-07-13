package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Stack_dfs_08_08 {
    /**
     * 面试题 08.08. 有重复字符串的排列组合
     * 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
     *
     * 示例1:
     *
     *  输入：S = "qqe"
     *  输出：["eqq","qeq","qqe"]
     * 示例2:
     *
     *  输入：S = "ab"
     *  输出：["ab", "ba"]
     */
    List<String> ans=new ArrayList<>();
    StringBuilder path=new StringBuilder();

    public String[] permutation(String S) {
        boolean[] used=new boolean[S.length()];
        char[] chs=S.toCharArray();
        Arrays.sort(chs);
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
                if (i>0&&chs[i]==chs[i-1]&&!used[i-1])
                    continue;
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
