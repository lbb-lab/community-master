package life.majiang.community;

/**
 * @author: liu bin bin
 * @Date: 2021/3/3 15:42
 * @Description:
 */
public class Fdfssd {


    private String name;

    private String user;

    public Fdfssd(){

    }

    public Fdfssd(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Fdfssd{" +
                "name='" + name + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
