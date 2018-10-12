package com.xinho.springboot.shardingJdbc.service.impl;

import com.xinho.springboot.shardingJdbc.entity.UserInfo;
import com.xinho.springboot.shardingJdbc.mapper.UserInfoMapper;
import com.xinho.springboot.shardingJdbc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1814:42
 */
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public int insert(UserInfo record) {
        return userInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(UserInfo record) {
        return userInfoMapper.insertSelective(record);
    }

    @Override
    public UserInfo selectByPrimaryKey(Long userId) {

        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo record) {
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserInfo record) {
        return userInfoMapper.updateByPrimaryKey(record);
    }
}
