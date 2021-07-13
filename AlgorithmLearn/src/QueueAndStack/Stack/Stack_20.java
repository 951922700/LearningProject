package QueueAndStack.Stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Stack_20 {
    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     */
    public boolean isValid(String s) {
        char[] strs=s.toCharArray();
        Deque<Character> stack=new LinkedList<>();
        Map<Character,Character> map=new HashMap<Character, Character>(){{
            put('}','{');
            put(')','(');
            put(']','[');
        }};
        if (strs.length%2==1)
            return false;
        for (int i=0;i<strs.length;i++){
            if (!map.containsKey(strs[i])){
                stack.push(strs[i]);
            }else{
                if (stack.isEmpty()||map.get(strs[i])!=stack.peek()){
                    return false;
                }else
                    stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
