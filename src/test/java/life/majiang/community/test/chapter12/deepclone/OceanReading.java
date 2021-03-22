package life.majiang.community.test.chapter12.deepclone;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 11:14
 * @Description:
 */
public class OceanReading implements Cloneable{
    private DepthReading depth;
    private TemperatureReading temperature;
    public OceanReading(double tdata, double ddata){
        temperature = new TemperatureReading(tdata);
        depth = new DepthReading(ddata);
    }

    public Object clone() {
        OceanReading o = null;
        try {
            o = (OceanReading)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();

        }
// Must clone handles:
        o.depth = (DepthReading)o.depth.clone();
        o.temperature = (TemperatureReading)o.temperature.clone();
        return o; // Upcasts back to Object
    }

}
