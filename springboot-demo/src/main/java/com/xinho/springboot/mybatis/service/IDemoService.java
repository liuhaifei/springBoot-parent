package com.xinho.springboot.mybatis.service;

import com.xinho.springboot.mybatis.model.UserStoreEntity;

import java.util.List;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1814:37
 */
public interface IDemoService {

    List<UserStoreEntity> getAll();

    UserStoreEntity getById(String id);
}
