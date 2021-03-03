package life.majiang.community.filter;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * request wrapper，重写body数据到request流中
 *
 * @author mengjiaxing
 * @since 2020/7/27
 */
public class RequestWrapper extends HttpServletRequestWrapper {
        private final String body;
        private JSONObject jsonBody = null;

        public RequestWrapper(HttpServletRequest request) {
            super(request);
            StringBuilder stringBuilder = new StringBuilder();
            InputStream is = null;
            try {
                is = request.getInputStream();

                byte[] b = new byte[4096];
//            int c = is.read(b);
//            System.out.println("我在requestWrapper:"+c);
                for (int n; (n = is.read(b)) != -1; ) {
                    stringBuilder.append(new String(b, 0, n));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != is) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            body = stringBuilder.toString();
            try {
                jsonBody = JSONObject.parseObject(body);
            } catch (Exception e) {

            }
        }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;

    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }

    public JSONObject getJsonBody() {
        return this.jsonBody;
    }
}
