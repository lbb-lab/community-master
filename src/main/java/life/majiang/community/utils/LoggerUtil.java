package life.majiang.community.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: liu bin bin
 * @Date: 2021/2/20 17:01
 * @Description:
 */
public class LoggerUtil {
    private LoggerUtil() {
    }

    public static StackTraceElement findCaller() {
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        if (null == callStack) {
            return null;
        } else {
            StackTraceElement caller = null;
            String logClassName = LoggerUtil.class.getName();
            boolean isEachLogClass = false;
            StackTraceElement[] var4 = callStack;
            int var5 = callStack.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                StackTraceElement s = var4[var6];
                if (logClassName.equals(s.getClassName())) {
                    isEachLogClass = true;
                }

                if (isEachLogClass && !logClassName.equals(s.getClassName())) {
                    isEachLogClass = false;
                    caller = s;
                    break;
                }
            }

            return caller;
        }
    }

    private static Logger logger() {
        StackTraceElement caller = findCaller();
        if (null == caller) {
            return LoggerFactory.getLogger(LoggerUtil.class);
        } else {
            Logger log = LoggerFactory.getLogger(caller.getClassName() + "." + caller.getMethodName() + "() Line: " + caller.getLineNumber());
            return log;
        }
    }

    public static void trace(String msg) {
        trace(msg, (Throwable)null);
    }

    public static void trace(String msg, Throwable e) {
        if (StringUtils.isNotEmpty(msg)) {
            logger().trace(msg.replaceAll("\r\n", ""), e);
        } else {
            logger().trace(msg, e);
        }

    }

    public static void debug(String msg) {
        debug(msg, (Throwable)null);
    }

    public static void debug(String msg, Throwable e) {
        if (StringUtils.isNotEmpty(msg)) {
            logger().debug(msg.replaceAll("\r\n", ""), e);
        } else {
            logger().debug(msg, e);
        }

    }

    public static void info(String format, Object... arguments) {
        if (StringUtils.isNotEmpty(format)) {
            logger().info(format.replaceAll("\r\n", ""), arguments);
        } else {
            logger().info(format, arguments);
        }

    }

    public static void info(String msg, Throwable e) {
        if (StringUtils.isNotEmpty(msg)) {
            logger().info(msg.replaceAll("\r\n", ""), e);
        } else {
            logger().info(msg, e);
        }

    }

    public static void warn(String msg) {
        warn(msg, (Throwable)null);
    }

    public static void warn(String msg, Throwable e) {
        if (StringUtils.isNotEmpty(msg)) {
            logger().warn(msg.replaceAll("\r\n", ""), e);
        } else {
            logger().warn(msg, e);
        }

    }

    public static void error(String format, Object... arguments) {
        if (StringUtils.isNotEmpty(format)) {
            logger().error(format.replaceAll("\r\n", ""), arguments);
        } else {
            logger().error(format, arguments);
        }

    }

    public static void error(String msg, Throwable e) {
        if (StringUtils.isNotEmpty(msg)) {
            logger().error(msg.replaceAll("\r\n", ""), e);
        } else {
            logger().error(msg, e);
        }

    }

    public static boolean isDebugEnabled() {
        return logger().isDebugEnabled();
    }
}
