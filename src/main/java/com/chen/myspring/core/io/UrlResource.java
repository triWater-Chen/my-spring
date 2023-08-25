package com.chen.myspring.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Chen
 * @description 对 java.net.URL 进行资源定位的实现类
 * @create 2023-08-16
 */
public class UrlResource implements Resource {

    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {

        URLConnection con = this.url.openConnection();
        try {
            return con.getInputStream();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
}
