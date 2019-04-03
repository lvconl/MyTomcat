package edu.mytomcatv2.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author lvconl
 * 类说明： 程序服务统一接口
 * */
public interface Servlet {
    /**
     * 初始化方法
     * */
    void init();
    /**
     * 服务方法
     * @param inputStream 输入流
     * @param outputStream 输出流
     * */
    void service(InputStream inputStream, OutputStream outputStream) throws IOException;
    /**
     * 服务结束销毁方法
     * */
    void destory();
}
