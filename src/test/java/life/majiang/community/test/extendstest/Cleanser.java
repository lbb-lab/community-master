package life.majiang.community.test.extendstest;

/**
 * @author: liu bin bin
 * @Date: 2021/3/13 12:19
 * @Description:
 */
public class Cleanser {
    private String s = new String("Cleanser");
    public void append(String s){
        this.s+=s;
    }
    public void dilute(){
        append("dilute()");
    }
    public void apply(){
        append("apply()");
    }
    public void scrub(){
        append("scrub");
    }
    public void print(){
        System.out.println(s);
    }

    public static void main(String[] args){
        Cleanser x = new Cleanser();
        x.dilute();x.apply();x.scrub();
        x.print();
    }



}
