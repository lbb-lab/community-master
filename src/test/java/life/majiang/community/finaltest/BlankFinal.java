package life.majiang.community.finaltest;

/**
 * @author: liu bin bin
 * @Date: 2021/3/13 21:27
 * @Description:
 */
public class BlankFinal {
    final int i = 0;
    final int j;      //final定义的成员变量必须进行初始化，否侧会报错，下面的构造函数对两个都进行了初始化
    final Poppet p; //final定义的成员变量必须进行初始化，否侧会报错，下面的构造函数对两个都进行了初始化
    BlankFinal(){
        j = 1;
        p = new Poppet();
    }

    BlankFinal(int x){
        j = x;
        p = new Poppet();
    }

    public static void main(String[] args){
        BlankFinal bf = new BlankFinal();
    }
}
