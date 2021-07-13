package QueueAndStack.Stack;

import java.util.Deque;
import java.util.LinkedList;

public class SingleStack_739 {
    /**
     * 739. 每日温度
     * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，
     * 至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     *
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     *
     * 解决方法：
     * 单调栈
     */
    public int[] dailyTemperatures(int[] T) {
        int days[]=new int[T.length];
        Deque<Integer> stack=new LinkedList<>();
        for (int i=0;i<T.length;i++){
            if (stack.isEmpty()){
                //栈是空的  直接入栈
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty()&&T[i]>T[stack.peek()]){
                //当前元素大于栈顶  出栈
                int index=stack.pop();
                days[index]=i-index;
            }
            stack.push(i);
        }
        return days;
    }
    }
