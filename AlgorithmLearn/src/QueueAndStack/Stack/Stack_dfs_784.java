package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.List;

public class Stack_dfs_784 {
    /**
     * 784. 字母大小写全排列
     * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
     *
     *
     *
     * 示例：
     * 输入：S = "a1b2"
     * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
     *
     * 输入：S = "3z4"
     * 输出：["3z4", "3Z4"]
     *
     * 输入：S = "12345"
     * 输出：["12345"]
     */
    List<String> ans=new ArrayList<>();
    public List<String> letterCasePermutation(String S) {
        char chs[]=S.toCharArray();
        if (S.length()==0)
            return ans;
        dfs(chs,0,"");
        return ans;
    }

    public void dfs(char chs[],int index,String result){
        if (index==chs.length){
            ans.add(result);
            return;
        }
        if (Character.isDigit(chs[index])){
            //是数字直接进入递归
            dfs(chs,index+1,result+chs[index]);
        }else if(Character.isUpperCase(chs[index])){
            //是大写字母
            dfs(chs,index+1,result+chs[index]);
            //变小写
            dfs(chs,index+1,result+Character.toLowerCase(chs[index]));
        }else{
            //是小写
            dfs(chs,index+1,result+chs[index]);
            //变大写
            dfs(chs,index+1,result+Character.toUpperCase(chs[index]));
        }
    }
}
