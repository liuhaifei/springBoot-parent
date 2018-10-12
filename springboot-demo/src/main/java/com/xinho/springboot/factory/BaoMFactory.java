package com.xinho.springboot.factory;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/115:24
 */
public class BaoMFactory implements funFactory {
    @Override
    public Car getCar() {
        return new BaoM();
    }
}
