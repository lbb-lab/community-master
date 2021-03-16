package life.majiang.community.test.hashtabletest;

import java.util.Hashtable;

/**
 * @author: liu bin bin
 * @Date: 2021/3/15 16:21
 * @Description:
 */
public class Statistics {
    public static void main(String[] args) {
        Hashtable ht = new Hashtable();
        for(int i = 0; i < 10000; i++) {
            // Produce a number between 0 and 20:
            Integer r = new Integer((int)(Math.random() * 20));
            if(ht.containsKey(r))
                ((Counter)ht.get(r)).i++;
            else
                ht.put(r, new Counter());
        }
        System.out.println(ht);
    }

}
