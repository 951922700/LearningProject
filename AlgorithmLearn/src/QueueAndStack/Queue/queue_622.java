package QueueAndStack.Queue;

public class queue_622 {
    /**
     * 622. 设计循环队列
     * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
     *
     * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
     *
     * 你的实现应该支持如下操作：
     *
     * MyCircularQueue(k): 构造器，设置队列长度为 k 。
     * Front: 从队首获取元素。如果队列为空，返回 -1 。
     * Rear: 获取队尾元素。如果队列为空，返回 -1 。
     * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
     * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
     * isEmpty(): 检查循环队列是否为空。
     * isFull(): 检查循环队列是否已满。
     */
    //用数组方式实现 tail指针指向尾部元素的后一个
    class MyCircularQueue {
        int values[];//数组长度
        int head;
        int tail; // 不直接指向尾部
        int size;//当前长度
        int capacity;
        public MyCircularQueue(int k) {
            values=new int[k];
            head=tail=size;
            capacity=k;
        }

        public boolean enQueue(int value) {
            if (isFull()){
                //满了
                return false;
            }
            values[tail]=value;
            tail=(tail+1)%capacity;
            size++;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()){
                return false;
            }
            head=(head+1)%capacity;
            size--;
            return true;
        }

        public int Front() {
            if (!isEmpty())
                return values[head];
            return -1;
        }

        public int Rear() {
            if (!isEmpty()){
                if (tail==0)
                    return values[capacity-1];
                else
                    return values[tail-1];
            }
            return -1;
        }

        public boolean isEmpty() {
            if (size==0)
                return true;
            return false;
        }

        public boolean isFull() {
            if (values.length==size)
                return true;
            return false;
        }
    }
}
