package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.List;

public class Stack_dfs_93 {
    /**
     * 93. 复原 IP 地址
     * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
     *
     * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     *
     * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
     *
     *重点：
     * String.join方法
     */
    List<String> path=new ArrayList<>();
    List<String> ans=new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        dfs(s,0,0);
        return ans;
    }

    public int judge(String s,int left,int right){
        int len=right-left+1;
        //长度大于1 第一个字符不能为0
        if (len>1&&s.charAt(left)=='0')
                return -1;
        int cur=0;
        //要在0-255之间
        for (int i=left;i<=right;i++){
            cur=cur*10+(s.charAt(i)-'0');
        }
        if (cur<0||cur>255)
            return -1;
        return cur;
    }

    public void dfs(String s,int begin,int split){
        int len=s.length();
        if (begin==len){
            //后面没有能分离的字符串了
            if (split==4){
                //如果分离次数刚好为4次
                ans.add(String.join(".",path));
                return;
            }
        }
        //剪枝 四个位置每个位置最少是一位  最多是三位  len-begin是剩余字符串长度
        if ((len-begin)<(4-split)||(len-begin)>3*(4-split))
            return;
        /**
         * 循环三次表示 1位 2位 3位
         * 当begin+i>=len的时候 循环要推出 否则越界
         */
        for (int i=0;i<3;i++){
            if (begin+i>=len)
                break;
            //获取值
            int judgedNum=judge(s,begin,begin+i);
            if (judgedNum!=-1){
                //值合法 加入path中
                path.add(judgedNum+"");
                //下一个起始位置应该在begin+i+1 同时分离次数加1
                dfs(s,begin+i+1,split+1);
                //回溯 将path的最后一个解放
                path.remove(path.size()-1);
            }
        }
    }
}
