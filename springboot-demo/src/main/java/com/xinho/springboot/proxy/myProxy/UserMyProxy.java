package com.xinho.springboot.proxy.myProxy;

import com.xinho.springboot.proxy.jdkProxy.User;

import java.lang.reflect.Method;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/810:11
 */
public class UserMyProxy implements GPInvocationHandler {
    private User target;


    public Object getInstance(User target){
        this.target=target;

        Class<?> clazz=target.getClass();

        //返回一个代理对象
        return GPProxy.newProxyInstance(new GPClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("方法执行之前");
        method.invoke(this.target,args);//执行目标方法
        System.out.println("方法执行之后");
        return null;
    }
}
