package com.xinho.springboot.proxy.staticProxy;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 委托类
 * @date 2018/6/810:03
 */
public class CountImpl implements Account {
    @Override
    public void queryCount() {
        System.out.println("查询账户");
    }

    @Override
    public void updateCount() {
        System.out.println("修改账户");
    }
}
