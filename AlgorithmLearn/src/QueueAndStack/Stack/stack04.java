package QueueAndStack.Stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class stack04 {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack=new LinkedList<>();
        int length=tokens.length;
        for (String token:tokens) {
            if(token.equals("+")||token.equals("-")||token.equals("*")||token.equals("/"))
            {
                int y=stack.pop();
                int x=stack.pop();
                stack.push(calculate(x,y,token));
            }
            else
                stack.push(new Integer(token));
        }
        return stack.peek();
    }


    public int calculate(int x,int y,String s){
        if (s.equals("+")) return x+y;
        else if(s.equals("-")) return x-y;
        else if(s.equals("*")) return x*y;
        else return x/y;
    }

    //这个不能判断负数
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    public static void main(String[] args) {
        stack04 stack04=new stack04();
        System.out.println(stack04.evalRPN(new String[]{"2","1","+","3","*"}));
    }
}
