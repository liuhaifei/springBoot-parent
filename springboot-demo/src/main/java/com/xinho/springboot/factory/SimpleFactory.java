package com.xinho.springboot.factory;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/115:10
 */
public class SimpleFactory {

    public Car getCar(String name){

        switch (name){
            case "BaoM":
                return new BaoM();
            case "DaZ":
                return new DaZ();
            case "AoDi":
                return new AoDi();
            default:
                return null;
        }
    }
}
