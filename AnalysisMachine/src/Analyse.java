import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Analyse{
    private Map<String,Integer> encode;//单词,种别码的map
    //private Map<Integer,Integer> token;//保存识别出来的 SIMPLE CHAR 不能用map因为要按识别出的顺序输出
    private Token tokens[];//Token数组来存放识别出的 SIMPLE CHAR
    private Map<String,Integer> identifier;//这个map的作用是将识别出来的标识符放进去，以便下一次识别出相同的标识符的时候能够做出判断
    private String line;//一行一行的读取，当前行的字符串
    private int row;//当前所在的行数
    private Integer identifierNum;//标识符数量
    private int tokenNum;//token的数量方便输出
    BufferedReader br;

    public Analyse(int a){
        //System.out.println("执行了");
        //初始化参数
        encode=new HashMap<>();
        tokens=new Token[100];
        //循环声明
        for (int i=0;i<100;i++)
            tokens[i]=new Token();
        identifier=new HashMap<>();
        line="";
        row=0;
        identifierNum=0;
        tokenNum=0;
        encode.put("and",1);encode.put("array",2);
        encode.put("begin",3);encode.put("bool",4);
        encode.put("call",5);encode.put("case",6);
        encode.put("char",7);encode.put("constant",8);
        encode.put("dim",9);encode.put("do",10);

        encode.put("else",11);encode.put("end",12);
        encode.put("false",13);encode.put("for",14);
        encode.put("if",15);encode.put("input",16);
        encode.put("integer",17);encode.put("not",18);
        encode.put("of",19);encode.put("or",20);

        encode.put("output",21);encode.put("procedure",22);
        encode.put("program",23);encode.put("read",24);
        encode.put("real",25);encode.put("repeat",26);
        encode.put("set",27);encode.put("stop",28);
        encode.put("then",29);encode.put("to",30);

        encode.put("true",31);encode.put("until",32);
        encode.put("var",33);encode.put("while",34);
        encode.put("write",35);
    }

    public void addToken(int type,String typeNum){
        tokens[tokenNum].type=type;
        tokens[tokenNum++].typeNum=typeNum;
    }

    public void print(){
        for(int i=0;i<tokenNum;i++){
            if(i%5==0) System.out.print("\n");
            System.out.print("("+tokens[i].type+","+tokens[i].typeNum+")  ");
        }
    }

    /**
     * 判断是否是关键词或者标识符
     * @param pos
     * @return
     */
    public int isKeyWordOrIdentifier(int pos){
        String temp="";
        int npos;
        for(npos=pos;npos<line.length();npos++){
            if(line.charAt(npos)>='a'&&line.charAt(npos)<='z' || line.charAt(npos)>='A'&&line.charAt(npos)<='Z'||line.charAt(npos)>='0'&&line.charAt(npos)<='9')
                //输入的如果是字母或者数字
                temp+=line.charAt(npos);
            else break;
        }
        //读取完毕判断是否是关键词或者标识符
        if(encode.containsKey(temp)){
            //判断 是否在关键词和种别码的关系中
            //如果有则是
            addToken(encode.get(temp),"-");
            return npos;
        }
        //如果上面的循环没有return那么他不是关键词，这个时候就是标识符了
        //先判断这儿标识符在不在
        if(identifier.containsKey(temp)){
            //存在
            addToken(36,identifier.get(temp).toString());
        }
        else {
            //不存在，新的
            addToken(36,(++identifierNum).toString());
            //放进map中
            identifier.put(temp,identifierNum);
        }
        return npos;
    }

    /**
     * 是否是数字
     * @param pos
     * @return
     */
    public int isNum(int pos){
        String temp="";
        int npos;
        for (npos=pos;npos<line.length();npos++){
            if(line.charAt(npos)>='0'&&line.charAt(npos)<='9')
                temp+=line.charAt(npos);
            else break;
        }
        //识别完毕
        if(identifier.containsKey(temp)){
            //存在
            addToken(37,identifier.get(temp).toString());
        }else{
            //不存在
            addToken(37,(++identifierNum).toString());
            identifier.put(temp,identifierNum);
        }
        return npos;
    }


    /**
     * 是否是字符串
     * @param pos
     * @return
     */
    public int isString(int pos){
        String temp="'";
        int npos=pos;
        while(true){
            if(npos>=line.length()){
                //如果读到末尾还没有读到',说明缺少'要报错
                System.out.println("缺少 ' 在("+row+","+(npos+1)+")坐标位置\n");
                return npos;
            }
            if(line.charAt(npos)!='\'') temp+=line.charAt(npos);
            else{
                //遇到 '
                temp+=line.charAt(npos);
                npos++;
                break;
            }
            npos++;
        }
        //读取完毕
        if(identifier.containsKey(temp)){
            //存在
            addToken(38,identifier.get(temp).toString());
        }else{
            //不存在
            addToken(38,(++identifierNum).toString());
            identifier.put(temp,identifierNum);
        }
        return npos;
    }

    /**
     * 是否是注解
     * @param pos
     * @return
     */
    public int isNote(int pos){//加多一个参数，是不是上一行有了/*
        while(true){
            if(pos==line.length()-2)//防止出现一行只有一个字符，越界
            if(line.charAt(pos)=='*'&&line.charAt(pos+1)=='/') return pos+2;
            /**if(pos>=line.length()-2){
                //如果现在到了倒数第二个字符但是又不满足第一个条件则报错
                System.out.println("缺少 * / 在("+row+","+(pos+1)+")坐标位置\n");
                return pos+2;
            }**/
            if(pos>=line.length()-1&&line.charAt(pos)!='*'){
                //直到最后一行也没有出现*/一起或者最后一个字符不是*
                //不报错，有可能/*在下一行告知程序读下一行的内容并且寻找*/
                try{

                    if((line=br.readLine())!=null){
                        row++;
                        //继续读取下一行
                        pos=isNote(0);//从新的一行的第一个字符开始找 进行递归查找
                    }
                    return pos;
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
            if (pos>=line.length()-1&&line.charAt(pos)=='*'){
                //最后一个字符是*
                //读下一行看第一个是否是/，是的话报错
                try{
                    if((line=br.readLine())!=null){
                        row++;
                        //继续读取下一行
                        if(line.charAt(0)=='/'){
                            //如果是/报错 继续进行后面的
                            System.out.println("错误类型：*/之间存在换行在("+row+","+"0"+")坐标位置\n");
                        }
                        pos=isNote(0);//从新的一行的第一个字符开始找 进行递归查找
                    }
                    return pos;
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            pos++;
        }
    }

    public void run(){
        String filePath=null;
        FileReader fileReader=null;

        //1.输出作者姓名、班级学号
        System.out.println("2018级计算机科学与技术");
        System.out.println("小组成员：蓝永龙     何定炜     朱舟明");
        System.out.println("学号：2018071027  2018071027 2018071027");
        System.out.print("请输入文件位置：");
        //2.控制台输入读取文件的位置
        Scanner scanner=new Scanner(System.in);
        //BufferedReader filePathbr = new BufferedReader(new InputStreamReader(System.in));
        try {
            //捕获异常
            //filePath=filePathbr.readLine();
            filePath=scanner.nextLine();
            System.out.println(filePath);
            //按字符读取文件(PS:读取的文件需要是utf-8格式否则中文乱码)     先实现读取每个字符并输出
            fileReader=new FileReader(filePath);
            //准备按行读取 BufferedReader
             br=new BufferedReader(fileReader);
            while((line=br.readLine())!=null)//没有可读取字符时返回-1
            {
                //System.out.println("???");
                if(line.equals("")) break;//空行跳过
                ++row;
                for(int pos=0;pos<line.length();){
                    //System.out.println("进入循环");
                    if(line.charAt(pos)==' ') pos++;//空格跳过
                    else if(line.charAt(pos)>='a'&&line.charAt(pos)<='z'||line.charAt(pos)>='A'&&line.charAt(pos)<='Z'){
                        //输入的是字母
                        pos=isKeyWordOrIdentifier(pos);
                    }
                    else if(line.charAt(pos)>='0'&&line.charAt(pos)<='9') pos=isNum(pos);
                    else if(line.charAt(pos)=='\'') pos=isString(pos+1);
                    else if(line.charAt(pos)=='(') {addToken(39,"-");pos++;}
                    else if(line.charAt(pos)==')') {addToken(40,"-");pos++;}
                    else if(line.charAt(pos)=='*') {addToken(41,"-");pos++;}
                    else if(line.charAt(pos)=='+') {addToken(43,"-");pos++;}
                    else if(line.charAt(pos)==',') {addToken(44,"-");pos++;}
                    else if(line.charAt(pos)=='-') {addToken(45,"-");pos++;}
                    else if(line.charAt(pos)=='.'){
                        //双界符
                        if(pos+1<line.length()&&line.charAt(pos+1)=='.'){
                            addToken(47,"-");
                            pos+=2;
                        }else{
                            addToken(46,"-");
                            pos++;
                        }
                    }
                    else if(line.charAt(pos)=='/'){
                        //双界符
                        if(pos+1<line.length()&&line.charAt(pos+1)=='*'){
                            pos=isNote(pos+2);
                            if(line==null) break;
                        }else{
                            addToken(48,"-");
                            pos++;
                        }
                    }
                    else if(line.charAt(pos)==':'){
                        //双界符
                        if(pos+1<line.length()&&line.charAt(pos+1)=='='){
                            addToken(51,"-");
                            pos+=2;
                        }else{
                            addToken(50,"-");
                            pos++;
                        }
                    }
                    else if(line.charAt(pos)==';'){addToken(52,"-");pos++;}
                    else if(line.charAt(pos)=='<'){
                        //双界符
                        if(pos+1<line.length()&&line.charAt(pos+1)=='='){
                            addToken(54,"-");
                            pos+=2;
                        }else if(pos+1<line.length()&&line.charAt(pos+1)=='>'){
                            addToken(55,"-");
                            pos+=2;
                        }else{addToken(53,"-");pos++;}
                    }
                    else if(line.charAt(pos)=='='){addToken(56,"-");pos++;}
                    else if(line.charAt(pos)=='>'){
                        //双界符
                        if(pos+1<line.length()&&line.charAt(pos+1)=='='){
                            addToken(58,"-");
                            pos+=2;
                        }else{
                            addToken(57,"-");
                            pos++;
                        }
                    }
                    else if(line.charAt(pos)=='['){addToken(59,"-");pos++;}
                    else if(line.charAt(pos)==']'){addToken(60,"-");pos++;}
                    else{
                        //报错
                        System.out.println("非法字符"+line.charAt(pos)+"在("+row+","+(pos+1)+")坐标位置\n");
                        pos++;
                        /*for (;pos<line.length();){
                            if (line.charAt(pos)!=' '){
                                //如果不是空格++  非法符号相连的不要了
                                pos++;
                            }else break;
                        }*/
                    }
                }
            }
            print();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
