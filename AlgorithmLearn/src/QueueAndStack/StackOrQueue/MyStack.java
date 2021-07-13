package QueueAndStack.StackOrQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列实现栈的下列操作：
 *
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 * 注意:
 *
 * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 *
 */
//一个队列也可以   先入队列然后将前面n个不断出队再入队即可
public class MyStack {
    Queue<Integer> queueF=new LinkedList();
    Queue<Integer> queueS=new LinkedList();
    int top;
    boolean isF=true;//数据在F还是S
    /** Initialize your data structure here. */
    public MyStack() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
       top=x;//每次入栈更新top值
        if(isF)
            queueF.offer(x);
        else
            queueS.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (isF){
            //数据在F将数据一个个送到S
            int num = queueF.size();
            for (int i=0;i<num-1;i++){
                int temp=queueF.poll();
                if (i==num-2) top=temp;//更新top值
                queueS.offer(temp);
            }
            isF=false;
            //留最后一个弹出返回
            return queueF.poll();
        }else{
            //数据在S将数据一个个送到F
            int num = queueS.size();
            for (int i=0;i<num-1;i++){
                int temp=queueS.poll();
                if (i==num-2) top=temp;//更新top值
                queueF.offer(temp);
            }
            isF=true;
            //留最后一个弹出返回
            return queueS.poll();
        }
    }

    /** Get the top element. */
    public int top() {
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queueS.isEmpty()&&queueF.isEmpty();

    }

    public static void main(String[] args) {
        MyStack myStack=new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.top();
        myStack.pop();
        myStack.pop();
    }
}
