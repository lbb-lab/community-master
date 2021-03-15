package life.majiang.community.test.finaltest;

/**
 * @author: liu bin bin
 * @Date: 2021/3/13 21:35
 * @Description:
 */
public class FinalArguments {
    void with(final Gizmo g){
        //g = new Gizmo();  //g是final类型的，句柄不能变化
        g.spin();
    }

    void without(Gizmo g){
        g = new Gizmo();
        g.spin();
    }

   //    void f(final int i){ i++; }  //final不能改变

    int g(final int i){return i+1;}

    public static void main(String[] args){
        FinalArguments bf = new FinalArguments();
        bf.without(null);                        //一样可以为final变量赋值为null（空句柄），编译期间可以通过，但是运行期间会报空指针异常
        bf.with(null);
    }
}
