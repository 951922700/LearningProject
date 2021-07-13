package QueueAndStack.Queue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 *
 *  
 *
 * 示例 1:
 *
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * 示例 2:
 *
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 * 示例 3:
 *
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 * 示例 4:
 *
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 *
 */
public class queue02 {
    public String plusOne(String str,int j){
        char ch[]=str.toCharArray();
        if (ch[j]=='9'){
            ch[j]='0';
        }else{
            ch[j]+=1;
        }
        return new String(ch);
    }

    public String minusOne(String str,int j){
        char ch[]=str.toCharArray();
        if (ch[j]=='0'){
            ch[j]='9';
        }else{
            ch[j]-=1;
        }
        return new String(ch);
    }

    //单向dfs
    public int dfs(String[] deadends, String target){
        Queue<String> queue=new LinkedList<>();
        Set<String> deads=new HashSet<>();//存入死亡数字
        Set<String> visited=new HashSet<>();//已经访问过的加入进去防止走回头路
        //将死亡数字加入集合
        for (String s:deadends) {
            deads.add(s);
        }
        //将第一个元素加入队列
        queue.offer("0000");
        visited.add("0000");
        int step=0;
        while (!queue.isEmpty()){
            //将队列所有节点扩散
            int size=queue.size();
            for (int j=0;j<size;j++){
                String temp=queue.poll();
                if (temp.equals(target)) return step;
                //在这里判断是否在死亡数字中  如果存在就不执行后面那段
                if(!deads.contains(temp)) {
                    for (int i = 0; i < 4; i++) {
                        String up = plusOne(temp, i);
                        if (!visited.contains(up)){
                            queue.offer(up);
                            visited.add(up);
                        }
                        String down = minusOne(temp, i);
                        if (!visited.contains(down)){
                            queue.offer(down);
                            visited.add(down);
                        }

                    }
                }
            }
        step++;
        }
        return -1;
    }
    //双向dfs  必须知道终点  扩散的指数增长少了
    public int doubleDfs(String[] deadends, String target){
        //不用队列了，用两个集合存
        Set<String> q1=new HashSet<>();
        Set<String> q2=new HashSet<>();
        Set<String> deads=new HashSet<>();
        Set<String> visited=new HashSet<>();

        //将死亡数字加入集合
        for (String s:deadends) {
            deads.add(s);
        }
        q1.add("0000");
        q2.add(target);
        int step=0;

        while (!q1.isEmpty()&&!q2.isEmpty()){
            //用一个临时set来存扩散结果
            Set<String> temp=new HashSet<>();
            //进行遍历
            for (String str:q1) {
                if (deads.contains(str)) continue;
                if (q2.contains(str)) return step;//如果两个集合的元素有交集就说明达到目的了
                visited.add(str);
                //对未访问过的元素进行扩散
                for (int i = 0; i < 4; i++) {
                    String up = plusOne(str, i);
                    if (!visited.contains(up)){
                        temp.add(up);
                    }
                    String down = minusOne(str, i);
                    if (!visited.contains(down)){
                        temp.add(down);
                    }

                }
            }
            step++;
            //进行交换
            q1=q2;
            q2=temp;
            //相当于下一次就是q2  轮回扩散
        }
        return -1;
    }
    public int openLock(String[] deadends, String target) {
        int resultCode=doubleDfs(deadends,target);
        return resultCode;
    }
}
