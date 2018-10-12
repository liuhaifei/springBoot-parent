package com.xinho.springboot.mybatis.service.impl;

import com.xinho.springboot.mybatis.mapper.DemoMapper;
import com.xinho.springboot.mybatis.model.UserStoreEntity;
import com.xinho.springboot.mybatis.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1814:42
 */
@Service
public class DemoServiceImpl implements IDemoService{

    @Autowired
    DemoMapper demoMapper;
    @Override
    public List<UserStoreEntity> getAll() {
        return demoMapper.getAll();
    }

    @Override
    public UserStoreEntity getById(String id) {
        return demoMapper.getById(id);
    }
}
