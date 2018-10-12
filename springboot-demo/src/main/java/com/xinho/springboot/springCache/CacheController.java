package com.xinho.springboot.springCache;

import com.xinho.springboot.jpa.model.UserStoreEntity;
import com.xinho.springboot.jpa.service.UserStoreImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/2816:43
 */
@RestController
public class CacheController {
    @Autowired
    private UserStoreImpl userStore;

    @RequestMapping(value = "/findStoreById",method = RequestMethod.GET)
    public List<UserStoreEntity> findStoreById(String id){
        //0dbf6de6-9388-4f1b-8529-9f49ab822cb5
        List<UserStoreEntity> entity = (List<UserStoreEntity>) userStore.findStoreById(id);

        return  entity;
    }
}
