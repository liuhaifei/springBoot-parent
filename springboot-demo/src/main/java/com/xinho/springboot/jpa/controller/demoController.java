package com.xinho.springboot.jpa.controller;


import com.xinho.springboot.jpa.model.UserStoreEntity;
import com.xinho.springboot.jpa.service.UserStoreImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1715:00
 */
@RestController
public class demoController {

    @Autowired
    UserStoreImpl userStoreImpl;

    @RequestMapping("/hi")
    public String hi(){
        List<UserStoreEntity> entity= userStoreImpl.findStoreByUserName("zs");
        for (UserStoreEntity en:entity){
            System.out.println(en.getMerchantId()+"--"+en.getStoreId());
        }
        return  "hello demo";
    }


    @RequestMapping("/hi1")
    public String hi1(){
        List<UserStoreEntity> entity= userStoreImpl.findStoreById("zs");
        for (UserStoreEntity en:entity){
            System.out.println(en.getMerchantId()+"--"+en.getStoreId());
        }
        return  "hello demo";
    }


    @RequestMapping("/hi2")
    public String hi2(){
        List<UserStoreEntity> entity= userStoreImpl.findStoreByDate();
        for (UserStoreEntity en:entity){
            System.out.println(en.getMerchantId()+"--"+en.getStoreId());
        }
        return  "hello demo";
    }
}
