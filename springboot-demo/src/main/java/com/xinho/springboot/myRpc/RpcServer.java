package com.xinho.springboot.myRpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: rpcServer 用于发布远程服务
 * @date 2018/6/510:04
 */
public class RpcServer {

    //创建一个线程池
    private static final ExecutorService EXECUTOR_SERVICE= Executors.newCachedThreadPool();

    public void publisher(final Object service,int port){
        //创建一个服务端socket，且设置传送过来的端口
        ServerSocket serverSocket=null;
        try {
             serverSocket=new ServerSocket(port);
             //循环监听
            while (true){
                Socket socket=serverSocket.accept();
                //把监听到的请求交给线程池去处理
                EXECUTOR_SERVICE.execute(new ProcessorHandler(service,socket));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            if (serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
