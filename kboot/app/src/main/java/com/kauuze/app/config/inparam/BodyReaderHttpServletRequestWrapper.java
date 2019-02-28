package com.kauuze.app.config.inparam;

import com.kauuze.app.config.Interceptor;
import com.kauuze.app.include.BoxUtil;
import com.kauuze.app.include.RU;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 包装HttpServletRequest,用来获取body里的信息
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private static final Logger LOG = LoggerFactory.getLogger(Interceptor.class);

    private byte[] body;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = RequestBodyUtils.getBodyString(request).getBytes(Charset.forName("UTF-8"));
        String url = request.getRequestURL().toString();
        String bodyStr = new String(body);
        if(RU.isEmpty(bodyStr)){
            body = "{}".getBytes();
        }
        LOG.info(String.format("%s interceptor request to %s  %s", request.getMethod(), url,"requestKey(" + request.getHeader("requestKey") + ")  "
                + "Authorization(" + request.getHeader("Authorization") + ")  ") + "content-type(" + request.getContentType() + ")  "
                + "params:" + BoxUtil.toJsonString(request.getParameterMap()) + "  body:" + new String(body));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

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
        };
    }
    public void setInputStream(byte[] body) {
        this.body = body;
    }
}