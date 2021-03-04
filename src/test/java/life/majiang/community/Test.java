package life.majiang.community;


import java.util.HashMap;
import java.util.Map;

/**
 * @author: liu bin bin
 * @Date: 2021/3/3 15:40
 * @Description:
 */
public class Test {


    public static void main(String[] args){


        Map<Integer,String> map = new HashMap<>();
        String name1 = "nihao";
        String name2 = null;
        Fdfssd fdfssd = new Fdfssd();
        Fdfssd fdfssd1 = new Fdfssd(name1);
        Fdfssd fdfssd2 = new Fdfssd(name2);
        System.out.println("f1:"+fdfssd+"\n"+"f2:"+fdfssd1+"\n"+"f3:"+fdfssd2);
    }



}
