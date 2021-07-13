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
public class stack03 {
    //暴力  计算每个柱子能接的水的数量
/*    public int trap(int[] height) {
        int length=height.length;
        int ans=0;
        for (int i=1;i<length-1;i++){
            int max_left=0;
            int max_right=0;
            for (int j=i;j>=0;j--){
                max_left=Math.max(max_left,height[j]);//最大值至少也是自己
            }
            for (int j=i;j<length;j++){
                max_right=Math.max(max_right,height[j]);
            }
            ans+=Math.min(max_left,max_right)-height[i];
        }

        return ans;
    }*/

    //暴力的时候因为每一次都在找左右最大值 这个时候用动态规划先存好最大值
 /*   public int trap(int[] height) {
        int length=height.length;
        if (length==0) return 0;
        int ans=0;
        int max_left[]=new int[length];
        int max_right[]=new int[length];
        //求左边最大值
        max_left[0]=height[0];
        for (int i=1;i<length;i++){
            max_left[i]=Math.max(max_left[i-1],height[i]);
        }
        //求右边最大值
        max_right[length-1]=height[length-1];
        for (int i=length-2;i>=0;i--){
            max_right[i]=Math.max(max_right[i+1],height[i]);
        }
        for (int i=1;i<length-1;i++){
            ans+=Math.min(max_left[i],max_right[i])-height[i];
        }
        return ans;
    }*/

    /**
     * 单调栈  思路是  当遇到一个比栈顶小或者等于的数时压栈
     * 当遇到一个比栈顶大的数时  可以开始计算装水量 而且是计算左柱子与右柱子距离乘以min(左，右)-栈顶高度的乘积
     * 此时栈顶的前一个柱子肯定比栈顶高 也就是左右边界确定  distance=current-栈顶左边索引-1
     *height=min(左，右)-栈顶
     * 水量=distance*height
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int ans=0,current=0;
        Deque<Integer> stack=new LinkedList<>();
        while(current<height.length){
            while (!stack.isEmpty()&&height[current]>height[stack.peek()]){
                int top=stack.pop();
                if (stack.isEmpty())
                    break;
                int distance=current-stack.peek()-1;
                int perHeight=Math.min(height[current],height[stack.peek()])-height[top];
                ans+=distance*perHeight;
            }
            stack.push(current++);
        }
        return ans;
    }
    public static void main(String[] args) {
        stack03 stack03=new stack03();
        System.out.println(stack03.trap(new int[]{2,1,0,1,3}));
    }
}
