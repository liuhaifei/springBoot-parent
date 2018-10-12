package com.xinho.springboot.myRpc;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/510:02
 */
public class HelloImpl implements IHello {
    @Override
    public String sayHello(String message) {
        return "HELLO "+message;
    }
}
