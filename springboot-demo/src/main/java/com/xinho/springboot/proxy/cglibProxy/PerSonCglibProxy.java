package com.xinho.springboot.proxy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/811:00
 */
public class PerSonCglibProxy implements MethodInterceptor {

    public Object getInstance(Class<?> clazz){

        Enhancer enhancer=new Enhancer();

        //要把哪个类设置成即将生效类的父类
        enhancer.setSuperclass(clazz);

        enhancer.setCallback(this);

        return enhancer.create();

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("方法开始之前");
        methodProxy.invokeSuper(o,objects);

        System.out.println("方法开始之后");

        return null;
    }
}
