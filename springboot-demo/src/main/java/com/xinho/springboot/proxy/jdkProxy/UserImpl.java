package com.xinho.springboot.proxy.jdkProxy;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/810:10
 */
public class UserImpl implements User {
    @Override
    public void add() {
        System.out.println("新增用户");
    }

    @Override
    public void update() {
        System.out.println("修改用户");
    }
}
