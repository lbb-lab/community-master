package life.majiang.community.test.exceptionlimit;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 15:26
 * @Description:
 */
public class StormyInning extends Inning implements Storm {
    // OK to add new exceptions for constructors,
    // but you must deal with the base constructor
    // exceptions:

    StormyInning() throws RainedOut, BaseballException {}

    StormyInning(String s) throws Foul, BaseballException {}
    // Regular methods must conform to base class:
    //! void walk() throws PopFoul {} //Compile error
    // Interface CANNOT add exceptions to existing
    // methods from the base class:
    //! public void event() throws RainedOut {}
    // If the method doesn't already exist in the
    // base class, the exception is OK:
    public void rainHard() throws RainedOut {}

    // You can choose to not throw any exceptions,
    // even if base version does:
    public void event() {}

    // Overridden methods can throw
    // inherited exceptions:
    void atBat() throws PopFoul {}

    public static void main(String[] args) {
        try {
            StormyInning si = new StormyInning();
            si.atBat();
        } catch(PopFoul e) {
        } catch(RainedOut e) {
        } catch(BaseballException e) {}
        // Strike not thrown in derived version.
        try {
        // What happens if you upcast?
            Inning i = new StormyInning(); i.atBat();
        // You must catch the exceptions from the
        // base-class version of the method:
        } catch(Strike e) {
        } catch(Foul e) {
        } catch(RainedOut e) {
        } catch(BaseballException e) {}
    }

}
