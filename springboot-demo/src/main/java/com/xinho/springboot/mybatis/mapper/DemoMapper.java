package com.xinho.springboot.mybatis.mapper;

import com.xinho.springboot.mybatis.model.UserStoreEntity;

import java.util.List;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1814:38
 */

public interface DemoMapper {

    List<UserStoreEntity> getAll();

    UserStoreEntity getById(String id);
}
