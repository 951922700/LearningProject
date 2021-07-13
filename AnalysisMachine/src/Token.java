public class Token {
    public int type;//种别码
    public String typeNum;//非标识符、整数、字符常熟为- 否则为对应的顺序增加的num

    public Token() {
        type = 0;
        typeNum = "";
    }
}
