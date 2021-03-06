package edu.mytomcatv2;

import edu.mytomcatv2.impl.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author lvconl
 * 类说明： 第二个Servlet测试类
 * */
public class SecondServlet implements Servlet {
    /**
     * 初始化方法
     */
    @Override
    public void init() {
        System.out.println("Second Servlet init...");
    }

    /**
     * 服务方法
     *
     * @param inputStream  输入流
     * @param outputStream 输出流
     */
    @Override
    public void service(InputStream inputStream, OutputStream outputStream) throws IOException {
        System.out.println("Second Servlet service...");
        outputStream.write("Second Servlet service...".getBytes());
        outputStream.flush();
    }

    /**
     * 服务结束销毁方法
     */
    @Override
    public void destory() {

    }
}
