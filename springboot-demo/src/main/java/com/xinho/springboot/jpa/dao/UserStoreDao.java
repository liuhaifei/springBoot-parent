package com.xinho.springboot.jpa.dao;

import com.xinho.springboot.jpa.model.UserStoreEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1716:01
 */
@Repository
public interface UserStoreDao extends JpaRepository<UserStoreEntity,String>,
        JpaSpecificationExecutor<UserStoreEntity> {

    @Query(nativeQuery = true,value = "select * from  user_store WHERE id=?1")
     UserStoreEntity findUserById(@Param("id") String id) ;

}
