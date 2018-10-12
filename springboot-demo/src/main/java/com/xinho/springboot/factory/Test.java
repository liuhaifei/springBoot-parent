package com.xinho.springboot.factory;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/115:18
 */
public class Test {

    public static void main(String[] args) {
        SimpleFactory simpleFactory=new SimpleFactory();
        //1
        System.out.println(simpleFactory.getCar("DaZ").getName());


        //2.
        AoDiFactory aoDiFactory=new AoDiFactory();
        System.out.println(aoDiFactory.getCar().getName());


        //3
        Factory factory=new Factory();
        System.out.println(factory.getAodi().getName());
    }
}
