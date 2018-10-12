package com.xinho.springboot.proxy.staticProxy;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/810:07
 */
public class Test {
    public static void main(String[] args) {

        CountImpl cout=new CountImpl();

        CountProxy proxy=new CountProxy(cout);

        proxy.queryCount();
    }
}
