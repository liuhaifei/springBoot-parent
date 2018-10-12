package com.xinho.springboot.factory;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/115:25
 */
public class DaZFactory  implements  funFactory{
    @Override
    public Car getCar() {
        return new DaZ();
    }
}
