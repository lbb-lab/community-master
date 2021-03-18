package life.majiang.community.test.map;

import java.util.Collection;
import java.util.Map;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 10:14
 * @Description:
 */
public class Map1 {
    public static String testData1 = "testData";

    public static Map<String,Integer> fill(Map c, String testData){
        ((Map)(c)).put(testData,1);
        return (Map<String, Integer>) c;
    }

    public static void print(Map c){
        System.out.println(c);
    }


}
