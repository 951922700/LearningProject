package QueueAndStack.Stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 每日温度
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 */
public class stack02 {
    //暴力
   /* public int[] dailyTemperatures(int[] T) {
        int length=T.length;
        int [] days=new int[length];
        for (int i=0;i<length;i++){
            int temp=T[i];
            int step=1;
            boolean isHave=false;//标记是否有更大的
            for (int j=i+1;j<length;j++){
                if (T[j]<=temp) step++;
                else {
                    //找到一个更高的温度 推出循环
                    isHave=true;//标记，有更大的
                    days[i]=step;
                    break;
                }
            }
            if (!isHave) days[i]=0;
        }
        return days;
    }*/
   //单调栈  从大到小  存温度下标 判别是否需要使用单调栈，如果需要找到左边或者右边第一个比当前位置的数大或者小，
    // 则可以考虑使用单调栈；单调栈的题目如矩形米面积等等
    public int[] dailyTemperatures(int[] T) {
        int length=T.length;
        int days[]=new int[length];
        Deque<Integer> stack=new LinkedList<>();
        for (int i=0;i<length;i++){
            int temp=T[i];
            //当栈不为空  当前元素比栈顶元素大
            while(!stack.isEmpty()&&T[stack.peek()]<temp){
                int index=stack.pop();
                days[index]=i-index;//当前天数等于当前下标-栈存储下标
            }
            //栈为空  或者当前元素<=栈顶元素
            stack.push(i);
        }
        return days;
    }
}
