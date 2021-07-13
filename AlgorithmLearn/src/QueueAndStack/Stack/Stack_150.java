package QueueAndStack.Stack;

import java.util.Deque;
import java.util.LinkedList;

public class Stack_150 {
    /**
     * 150. 逆波兰表达式求值
     * 根据 逆波兰表示法，求表达式的值。
     *
     * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     *
     *
     *
     * 说明：
     *
     * 整数除法只保留整数部分。
     * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack=new LinkedList<>();
        for (int i=0;i<tokens.length;i++){
            if (tokens[i].equals("+")){
                int right=stack.pop();
                int left=stack.pop();
                int result=left+right;
                stack.push(result);
            }else if (tokens[i].equals("-")){
                int right=stack.pop();
                int left=stack.pop();
                int result=left-right;
                stack.push(result);
            } else if (tokens[i].equals("*")){
                int right=stack.pop();
                int left=stack.pop();
                int result=left*right;
                stack.push(result);
            } else if (tokens[i].equals("/")){
                int right=stack.pop();
                int left=stack.pop();
                int result=left/right;
                stack.push(result);
            }else{
                //数字
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.pop();
    }
}
