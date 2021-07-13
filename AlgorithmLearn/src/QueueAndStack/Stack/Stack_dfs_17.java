package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stack_dfs_17 {
    /**
     * 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     */
    //用一个map存储数字和字母的映射
    Map<Character,Character[]> reflect=new HashMap<Character,Character[]>(){
        {
            put('2',new Character[]{'a','b','c'});
            put('3',new Character[]{'d','e','f'});
            put('4',new Character[]{'g','h','i'});
            put('5',new Character[]{'j','k','l'});
            put('6',new Character[]{'m','n','o'});
            put('7',new Character[]{'p','q','r','s'});
            put('8',new Character[]{'t','u','v'});
            put('9',new Character[]{'w','x','y','z'});
        }
    };
    List<String> ans=new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        char chs[]=digits.toCharArray();
        //digits为"“的情况
        if (digits.length()==0)
            return ans;
        dfs(chs,0,"");
        return ans;
    }

    public void dfs(char chs[],int index,String result){
        //当长度达到最大长度时
        if (index==chs.length)
        {
            ans.add(result);
            return;
        }

        Character temp[]=reflect.get(chs[index]);
        for (int i=0;i<temp.length;i++){
            dfs(chs,index+1,result+temp[i]);
        }
    }
}
