package com.xinho.springboot.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/810:11
 */
public class UserProxy implements InvocationHandler {
    private User target;


    public Object getInstance(User target){
        this.target=target;

        Class<?> clazz=target.getClass();

        //返回一个代理对象
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("方法执行之前");
        method.invoke(this.target,args);//执行目标方法
        System.out.println("方法执行之后");
        return null;
    }
}
