package com.xinho.springboot.shardingJdbc.mapper;

import com.xinho.springboot.shardingJdbc.entity.UserInfo;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/614:07
 */
public interface UserInfoMapper {


    int insert(UserInfo record);


    int insertSelective(UserInfo record);


    UserInfo selectByPrimaryKey(Long userId);


    int updateByPrimaryKeySelective(UserInfo record);


    int updateByPrimaryKey(UserInfo record);


}
