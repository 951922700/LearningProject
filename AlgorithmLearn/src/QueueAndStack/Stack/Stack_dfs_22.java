package QueueAndStack.Stack;

import java.util.*;

public class Stack_dfs_22 {
    /**
     * 22. 括号生成
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     * 示例 2：
     *
     * 输入：n = 1
     * 输出：["()"]
     */
    List<String> ans=new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if (n==0)
            return ans;
        //dfs(n,0,"");
        dfs("",n,n);
        return ans;
    }

/*    public void dfs(int n,int index,String result){
        if (index==2*n){
            if (isValid(result)){
                //合法再加入
                ans.add(result);
            }
            return;
        }
       if (index==0){
           //第一次必定是左括号
           dfs(n,index+1,result+'(');
       }else{
           //否则先判断是否要剪枝
           if (!isCut(result+'(',n))
               dfs(n,index+1,result+'(');
           if (!isCut(result+')',n))
               dfs(n,index+1,result+')');
       }
    }*/

    //看看是否要剪枝
    public boolean isCut(String str,int n){
        char[] chs=str.toCharArray();
        //统计括号数量个数
        int left=0,right=0;
        for (char cur: chs) {
            if (cur=='(')
                left++;
            else
                right++;
        }
        //某个括号大于对数 则把他剪枝
        if (left>n||right>n)
            return true;
        return false;
    }

    //判断是否合法
    public boolean isValid(String result){
        char[] chs=result.toCharArray();
        Deque<Character> stack=new ArrayDeque<>();
        for (int i=0;i<chs.length;i++){
            //如果是左括号入栈
            if (chs[i]=='('){
                stack.push(chs[i]);
            }else{
                //是右括号
                //栈为空 非法
                if (stack.isEmpty())
                    return false;
                //不为空 把一个左括号弹出栈
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    //优化  利用左括号数和右括号数来剪枝  只会出现满足要求的组合
    public void dfs(String str,int left,int right){
        if (left==0&&right==0){
            ans.add(str);
            return;
        }
        if (left==right){
            //相等情况下只能放左
            dfs(str+"(",left-1,right);
        }else if (left<right){
            //左括号数小于右括号  左右都能放
            if (left>0)
                dfs(str+"(",left-1,right);
            dfs(str+")",left,right-1);
        }
    }
    public static void main(String[] args) {
        Stack_dfs_22 s=new Stack_dfs_22();
        s.generateParenthesis(1);
    }
}
