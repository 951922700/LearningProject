package QueueAndStack.Stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 */
public class stack01 {
    //这种方法当左括号很多的时候  会有很多没用意义的比较
/*    public boolean isValid(String s) {
        char ch[]=s.toCharArray();
        Deque<Character> stack=new LinkedList<>();
        if (s.length()%2==1) return false;
        for (int i=0;i<ch.length;i++){
            if (stack.isEmpty())
                stack.push(ch[i]);
            else{//判断当前字符和栈顶字符是否对应
                char temp=stack.poll();
                if (isequal(temp,ch[i]))
                    continue;//对应，退出本次循环
                else{
                    //不对应，一起压入栈
                    stack.push(temp);
                    stack.push(ch[i]);
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isequal(char x,char y){
        if (x=='('&&y==')') return true;
        if (x=='['&&y==']') return true;
        if (x=='{'&&y=='}') return true;
        return false;
    }*/
    //思路：遇到左括号直接压栈  遇到右括号看看与栈顶是否对应  用map存储括号
    public boolean isValid(String s) {
        char ch[]=s.toCharArray();
        Deque<Character> stack=new LinkedList<>();
        if (ch.length%2==1)
            return false;
        Map<Character,Character> map=new HashMap<Character, Character>(){{
            put('}','{');
            put(']','[');
            put(')','(');
        }};
        for (int i=0;i<ch.length;i++){
            //是左括号
            if (!map.containsKey(ch[i]))
                stack.push(ch[i]);
            else{
                //是右括号
                if (stack.isEmpty()||stack.peek()!=map.get(ch[i])){
                    //没对应上
                    return false;
                }else
                    stack.pop();
            }
        }
        return stack.isEmpty();
    }
    public static void main(String[] args) {
        stack01 stack01=new stack01();
        System.out.println(stack01.isValid("{[]}"));
    }
}
