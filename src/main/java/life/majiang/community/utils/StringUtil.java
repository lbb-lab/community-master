package life.majiang.community.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: liu bin bin
 * @Date: 2021/2/20 16:57
 * @Description:
 */
public class StringUtil {

    private StringUtil() {

    }

    /**
     * 将字符串的数据，转换成中文的大写货币值
     *
     * @param pValue
     * @return
     */
    public static String convertToBigMoney(String pValue) {
        double str = 0;

        try {
            str = Double.parseDouble(pValue);
        }
        catch (Exception e) {
            LoggerUtil.error(e.getMessage(), e);
        }

        str = str + 0.005; // 把它加0.005,为了预防浮点数的四舍五入造成的误差
        String result = "";
        String odxs;
        String odxc;
        String temp1;
        String temp2;
        int integer;
        int point;
        int ormb;
        odxs = "零壹贰叁肆伍陆柒捌玖";
        odxc = "分角圆拾佰仟万拾佰仟亿拾佰仟万拾佰仟亿拾佰仟";
        try {
            integer = (int) str; // 取得他的整数部分
            point = (int) (100 * (str - (float) integer)); // 取得他的小数部分
            if (integer == 0) {
                result = "零圆"; // 如果整数为0,则显示零圆
            }
            for (int i = 1; integer > 0; i++) {
                ormb = (integer % 10); // 取得他的个位
                // 根据相应的值取得他的大写
                temp1 = odxs.substring(ormb, ormb + 1);
                temp2 = odxc.substring(i + 1, i + 2); // 根据循环次数确定他的单位
                result = temp1 + temp2 + result;
                integer = integer / 10;
            }
            ormb = (point / 10); // 取得角
            for (int i = 1; i > -1; i--) {
                // 根据相应的值取得他的大写
                temp1 = odxs.substring(ormb, ormb + 1);
                temp2 = odxc.substring(i, i + 1); // 根据循环次数确定他的单位
                result = result + temp1 + temp2;
                ormb = point % 10; // 取得分
            }
            result = result.replaceAll("零仟", "零");
            result = result.replaceAll("零佰", "零");
            result = result.replaceAll("零拾", "零");
            while (result.indexOf("零零") > -1) {
                result = result.replaceAll("零零", "零");
            }
            result = result.replaceAll("零圆", "圆");
            result = result.replaceAll("零万", "万");
            if ("圆零角零分".equals(result)) {
                result = "零圆零角零分";
            }
        }
        catch (Exception e) {
            LoggerUtil.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 将数字串的数据，转换成中文的大写货币值
     *
     * @param pValue
     * @return
     */
    public static String convertToBigMoney(double pValue) {
        double str = pValue;
        str = str + 0.005; // 把它加0.005,为了预防浮点数的四舍五入造成的误差
        String result = "";
        String odxs;
        String odxc;
        String temp1;
        String temp2;
        int integer;
        int point;
        int ormb;
        odxs = "零壹贰叁肆伍陆柒捌玖";
        odxc = "分角圆拾佰仟万拾佰仟亿拾佰仟万拾佰仟亿拾佰仟";
        try {
            integer = (int) str; // 取得他的整数部分
            point = (int) (100 * (str - (float) integer)); // 取得他的小数部分
            if (integer == 0) {
                result = "零圆"; // 如果整数为0,则显示零圆
            }
            for (int i = 1; integer > 0; i++) {
                ormb = (integer % 10); // 取得他的个位
                // 根据相应的值取得他的大写
                temp1 = odxs.substring(ormb, ormb + 1);
                // 根据循环次数确定他的单位
                temp2 = odxc.substring(i + 1, i + 2);
                result = temp1 + temp2 + result;
                integer = integer / 10;
            }
            ormb = (point / 10); // 取得角
            for (int i = 1; i > -1; i--) {
                // 根据相应的值取得他的大写
                temp1 = odxs.substring(ormb, ormb + 1);
                // 根据循环次数确定他的单位
                temp2 = odxc.substring(i, i + 1);
                result = result + temp1 + temp2;
                ormb = point % 10; // 取得分
            }
            result = result.replaceAll("零仟", "零");
            result = result.replaceAll("零佰", "零");
            result = result.replaceAll("零拾", "零");
            while (result.indexOf("零零") > -1) {
                result = result.replaceAll("零零", "零");
            }
            result = result.replaceAll("零圆", "圆");
            result = result.replaceAll("零万", "万");
            if ("圆零角零分".equals(result)) {
                result = "零圆零角零分";
            }

        }
        catch (Exception e) {
            LoggerUtil.error(e.getMessage(), e);
        }
        return result;
    }

    public static final String[] split(String line, String separator) {
        LinkedList list = new LinkedList();
        if (line != null) {
            int start = 0;
            int end = 0;
            int separatorLen = separator.length();
            while ((end = line.indexOf(separator, start)) >= 0) {
                list.add(line.substring(start, end));
                start = end + separatorLen;
            }
            if (start < line.length()) {
                list.add(line.substring(start, line.length()));
            }
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    public static final String[] splitWithoutSpace(String line, String separator) {
        LinkedList list = new LinkedList();
        if (line != null) {
            int start = 0;
            int end = 0;
            int separatorLen = separator.length();
            while ((end = line.indexOf(separator, start)) >= 0) {
                String str = line.substring(start, end).trim();
                if (str != null && !"".equals(str)) {
                    list.add(str);
                }
                start = end + separatorLen;
            }
            if (start < line.length()) {
                String str = line.substring(start, line.length()).trim();
                if (str != null && !"".equals(str)) {
                    list.add(str);
                }
            }
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    public static final String replaceAll(String line, String str1, String str2) {
        String newStr = "";
        boolean start = line.startsWith(str1);
        boolean end = line.endsWith(str1);
        String[] strs = split(line, str1);
        if (strs != null && strs.length > 0) {
            if (start) {
                newStr = newStr + str2;
            }
            for (int i = 0; i < strs.length; i++) {

                newStr = newStr + strs[i];

                if (i != strs.length - 1 || end) {

                    newStr = newStr + str2;
                }

            }

        }
        else {
            newStr = line;
        }
        return newStr;

    }

    public static String join(Iterator iterator, String separator) throws Exception {
        if (iterator == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(256);
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (obj != null) {
                buf.append("'" + obj + "'");
            }
            if (iterator.hasNext()) {
                buf.append(separator);
            }
        }
        return buf.toString();
    }

    /**
     * 把字符转换为钱数0.00的格式
     *
     * @return
     */
    public static String formatToMoney(String str) {
        int place = 0;
        int len = 0;
        if (str == null || "".equals(str)) {
            return "0.00";
        }
        place = str.indexOf(".");
        if (place == -1) {
            str = str + ".00";
            return str;
        }
        len = str.length();
        if ((len - place) == 2) {
            str = str + "0";
        }
        else if ((len - place) == 1) {
            str = str + "00";
        }
        else {
            str = str.substring(0, place + 3);
        }
        return str;
    }

    public static boolean containValue(Map m, String name) {
        return m.get(name) != null;
    }

    public static boolean containStrValue(Map m, String name) {
        if (!containValue(m, name)) {
            return false;
        }
        String t = (String) m.get(name);
        return t != null && !"".equals(t.trim());
    }

    public static int getPageSize(Map m) {
        return ((Integer) m.get("pageSize")).intValue();
    }

    public static int getPageIndex(Map m) {
        return ((Integer) m.get("pageIndex")).intValue();
    }

    // 第一个字母转换为小写
    public static String unCapital(String methodName) {
        char[] resChars = methodName.toCharArray();
        if (resChars.length >= 1) {
            resChars[0] = methodName.substring(0, 1).toLowerCase().charAt(0);
        }
        return new String(resChars);
    }

    public static boolean hasArrValue(String[] valArr, String value) {
        if (valArr.length == 0 || value == null || "".equals(value)) {
            return false;
        }
        for (String val : valArr) {
            if (value.equals(val)) {
                return true;
            }
        }
        return false;
    }

    // 第一个字母转换为大写
    public static String capital(String methodName) {
        char[] resChars = methodName.toCharArray();
        if (resChars.length >= 1) {
            resChars[0] = methodName.substring(0, 1).toUpperCase().charAt(0);
        }
        return new String(resChars);
    }

    public static boolean notNull(String s) {
        return !(s == null || "".equals(s));
    }

    /**
     * 用16进制表示给定的byte数组
     *
     * @param b
     *            byte[]
     * @return String
     */
    public static String bytes2HexString(byte[] b) {
        String hexStr = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            hexStr += hex;
        }
        return hexStr.toUpperCase();
    }

    public static String join(String seperator, String[] strings) {
        if (strings == null) {
            return "";
        }
        int length = strings.length;
        if (length == 0) {
            return "";
        }
        StringBuffer buf = new StringBuffer(length * strings[0].length()).append(strings[0]);
        for (int i = 1; i < length; i++) {
            buf.append(seperator).append(strings[i]);
        }
        return buf.toString();
    }

    public static String join(String seperator, Iterator objects) {
        StringBuffer buf = new StringBuffer();
        if (objects.hasNext()) {
            buf.append(objects.next());
        }
        while (objects.hasNext()) {
            buf.append(seperator).append(objects.next());
        }
        return buf.toString();
    }

    public static String joinOper(String seperator, Iterator objects, String oper) {
        StringBuffer buf = new StringBuffer();
        if (objects.hasNext()) {
            objects.next();
        }
        buf.append(oper);
        while (objects.hasNext()) {
            objects.next();
            buf.append(seperator).append(oper);
        }
        return buf.toString();
    }

    public static String joinWithQMarks(String seperator, String[] strings) {

        List list = Arrays.asList(strings);
        return joinWithQMarks(seperator, list.iterator());
    }

    public static String joinWithQMarks(String seperator, Iterator objects) {
        StringBuffer buf = new StringBuffer();
        if (objects.hasNext()) {
            buf.append("'").append(objects.next()).append("'");
        }
        while (objects.hasNext()) {
            buf.append(seperator).append("'").append(objects.next()).append("'");
        }
        return buf.toString();
    }

    public static boolean booleanValue(String tfString) {
        String trimmed = tfString.trim().toLowerCase();
        return "true".equals(trimmed) || "t".equals(trimmed);
    }

    public static boolean isEqual(String o, boolean c) {

        if (o == null || "".equals(o)) {
            return false;
        }
        return o.equals(String.valueOf(c).toLowerCase());

    }

    public static boolean isEqual(String o, String c) {
        if (StringUtil.isEmpty(o)) {
            o = "";
        }
        if (StringUtil.isEmpty(c)) {
            c = "";
        }
        return o.equals(c);
    }

    public static String toString(Object[] array) {
        int len = array.length;
        if (len == 0) {
            return "";
        }
        StringBuffer buf = new StringBuffer(len * 12);
        for (int i = 0; i < len - 1; i++) {
            buf.append(array[i]).append(", ");
        }
        return buf.append(array[len - 1]).toString();
    }

    public static String[] toArray(List list) {
        int len = list.size();
        if (len == 0) {
            return null;
        }
        String[] array = new String[len];
        for (int i = 0; i < len; i++) {
            array[i] = list.get(i).toString();
        }
        return array;
    }

    public static boolean isNotEmpty(String string) {
        return string != null && string.length() > 0;
    }

    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static String truncate(String string, int length) {
        if (string.length() <= length) {
            return string;
        }
        else {
            return string.substring(0, length);
        }
    }

    public static String getStrValue(Map map, String key) {
        if (map == null || map.isEmpty() || StringUtils.isBlank(key)) {
            return "";
        }
        Object t = map.get(key);
        if (t != null) {
            return t.toString();
        }
        else {
            for (Object o : map.keySet()) {
                String name = (String) o;
                if (name.toLowerCase().equals(key.toLowerCase())) {
                    Object value = map.get(o);
                    if (value == null) {
                        return "";
                    }
                    return value.toString();
                }
            }
        }

        return "";
    }

    /*
     * public static String getValueFromMap(HashMap<String, HashMap<String,String>>
     * doubleMap,String key){ String value = ""; for (Map.Entry<String,
     * HashMap<String, String>> me : doubleMap.entrySet()) { String classNameKey =
     * me.getKey(); HashMap<String, String> numNameMapValue = me.getValue();
     * if(key.toLowerCase().equals(classNameKey.toLowerCase())){ //如果在第一层匹配上 value =
     * numNameMapValue.toString(); break; } for (Map.Entry<String, String>
     * nameMapEntry : numNameMapValue.entrySet()) { String numKey =
     * nameMapEntry.getKey(); String nameValue = nameMapEntry.getValue();
     * if(key.toLowerCase().equals(numKey.toLowerCase())){ value = nameValue; break;
     * } } } return value; }
     */


    public static String toUpperCase(String str) {
        return str == null ? null : str.toUpperCase();
    }

    public static String toLowerCase(String str) {
        return str == null ? null : str.toLowerCase();
    }

    public static Map toMap(String[] array) {

        if (array == null) {
            return null;
        }
        int len = array.length;

        if (len == 0) {
            return null;
        }

        Map map = new HashMap();

        for (int i = 0; i < len; i++) {
            map.put(array[i], array[i]);
        }
        return map;
        /*--repl将被with替换 -1为替换字符串text中所有的repl--*/
    }

    public static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    /*--max为被替换的个数--*/
    public static String replace(String text, String repl, String with, int max) {
        if (text == null || repl == null || with == null || max == 0) {
            return text;
        }

        StringBuffer buf = new StringBuffer(text.length());
        int start = 0;
        int end = 0;
        while ((end = text.indexOf(repl, start)) != -1) {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();

            if (--max == 0) {
                break;
            }
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * 将两个字符串转换成数字类型 返回大的那个
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String max(String str1, String str2) {
        int num1 = Integer.parseInt(str1);
        int num2 = Integer.parseInt(str2);
        if (num1 > num2) {
            return str1;
        }
        else {
            return str2;
        }
    }

    /**
     * 将两个字符串转换成数字类型 返回小的那个
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String min(String str1, String str2) {
        int num1 = Integer.parseInt(str1);
        int num2 = Integer.parseInt(str2);
        if (num1 > num2) {
            return str2;
        }
        else {
            return str1;
        }
    }

    private static Pattern unmber_pattern = Pattern.compile("^\\d+$");

    /**
     * 校验是否为数字
     */
    public static boolean isNum(String num) {

        if (num == null || "".equals(num)) {
            return false;
        }

        if (num.startsWith("-")) {
            return isNum(num.substring(1));
        }

        Matcher isNum = unmber_pattern.matcher(num);
        return isNum.matches();
    }

    // 防止如包含误判. 如判断"1234,222"是否包含"123",如果直接用String.indexOf()方法,会认为包含
    // 我们在源字符串和子字符串的前后都加上分隔标志,变成判断",1234,222,"中是否包含",123,",这样就不会误判了

    /**
     * @Title: isIndexOf @Description: 判断包含关系 @param str 源字符串 @param subStr
     *         子字符串 @param splitFlag 源字符串的分隔标志 @return true 包含 false 不包含 @throws
     */
    public static boolean isIndexOf(String str, String subStr, String splitFlag) {

        if (isEmpty(str)) {
            return false;
        }

        // 子字符串为空,认为包含
        if (isEmpty(subStr)) {
            return true;
        }

        if (null == splitFlag) {
            splitFlag = "";
        }

        String tmpStr = splitFlag + str + splitFlag;
        String tmpSubStr = splitFlag + subStr + splitFlag;

        return tmpStr.indexOf(tmpSubStr) >= 0;

    }

    /**
     * 克隆String
     *
     * @return
     */
    public static Object cloneMySelf(Object object) {
        Object cloneObj = null;
        ObjectOutputStream oo = null;
        ObjectInputStream oi = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(out);
            oo.writeObject(object);
            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            oi = new ObjectInputStream(in);
            cloneObj = (Object) oi.readObject();
        }
        catch (ConcurrentModificationException e) {
            LoggerUtil.error(e.getMessage(), e);
        }
        catch (IOException e) {
            LoggerUtil.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            LoggerUtil.error(e.getMessage(), e);
        }
        finally {
            if (oo != null) {
                try {
                    oo.close();
                }
                catch (IOException e) {
                    LoggerUtil.error(e.getMessage(), e);
                }
            }
            if (oi != null) {
                try {
                    oi.close();
                }
                catch (IOException e) {
                    LoggerUtil.error(e.getMessage(), e);
                }
            }
        }
        return cloneObj;
    }

    // 把一个HashMap拼凑成key:val|..的格式
    public static String hashMapToString(Map sqlGetKeyVals) {
        StringBuffer data = new StringBuffer("");
        if (sqlGetKeyVals != null) {
            for (Iterator it = sqlGetKeyVals.entrySet().iterator(); it.hasNext();) {
                Map.Entry map = (Map.Entry) it.next();
                String key = (String) map.getKey();
                String val = (String) map.getValue();
                data.append(key);
                data.append(":");
                data.append(val);
                data.append("|");
            }
        }
        return data.toString();
    }

    // 把一个key:val|..格式的String拼凑成HashMap
    public static Map stringToHashMap(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        String[] hashmap = str.split("\\|");
        if (hashmap == null || hashmap.length == 0) {
            return null;
        }
        Map ret = new HashMap();
        for (int i = 0; i < hashmap.length; i++) {
            String it = hashmap[i];
            if (it == null || "".equals(it)) {
                continue;
            }
            String[] maps = it.split(":");
            if (maps == null || maps.length != 2) {
                continue;
            }
            String key = maps[0];
            String val = maps[1];
            ret.put(key, val);
        }
        return ret;
    }

    /**
     * 校验字符串是否匹配正则表达式 add by xiaof
     */
    public static boolean isMatche(String checkStr, String regex) {

        if (checkStr == null || "".equals(checkStr)) {
            return false;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher isMathe = pattern.matcher(checkStr);
        return isMathe.matches();
    }

    public static boolean isMatcheIP(String ip) {
        String patterStr = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        return isMatche(ip, patterStr);
    }

    // 对空情况进行默认
    public static String isEmptyDefalut(String val, String defVal) {
        if (StringUtil.isEmpty(val)) {
            return defVal;
        }
        return val;
    }

    public static String safe(String val) {
        if (val == null) {
            return "";
        }
        return val;
    }

    /**
     * 获取字符串的长度，中文算两个字符
     *
     * @param str
     * @return
     */
    public static int getStrLength(String str) {
        if (isEmpty(str)) {
            return 0;
        }
        char[] charArr = str.toCharArray();
        int charCount = 0;
        for (int i = 0; i < charArr.length; i++) {
            if (isChinese(charArr[i])) {
                charCount += 2;
            }
            else {
                charCount++;
            }
        }
        return charCount;
    }

    public static final boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    public static String getPatchStr(String str, int size) {
        String returnValue = "";
        int len = size - str.length();
        for (int i = 0; i < len; i++) {
            returnValue += "0";
        }
        return returnValue;
    }

    /**
     * 提供删除字符串前后的空格的功能
     *
     * @return
     */
    public static String trimStr(String str) {

        if (null == str) {
            return "";
        }
        else {
            return str.trim();
        }

    }

    // 浮点数
    public static boolean isFloat(String num) {
        if (isNum(num)) {
            return true;
        }
        String reg = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$";

        if (num == null || "".equals(num)) {
            return false;
        }

        if (num.startsWith("-")) {
            return isMatche(num.substring(1), reg);
        }
        return isMatche(num, reg);
    }

    /**
     * 获取当前时间
     *
     * @param pattern
     *            yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String getCurrentTime(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

    // 获取唯一生成的UUId字符串
    public static String getUUIDCode() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     *
     * @param fileName
     *            文件名 如:mktcam_123.txt
     * @return 截取不带后缀的文件名mktcam_123
     */
    public static String getPrefixFileName(String fileName) {
        if (StringUtil.isNotEmpty(fileName)) {
            int lastIndexOf = fileName.lastIndexOf('.');
            return fileName.substring(0, lastIndexOf);
        }
        return null;
    }

    public static String join(Collection<String> coll, String split) {
        if (coll.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String s : coll) {
            if (isFirst) {
                isFirst = false;
            }
            else {
                sb.append(split);
            }
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 根据key获取map值
     *
     * @param map
     * @return
     */
    public static String getStrForMap(Map<String, Object> map, String key) {
        if (map == null || map.size() <= 0) {
            return "";
        }
        Object value = map.get(key);
        if (value != null) {
            return value.toString();
        }
        else {
            return "";
        }
    }

    /**
     * 在指定字符串的左边用指定�? Unicode 字符填充以达到指定的总长�?
     *
     * @param s
     *            String
     * @param totalWidth
     *            int
     * @param paddingChar
     *            char
     * @return String
     */
    public static String padLeft(String s, int totalWidth, char paddingChar) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < totalWidth - s.length(); i++) {
            sb.append(paddingChar);
        }
        sb.append(s);
        return sb.toString();
    }

    public static String leftPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        else {
            if (isEmpty(padStr)) {
                padStr = " ";
            }

            int padLen = padStr.length();
            int strLen = str.length();
            int pads = size - strLen;
            if (pads <= 0) {
                return str;
            }
            else if (padLen == 1 && pads <= 8192) {
                return leftPad(str, size, String.valueOf(padStr.charAt(0)));
            }
            else if (pads == padLen) {
                return padStr.concat(str);
            }
            else if (pads < padLen) {
                return padStr.substring(0, pads).concat(str);
            }
            else {
                char[] padding = new char[pads];
                char[] padChars = padStr.toCharArray();

                for (int i = 0; i < pads; ++i) {
                    padding[i] = padChars[i % padLen];
                }

                return (new String(padding)).concat(str);
            }
        }
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String stringFormat(String pattern, Object arg1) {
        Object[] argList = new Object[] {arg1};
        return MessageFormat.format(pattern, argList);
    }

    public static String join(Object[] array, String separator) {
        return array == null ? null : join(array, separator, 0, array.length);
    }

    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        else {
            if (separator == null) {
                separator = "";
            }

            int noOfItems = endIndex - startIndex;
            if (noOfItems <= 0) {
                return "";
            }
            else {
                StringBuilder buf = new StringBuilder(noOfItems * 16);

                for (int i = startIndex; i < endIndex; ++i) {
                    if (i > startIndex) {
                        buf.append(separator);
                    }

                    if (array[i] != null) {
                        buf.append(array[i]);
                    }
                }

                return buf.toString();
            }
        }
    }

    /**
     *
     * @description: 将数据库查出结果转为驼峰Map
     *
     * @author: zhou.wei
     *
     * @create: 2018/8/2 17:37
     **/
    public static Map<String, String> queryResultMapToBeanMap(Map<String, String> map) {
        Map<String, String> newone = new HashMap();
        Iterator var3 = map.keySet().iterator();
        while (var3.hasNext()) {
            String key = (String) var3.next();
            Object value = map.get(key);
            if (value != null) {
                newone.put(getCamelCaseString(key, false), value.toString());
            }
            else {
                newone.put(getCamelCaseString(key, false), "");
            }
        }
        return newone;
    }

    /**
     * getCamelCaseString: 把带下划线的字符串转成驼峰型. <br/>
     * @author samuel
     * @param str
     * @param firstUpperCase
     * @return
     * @since JDK 1.7
     */
    public static String getCamelCaseString(String str, boolean firstUpperCase){
        StringBuffer sb = new StringBuffer();

        str = str.toUpperCase();
        boolean nextUpperCase = firstUpperCase;

        for(int i=0; i<str.length(); i++){
            if( str.charAt(i)=='_' ){
                nextUpperCase = true;
                continue;
            }

            if( nextUpperCase ){
                sb.append(String.valueOf(str.charAt(i)).toUpperCase());
                nextUpperCase = false;
            }else{
                sb.append(String.valueOf(str.charAt(i)).toLowerCase());
            }
        }

        return sb.toString();
    }

    public static List<Long> stringToLongList(String s) {
        List<Long> result = new ArrayList<>();
        if (StringUtil.isEmpty(s) || "null".equals(s)) {
            return result;
        }
        String[] strings = split(s, ",");
        for (String ls : strings) {
            result.add(Long.valueOf(ls));
        }
        return result;
    }

    public static String strSub(String strMsg) {
        if (StringUtil.isEmpty(strMsg)) {
            return "";
        }
        return strMsg.split("_")[0];
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if ("127.0.0.1".equals(ipAddress)) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    }
                    catch (UnknownHostException e) {
                        throw new RuntimeException(e);
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        }
        catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

    public static void main(String[] args) {
    }

    public static Integer parseInte(String Str) {
        return Integer.parseInt(Str);
    }
}
