package life.majiang.community.test.keywordaccesspermissions;

import life.majiang.community.test.multithreads.ThreadTest;

/**
 * @author: liu bin bin
 * @Date: 2021/3/11 10:08
 * @Description:
 */
public class MainClass {


    public static void main(String[] args){
        TestClass testClass = new TestClass();
        testClass.saveMoney("zhangsan",500); //public方法，都可以访问
        //testClass.subMoney("zhangsan",300);  //private方法，只有它内部可以访问
                                                              //protected方法，只有继承他的类可以访问，继承不能访问private
                                                              //不写权限关键字，默认为friendly，同包下才能访问
//        testClass.setThreadTest();


    }
}
