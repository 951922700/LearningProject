package QueueAndStack.Stack;

import java.util.*;

/**
 * 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * 面试可能会问不用额外空间
 *辅助栈、差值
 */
public class MinStack {
    //最小栈 没有利用辅助栈
/*    List<Integer> stack;
    public MinStack() {
        stack=new ArrayList<>();
    }

    public void push(int x) {
        stack.add(x);
    }

    public void pop() {
        if (!stack.isEmpty()){
            stack.remove(stack.size()-1);
        }
    }

    public int top() {
        if (!stack.isEmpty()){
            return  stack.get(stack.size()-1);
        }
        return -1;
    }

    public int getMin() {
        Object[] list=stack.toArray();
        Arrays.sort(list);
        return (int)list[0];
    }*/


//利用辅助栈实现最小栈
    /** initialize your data structure here. */
/*    Deque<Integer> stack;
    Deque<Integer> minStack;
    public MinStack() {
        stack=new LinkedList<>();
        minStack=new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        stack.push(x);
        //对比现在栈顶元素和当前元素大小  把小的入栈
        minStack.push(Math.min(minStack.peek(),x));
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            minStack.pop();
        }
    }

    public int top() {
        //空的时候不调用
        return stack.peek();
    }

    //空的时候不调用
    public int getMin() {
         return minStack.peek();
    }*/

//利用差值 diff 2147483647 和-2147483648
    Deque<Integer> stack;
    int minValue;
    public MinStack() {
        stack=new LinkedList<>();
    }

    public void push(int x) {
        //栈为空的时候
        if (stack.isEmpty()){
            stack.push(0);//差值为0
            minValue=x;
        }else{
            int diff=x-minValue;
            if (diff>0){
                //说明x不是最小值 直接放进栈中
                stack.push(diff);
            }else{
                //说明x比最小值还小  更新最小值
                stack.push(diff);
                minValue=x;
            }
        }
    }

    public void pop() {
        int diff=stack.pop();//获取并删除栈顶元素
        if (diff<=0){
            //因为diff=x-minvalue  diff<=0说明x比较小或者等于最小值  那么x=diff+minvalue
            //所以之前的minvalue=现在的minvalue（也就是x）-diff
            minValue=minValue-diff;
        }
        //因为diff=x-minvalue  diff>0说明x比较大  那么x=diff+minvalue
        //所以minValue值不用变动
    }

    public int top() {
        int diff=stack.peek();
        return diff<=0?minValue:diff+minValue;
    }

    public int getMin() {
        return minValue;
    }
    public static void main(String[] args) {

    }
}
