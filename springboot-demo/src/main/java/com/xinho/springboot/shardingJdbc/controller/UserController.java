package com.xinho.springboot.shardingJdbc.controller;

import com.xinho.springboot.shardingJdbc.entity.UserInfo;
import com.xinho.springboot.shardingJdbc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/610:30
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;


    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insert(@RequestBody UserInfo userInfo){

        int result=userService.insert(userInfo);


        return result;
    }

    @RequestMapping(value = "/insertSelective",method = RequestMethod.POST)
    public int insertSelective(@RequestBody UserInfo userInfo){

        int result=userService.insert(userInfo);

        return result;
    }

    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public UserInfo selectByPrimaryKey(@RequestParam Long userId){
       return userService.selectByPrimaryKey(userId);
    }

    @RequestMapping(value = "/updateByPrimaryKeySelective",method = RequestMethod.PUT)
    public int updateByPrimaryKeySelective(@RequestBody UserInfo record){
        return userService.updateByPrimaryKeySelective(record);
    }


    @RequestMapping(value = "/updateByPrimaryKey",method = RequestMethod.PUT)
    public int updateByPrimaryKey(@RequestBody UserInfo record){
        return userService.updateByPrimaryKey(record);
    }
}
