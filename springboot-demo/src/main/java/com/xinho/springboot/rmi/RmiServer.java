package com.xinho.springboot.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/411:56
 */
public class RmiServer {

    public static void main(String[] args) {

        try {
            IHelloService helloService=new HelloServiceImpl();

            LocateRegistry.createRegistry(1099);
            //绑定协议与服务
            Naming.bind("rmi://127.0.0.1/Hello",helloService);

            System.out.println("--------服务启动成功-------");
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }


    }
}
