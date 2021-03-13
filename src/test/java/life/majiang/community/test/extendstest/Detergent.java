package life.majiang.community.test.extendstest;

/**
 * @author: liu bin bin
 * @Date: 2021/3/13 12:25
 * @Description:
 */
public class Detergent extends Cleanser{
    public void scrub(){
        append("Detergent.scrub");
        super.scrub();
    }

    public void foam(){
        append("foam()");
    }

    public static void main(String[] args){
        Detergent x = new Detergent();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        x.print();
        System.out.println("Testing base class:");
        Cleanser.main(args);
    }


}
