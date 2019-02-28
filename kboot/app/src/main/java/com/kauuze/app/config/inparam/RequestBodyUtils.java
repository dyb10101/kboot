package com.kauuze.app.config.inparam;

import javax.servlet.ServletRequest;
import java.io.*;
import java.nio.charset.Charset;

/**
 * 读写body里的信息工具类
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class RequestBodyUtils {

    private static final int BUFFER_SIZE = 1024 * 8;

    /**
     * read string.
     *
     * @param reader Reader instance.
     * @return String.
     * @throws IOException
     */
    public static String read(Reader reader) throws IOException
    {
        StringWriter writer = new StringWriter();
        try
        {
            write(reader, writer);
            return writer.getBuffer().toString();
        }
        finally{ writer.close(); }
    }

    /**
     * write.
     *
     * @param reader Reader.
     * @param writer Writer.
     * @return count.
     * @throws IOException
     */
    public static long write(Reader reader, Writer writer) throws IOException
    {
        return write(reader, writer, BUFFER_SIZE);
    }

    /**
     * write.
     *
     * @param reader Reader.
     * @param writer Writer.
     * @param bufferSize buffer size.
     * @return count.
     * @throws IOException
     */
    public static long write(Reader reader, Writer writer, int bufferSize) throws IOException
    {
        int read;
        long total = 0;
        char[] buf = new char[BUFFER_SIZE];
        while( ( read = reader.read(buf) ) != -1 )
        {
            writer.write(buf, 0, read);
            total += read;
        }
        return total;
    }

    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}