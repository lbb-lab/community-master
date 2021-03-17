package life.majiang.community.test.extendsinitialization;

/**
 * @author: liu bin bin
 * @Date: 2021/3/13 22:26
 * @Description:
 */
public class Beetle extends Insect {
    int k = prt("Beetle.k initialization");
    Beetle(){
        prt("k="+k);
        prt("j="+j);
    }
    static int x2 = prt("static Beetle.x2 initialization");
    static int prt(String s){
        System.out.println(s);
        return 63;
    }
    public static void main(String[] args){ //对Beetle运行Java时，发生的第一件事是装载程序到外面找到那个类。在装载的过程中
        prt("Beetle constructor");      //装载程序注意它有一个基础类，所以随之将其载入。无论是否准备生成那个基础类的一个对象，这个过程都会发生，若还有基础类
        Beetle b = new Beetle();            //则还会载入。接下来会在根基础类执行static初始化，再在下一个进行static初始化。
    }                                       //此时必要的类已经装载完毕，所以能够创建对象，首先，这个对象中的所有基本数据类型都会设成他们的默认值，而将句柄设置成为null
                                            //随后会调用基础类构建器。在这种情况下，调用是自动进行的。但也完全可以用super来自行指定构建器调用。
                                            //基础类的构建采用与衍生类构建器完全相同的处理过程。基础构建器完成以后，自变量会按照本来的顺序得以初始化。
                                            //最后，执行构建器剩余部分
}
