package com.xinho.springboot.jpa.service;


import com.xinho.springboot.jpa.dao.UserStoreDao;
import com.xinho.springboot.jpa.model.UserStoreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1717:09
 */
@Service
public class UserStoreImpl {

    @Autowired
    UserStoreDao userStoreDao;

    public List<UserStoreEntity> findStoreByUserName(String name){
        List<UserStoreEntity> entity = (List<UserStoreEntity>) userStoreDao.findAll();

        return  entity;
    }


    public List<UserStoreEntity> findStoreById(String id){
    //0dbf6de6-9388-4f1b-8529-9f49ab822cb5

       ArrayList list= new ArrayList<String>(5);
       list.add(id);
        List<UserStoreEntity> entity = (List<UserStoreEntity>) userStoreDao.findAllById(list);

        return  entity;
    }

    @Cacheable("users")
    public String findUserById(String id){
        //0dbf6de6-9388-4f1b-8529-9f49ab822cb5
        System.out.println("查询开始id："+id);
        UserStoreEntity entity = (UserStoreEntity) userStoreDao.findUserById(id);
        System.out.println("查询结束id:"+id);

        if (entity==null){
            return "000";
        }
        String result=entity.getCreateBy();
        return  result;
    }

    public List<UserStoreEntity> findStoreByDate(){

        Specification<UserStoreEntity> spec = new Specification<UserStoreEntity>() {
            @Override
            public Predicate toPredicate(Root<UserStoreEntity> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {

//              Predicate p1 = cb.like(root.get("name").as(String.class), "%"+um.getName()+"%");
                Predicate p2 = cb.equal(root.get("userId"), "880dc577-f127-4c83-93c1-9ff15a38588d");
//              Predicate p3 = cb.gt(root.get("id").as(Integer.class), um.getAge());
                Predicate p3 = cb.equal(root.get("id"), "0ffc204b-c9cb-48aa-a92c-a296a175a58f");
                //把Predicate应用到CriteriaQuery中去,因为还可以给CriteriaQuery添加其他的功能，比如排序、分组啥的
                query.where(cb.and(p3,p2));
                //添加排序的功能
                query.orderBy(cb.desc(root.get("createdDate")));

                return query.getRestriction();
            }
        };


        List<UserStoreEntity> entity =userStoreDao.findAll(spec);
        return  entity;
    }
}
