package life.majiang.community.test.utils;

/**
 * @author: liu bin bin
 * @Date: 2021/3/12 10:57
 * @Description:
 */
public class RandomUtils {

    public static void usage() {
        System.out.println("程序没有产生结果！");
    }

    public static void randomValue(String s){
        if ("lower".equals(s)){
            while (Math.random()!=0.0);
            System.out.println("random=0.0 success!");
        }else if ("upper".equals(s)){
            while (Math.random()!=1.0);
            System.out.println("random=1.0，success！");
        }else {
            System.out.println("输入有误！");
        }
    }

}
