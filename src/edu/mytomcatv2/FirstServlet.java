package edu.mytomcatv2;

import edu.mytomcatv2.impl.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author lvconl
 * 类说明：第一个Servlet测试类
 * */
public class FirstServlet implements Servlet {
    /**
     * 初始化方法
     */
    @Override
    public void init() {
        System.out.println("First Servlet init...");
    }

    /**
     * 服务方法
     *
     * @param inputStream  输入流
     * @param outputStream 输出流
     */
    @Override
    public void service(InputStream inputStream, OutputStream outputStream) throws IOException {
        System.out.println("First Servlet service...");
        outputStream.write("First Service service...".getBytes());
        outputStream.flush();
    }

    /**
     * 服务结束销毁方法
     */
    @Override
    public void destory() {

    }
}
