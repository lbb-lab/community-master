package life.majiang.community.test.map;

import java.util.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/17 16:07
 * @Description: 即使大小为 10，ArrayMap 的性能也要比HashMap 差——除反复循环时以外。而在使用 Map 时，
 * 反复的作用通常并不重要（get()通常是我们时间花得最多的地方）。TreeMap 提供了出色的 put()以及反复时间，但 get() 的性能并不佳。
 * 但是，我们为什么仍然需要使用TreeMap 呢？这样一来，我们可以不把它作为Map 使用，而作为创建顺序列表的一种途径。树的本质在于它总是顺序排列的，
 * 不必特别进行排序（它的排序方式马上就
 * 要讲到）。一旦填充了一个TreeMap，就可以调用 keySet() 来获得键的一个Set“景象”。然后用 toArray()
 *产生包含了那些键的一个数组。随后，可用static  方法Array.binarySearch()快速查找排好序的数组中的内容。当然，也许只有在HashMap 的行为不可接受的时候，才需要采用这种做法。因为HashMap 的设计宗旨就是进行快速的检索操作
 * 最后，当我们使用 Map 时，首要的选择应该是 HashMap。只有在极少数情况下才需要考虑其他方法。
 *
 */
public class MapPerformance {
    private static final int REPS = 200;
    public static Map fill(Map m, int size) {
        for(int i = 0; i < size; i++) {
            String x = Integer.toString(i);
            m.put(x, x);
        }
        return m;
    }
    private abstract static class Tester { String name;
        Tester(String name) { this.name = name; }
        abstract void test(Map m, int size);
    }
    private static Tester[] tests = {
            new Tester("put") {
        void test(Map m, int size) {
            for(int i = 0; i < REPS; i++) {
            m.clear();
            fill(m, size);
        }
        }
    },
            new Tester("get") {
                void test(Map m, int size) {
                    for(int i = 0; i < REPS; i++)
                    for(int j = 0; j < size; j++)
                        m.get(Integer.toString(j));
                }
            },
            new Tester("iteration") {
                void test(Map m, int size) {
                    for(int i = 0; i < REPS * 10; i++) {
                        Iterator it = m.entrySet().iterator();
                        while(it.hasNext())
                        it.next();
                    }
                }
            },
    };
    public static void test(Map m, int size) {
// A trick to print out the class name:
System.out.println("Testing " + m.getClass().getName() + " size " + size);
        fill(m, size);
        for(int i = 0; i < tests.length; i++) {
            System.out.print(tests[i].name);
            long t1 = System.currentTimeMillis();
            tests[i].test(m, size);
            long t2 = System.currentTimeMillis();
            System.out.println(": " + ((double)(t2 - t1)/(double)size));
        }
    }
    public static void main(String[] args) {
        // Small:
        test(new Hashtable(), 10);
        test(new HashMap(), 10);
        test(new TreeMap(), 10);
        // Medium:
        test(new Hashtable(), 100);
        test(new HashMap(), 100);
        test(new TreeMap(), 100);
        // Large:
        test(new HashMap(), 1000);
        test(new Hashtable(), 1000);
        test(new TreeMap(), 1000);
    }

    }
