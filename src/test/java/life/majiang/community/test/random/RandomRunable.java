package life.majiang.community.test.random;

import life.majiang.community.test.utils.RandomUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: liu bin bin
 * @Date: 2021/3/12 11:11
 * @Description:
 */
public class RandomRunable implements Runnable{

    String s;

    public RandomRunable(String s){
        this.s = s;
    }


    @Override
    public void run() {
        if (StringUtils.containsIgnoreCase(s,"low")){
            System.out.println("lower ：开始了！");
            RandomUtils.randomValue("lower");
        }else {
            System.out.println("upper ：开始了！");
            RandomUtils.randomValue("upper");
        }
    }
}
