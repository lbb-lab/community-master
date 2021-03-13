package life.majiang.community.test.extendsinitialization;

/**
 * @author: liu bin bin
 * @Date: 2021/3/13 22:24
 * @Description:
 */
public class Insect {
    int i = 9;
    int j;
    Insect(){
        prt("i+"+i+",j="+j);
        j = 39;
    }

    static int x1 = prt("static Insect.x1 initialization");
    static int prt(String s){
        System.out.println(s);
        return 47;
    }
}
