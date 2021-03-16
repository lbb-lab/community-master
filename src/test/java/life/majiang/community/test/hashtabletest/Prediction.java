package life.majiang.community.test.hashtabletest;

/**
 * @author: liu bin bin
 * @Date: 2021/3/15 16:51
 * @Description:
 */
public class Prediction {
    boolean shadow = Math.random() > 0.5; public String toString() {
        if(shadow)
            return "Six more weeks of Winter!"; else
            return "Early Spring!";
    }

}
