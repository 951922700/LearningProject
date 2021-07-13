package QueueAndStack.StackOrQueue;

import java.util.Deque;
import java.util.LinkedList;

public class MyQueue {
    Deque<Integer> st1;
    Deque<Integer> st2;
    /** Initialize your data structure here.
    public MyQueue() {
        st1=new LinkedList<>();
        st2=new LinkedList<>();
    }

    *//** Push element x to the back of queue. *//*
    public void push(int x) {
        //假设顶部是队头  那么添加元素应该添加到尾巴  栈的push是压栈顶所以先逆序
        while (!st1.isEmpty()){
            st2.push(st1.pop());
        }
        st2.push(x);
        while (!st2.isEmpty()){
            st1.push(st2.pop());
        }
    }

    *//** Removes the element from in front of queue and returns that element. *//*
    public int pop() {
        //因为顶部是队头所以直接弹出即可
        return st1.pop();
    }

    *//** Get the front element. *//*
    public int peek() {
        return st1.peek();
    }

    *//** Returns whether the queue is empty. *//*
    public boolean empty() {
        return st1.isEmpty();
    }*/
    /**
     * 使用两个栈 入队 - O(1)O(1)，出队 - 摊还复杂度 O(1)O(1)
     */
    /** Initialize your data structure here. */
    private int front;
    public MyQueue() {
        st1=new LinkedList<>();
        st2=new LinkedList<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if (st1.isEmpty())
            front=x;
        st1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (st2.isEmpty()){
            while (!st1.isEmpty()){
                st2.push(st1.pop());
            }
        }
        return st2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (!st2.isEmpty()){
            return st2.peek();
        }
        return front;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return st1.isEmpty()&&st2.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue myQueue=new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.pop();
        System.out.println(myQueue.peek());
    }
}
