package com.xinho.springboot.factory;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/115:29
 */
public class Factory extends AbstaractFactory {
    @Override
    Car getAodi() {
        return new AoDi();
    }

    @Override
    Car getBaoM() {
        return new BaoM();
    }

    @Override
    Car getDaZ() {
        return new DaZ();
    }
}
