package QueueAndStack;

//数组实现循环队列
/*public class MyCircularQueue {
    //可以把tail去掉  用(head+length-1)%size
    int[] queue=null;
    int head=0,tail=0,length=0,size=0;
    *//**
 * Initialize your data structure here. Set the size of the queue to be k.  Insert an element into the circular queue. Return true if the operation is successful.  Delete an element from the circular queue. Return true if the operation is successful.  Get the front item from the queue.  Get the last item from the queue.  Checks whether the circular queue is empty or not.
 * Insert an element into the circular queue. Return true if the operation is successful.  Delete an element from the circular queue. Return true if the operation is successful.  Get the front item from the queue.  Get the last item from the queue.  Checks whether the circular queue is empty or not.
 *//*
    public MyCircularQueue(int k) {
        queue=new int[k];
        size=k;
       // System.out.println(queue.size());
    }

    *//** Insert an element into the circular queue. Return true if the operation is successful. *//*
    public boolean enQueue(int value) {
        if (!isFull()){
            queue[tail]=value;
            tail=(tail+1)%size;
            length++;
            return true;
        }else return false;
    }

    *//** Delete an element from the circular queue. Return true if the operation is successful. *//*
    public boolean deQueue() {
        if (!isEmpty()){
            head=(head+1)%size;
            length--;
            return true;
        }else return false;
    }

    *//** Get the front item from the queue. *//*
    public int Front() {
        if (isEmpty()) return -1;
        else return queue[head];
    }

    *//** Get the last item from the queue. *//*
    public int Rear() {
        if (isEmpty()) return -1;
        else {
            if (tail==0) return  queue[size-1];
            else return queue[tail-1];
        }
    }

    *//** Checks whether the circular queue is empty or not. *//*
    public boolean isEmpty() {
        if (length==0) return true;
        else return false;
    }

    */

/** Checks whether the circular queue is full or not. *//*
    public boolean isFull() {
        if (length==size) return true;
        else return false;
    }

    }*/

class Node {
    public int value;
    public Node nextNode;

    public Node(int value) {
        this.value = value;
        this.nextNode = null;
    }
}

//单链表实现
public class MyCircularQueue {
    private Node head, tail;
    private int length;
    private int size;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        length=0;
        size=k;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
      if (isFull())
          return false;
      Node node=new Node(value);

      if (isEmpty()){
          head=tail=node;
      }else{
          tail.nextNode=node;
          tail=node;
      }
      length++;
      return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty())
            return false;
        head=head.nextNode;
        length--;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty())
            return -1;
        return head.value;
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty())
            return -1;
        return tail.value;
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
       return length==0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return length==size;
    }

}




