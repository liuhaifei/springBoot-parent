package com.xinho.springboot.shardingJdbc.service;

import com.xinho.springboot.shardingJdbc.entity.UserInfo;
import com.xinho.springboot.shardingJdbc.mapper.UserInfoMapper;
import io.shardingjdbc.core.api.HintManager;
import io.shardingjdbc.core.hint.HintManagerHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/615:21
 */

@Service
public class DemoService {

    @Resource
    UserInfoMapper userInfoMapper;

    public static Long userId = 150L;

    public void demo() {
        System.out.println("Insert--------------");

        for (int i = 1; i <= 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setAccount("Account" + i);
            userInfo.setPassword("pass" + i);
            userInfo.setUserName("name" + i);
            userId++;
            if (i == 3) {
                HintManagerHolder.clear();
                HintManager hintManager = HintManager.getInstance();
                hintManager.addDatabaseShardingValue("user_info", "user_id", 3L);
                hintManager.addTableShardingValue("user_info", "user_id", 3L);
                System.out.println(userId);
            }
            userInfoMapper.insert(userInfo);

        }
        System.out.println("over..........");
    }
}