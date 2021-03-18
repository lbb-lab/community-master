package life.majiang.community.test.exceptionlimit;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 15:16
 * @Description:
 */
abstract class Inning {
    Inning() throws BaseballException{}

    void event () throws BaseballException {
        // Doesn't actually have to throw anything
    }

    abstract void atBat() throws Strike, Foul;

    void walk() {} // Throws nothing

}
