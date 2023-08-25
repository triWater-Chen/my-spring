package com.chen.myspring.ioc;

import cn.hutool.core.io.IoUtil;
import com.chen.myspring.core.io.DefaultResourceLoader;
import com.chen.myspring.core.io.FileSystemResource;
import com.chen.myspring.core.io.Resource;
import com.chen.myspring.core.io.UrlResource;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author Chen
 * @description resource-and-resource-loader
 *              资源和资源加载器
 * @create 2023-08-16
 */
public class ResourceAndResourceLoaderTest {

    @Test
    public void testResourceLoader() throws Exception {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        // 加载 classpath 下的资源
        Resource resource = resourceLoader.getResource("classpath:hello.txt");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
        Assert.assertEquals(content, "hello world!");

        // 加载文件系统资源
        resource = resourceLoader.getResource("src/test/resources/hello.txt");
        Assert.assertTrue(resource instanceof FileSystemResource);
        inputStream = resource.getInputStream();
        content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
        Assert.assertEquals(content, "hello world!");

        // 加载 url 资源
        resource = resourceLoader.getResource("https://www.baidu.com");
        Assert.assertTrue(resource instanceof UrlResource);
        inputStream = resource.getInputStream();
        content = IoUtil.readUtf8(inputStream);
        System.out.println(content);

        System.out.println("===== SUCCESS =====");
    }
}
