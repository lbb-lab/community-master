package life.majiang.community.test.chapter12.deepclone;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 11:15
 * @Description: 试图深层复制合成对象时会遇到一个问题。必须假定成员对象中的clone()方法也能依次对自己的句柄进行 深层复制，
 * 以此类推。这使我们的操作变得复杂。为了能正常实现深层复制，必须对所有类中的代码进行控  制，或者至少全面掌握深层复制中需要涉及的类，
 * 确保它们自己的深层复制能正确进行。下面这个例子总结了面对一个合成对象进行深层复制时需要做哪些事情：
 *
 * DepthReading 和TemperatureReading 非常相似；它们都只包含了基本数据类型。所以clone()方法能够非常简单：调用 super.clone() 并返回结果即可。
 * 注意两个类使用的clone()代码是完全一致的。
 * OceanReading 是由DepthReading 和 TemperatureReading 对象合并而成的。为了对其进行深层复制，clone()必须同时克隆OceanReading 内的句柄。
 * 为达到这个目标，super.clone() 的结果必须造型成一个OceanReading 对象（以便访问depth 和temperature 句柄）。
 */
public class DeepCopy {
    public static void main(String[] args) {
        OceanReading reading = new OceanReading(33.9, 100.5);
// Now clone it:
        OceanReading r = (OceanReading)reading.clone();

        System.out.println(reading);
        System.out.println(r);
    }

}
