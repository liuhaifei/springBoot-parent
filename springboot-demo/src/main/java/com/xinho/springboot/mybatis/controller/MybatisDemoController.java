package com.xinho.springboot.mybatis.controller;

import com.xinho.springboot.mybatis.model.UserStoreEntity;
import com.xinho.springboot.mybatis.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1814:38
 */
@RestController
public class MybatisDemoController {

    @Autowired
    IDemoService demoService;

    @RequestMapping("/user")
    public String getUserStore(){

      List<UserStoreEntity> list= demoService.getAll();

      list.forEach(item ->{
          System.out.println( item.getUserId()+"--"+item.getStoreId());
      });
        return "hello mybatis";
    }
}
