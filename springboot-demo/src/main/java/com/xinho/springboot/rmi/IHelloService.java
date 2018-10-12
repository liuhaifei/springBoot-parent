package com.xinho.springboot.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/411:57
 */
public interface IHelloService extends Remote {

    String sayHello(String name) throws RemoteException;
}
