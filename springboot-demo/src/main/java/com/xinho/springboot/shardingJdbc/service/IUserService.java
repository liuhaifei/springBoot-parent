package com.xinho.springboot.shardingJdbc.service;

import com.xinho.springboot.shardingJdbc.entity.UserInfo;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1814:37
 */
public interface IUserService {

    int insert(UserInfo record);


    int insertSelective(UserInfo record);


    UserInfo selectByPrimaryKey(Long userId);


    int updateByPrimaryKeySelective(UserInfo record);


    int updateByPrimaryKey(UserInfo record);
}
