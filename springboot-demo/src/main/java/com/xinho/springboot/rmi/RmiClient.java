package com.xinho.springboot.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/413:54
 */
public class RmiClient {

    public static void main(String[] args) {
        try {
            IHelloService iHelloService=(IHelloService) Naming.lookup("rmi://127.0.0.1/Hello");
            System.out.println(iHelloService.sayHello("zhangsan"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
