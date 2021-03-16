package life.majiang.community.test.hashtabletest;

/**
 * @author: liu bin bin
 * @Date: 2021/3/15 16:58
 * @Description:
 */
public class Groundhog2 {
    int ghNumber;

    Groundhog2(int n) {
        ghNumber = n;
    }

    public int hashCode() {
        return ghNumber;
    }

    public boolean equals(Object o) {
        return (o instanceof Groundhog2) && (ghNumber == ((Groundhog2) o).ghNumber);

    }
}
