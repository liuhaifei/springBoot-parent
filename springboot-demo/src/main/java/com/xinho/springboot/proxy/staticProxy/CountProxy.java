package com.xinho.springboot.proxy.staticProxy;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 代理类
 * @date 2018/6/810:04
 */
public class CountProxy implements Account {
    private Account countImpl;

    public CountProxy(Account countImpl){
        this.countImpl=countImpl;
    }

    @Override
    public void queryCount() {
        System.out.println("代理开始");
        countImpl.queryCount();
        System.out.println("代理结束");
    }

    @Override
    public void updateCount() {
        System.out.println("代理开始");
        countImpl.updateCount();
        System.out.println("代理结束");
    }
}
