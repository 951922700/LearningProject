

public class Conditon {
    private int A;
    private int B;
    private int X;
    private int Y;

    /**
     * 初始化设置初值
     * @param A
     * @param B
     * @param X
     */
    public void init(int A,int B,int X){
        this.A=A;
        this.B=B;
        this.X=X;
    }

    /**
     * 逻辑处理
     */
    public void cal(){
        if (A>0&&B==1){
            X=X/A;
        }

        if (A==2||X>1){
            X=X+1;
        }

        Y=A+B+X;
    }

    public int getY(){
        return Y;
    }
}
