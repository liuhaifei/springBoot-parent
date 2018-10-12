package com.xinho.springboot.myRpc;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/510:50
 */
public class ServerDemo {
    public static void main(String[] args) {
        IHello hello=new HelloImpl();
        RpcServer rpcServer=new RpcServer();
        //发布服务
        rpcServer.publisher(hello,8080);
    }



}
