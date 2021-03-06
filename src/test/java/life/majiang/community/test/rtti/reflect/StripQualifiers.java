package life.majiang.community.test.rtti.reflect;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 1:27
 * @Description:
 */
public class StripQualifiers {
    private StreamTokenizer st;
    public StripQualifiers(String qualified) { st = new StreamTokenizer(new StringReader(qualified));
        st.ordinaryChar(' '); // Keep the spaces

    }
    public String getNext() {
        String s = null;
        try {
            if(st.nextToken() != StreamTokenizer.TT_EOF) {
                switch(st.ttype) {
                    case StreamTokenizer.TT_EOL: s = null;
                        break;
                    case StreamTokenizer.TT_NUMBER: s = Double.toString(st.nval); break;
                    case StreamTokenizer.TT_WORD: s = new String(st.sval); break;
                    default: // single character in ttype
                        s = String.valueOf((char)st.ttype);
                }
            }
        } catch(IOException e) { System.out.println(e);
        }
        return s;
    }
    public static String strip(String qualified) {
        StripQualifiers sq = new StripQualifiers(qualified);
        String s = "", si;
        while((si = sq.getNext()) != null) {
            int lastDot = si.lastIndexOf('.');
            if(lastDot != -1)
                si = si.substring(lastDot + 1);
            s += si;
        }
        return s;
    }

}
