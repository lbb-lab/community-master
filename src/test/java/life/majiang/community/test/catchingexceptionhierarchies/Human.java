//package life.majiang.community.test.catchingexceptionhierarchies;
//
///**
// * @author: liu bin bin
// * @Date: 2021/3/18 17:34
// * @Description:  Sneeze 违例会被相符的第一个 catch 从句捕获。当然，这只是第一个。然而，假如我们删除第一个 catch 从句：
// * 那么剩下的 catch 从句依然能够工作，因为它捕获的是 Sneeze 的基础类。换言之，catch(Annoyance e)能捕获一个 Annoyance 以及从它衍生的任何类。
// * 这一点非常重要，因为一旦我们决定为一个方法添加更多的违例，而且它们都是从相同的基础类继承的，那么客户程序员的代码就不需要更改。
// * 至少能够假定它们捕获的 是基础类
// */
//public class Human {
//    public static void main(String[] args) {
//        try {
//            throw new Sneeze();
//        } catch(Annoyance s) {
//            System.out.println("Caught Sneeze");
//        } catch(Sneeze a) {
//            System.out.println("Caught Annoyance");
//        }
//    }
//
//}
