package life.majiang.community.finaltest;

/**
 * @author: liu bin bin
 * @Date: 2021/3/13 20:58
 * @Description:
 */
public class FinalData {
    final int i1 = 9;
    static final int I2 = 99;  //编译期确定的final的常量要大写
    public static final int I3 = 39; //编译期确定的final的常量要大写
    final int i4 = (int)(Math.random()*20);  //编译期无法确定，小写
    static final int i5 = (int)(Math.random()*20); //编译期无法确定，小写

    Value v1 = new Value();
    final Value v2 = new Value();
    static final Value v3 = new Value();
    final int[] a = {1,2,3,4,5,6};

    public void print(String id){
        System.out.println(id+":"+"i4="+i4+",i5="+i5);
    }

    public static void main(String[] args){
        FinalData fd1 = new FinalData();
        fd1.v2.i++;
        fd1.v1 = new Value();
        for (int i=0;i<fd1.a.length;i++){
            fd1.a[i]++; //a里面的内容可以改变但是这个句柄a是不能改变的
        }
//        fd1.v2 = new Value(); //错误
//        fd1.v3 = new Value(); //错误
//        fd1.a = new int[3];   //错误
        fd1.print("fd1");
        System.out.println("Create new FinalData");

        FinalData fd2 = new FinalData();
        fd1.print("fd1");
        fd2.print("fd2");

    }

}
