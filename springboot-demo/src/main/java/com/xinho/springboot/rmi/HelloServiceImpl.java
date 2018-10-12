package com.xinho.springboot.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/411:57
 */
public class HelloServiceImpl  extends UnicastRemoteObject implements IHelloService{
    protected HelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String name) {
        return "HELLO "+name;
    }
}
