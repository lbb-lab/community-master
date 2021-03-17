package life.majiang.community.test.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeMap;

/**
 * @author: liu bin bin
 * @Date: 2021/3/17 16:17
 * @Description:  在写这个程序期间，TreeMap的创建速度比其他两种类型明显快得多（但你应亲自尝试一下，因为据说新版 本可能会改善ArrayMap 的性能）。
 * 考虑到这方面的原因，同时由于前述 TreeMap 出色的 put()性能，所以如果需要创建大量Map，而且只有在以后才需要涉及大量检索操作，
 * 那么最佳的策略就是：创建和填充TreeMap；以后检索量增大的时候，再将重要的TreeMap 转换成 HashMap——使用HashMap(Map)构建器。
 * 同样地，只有在事实证明确实存在性能瓶颈后，才应关心这些方面的问题——先用起来，再根据需要加快速度。
 */
public class MapCreation {
    public static void main(String[] args) {
        final long REPS = 100000;
        long t1 = System.currentTimeMillis();
        System.out.print("Hashtable");
        for(long i = 0; i < REPS; i++)
            new Hashtable();
        long t2 = System.currentTimeMillis();
        System.out.println(": " + (t2 - t1));
        t1 = System.currentTimeMillis();
        System.out.print("TreeMap");
        for(long i = 0; i < REPS; i++)
            new TreeMap();
        t2 = System.currentTimeMillis();
        System.out.println(": " + (t2 - t1));
        t1 = System.currentTimeMillis();
        System.out.print(" HashMap");
        for(long i = 0; i < REPS; i++)
            new HashMap();
        t2 = System.currentTimeMillis();
        System.out.println(": " + (t2 - t1));
    }

}
