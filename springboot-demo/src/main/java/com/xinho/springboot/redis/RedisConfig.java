//package com.xinho.springboot.redis;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * @author lhf
// * @Title: ${file_name}
// * @Package ${package_name}
// * @Description: ${todo}
// * @date 2018/5/2518:03
// */
//@Configuration
//public class RedisConfig {
//
//    @Autowired
//    private RedisProperties redisProperties;
//
//
//    /**
//     * 注意：
//     * 这里返回的JedisCluster是单例的，并且可以直接注入到其他类中去使用
//     * @return
//     */
//    @Bean
//    public JedisCluster getJedisCluster() {
//        List<String> serverArray = redisProperties.getCluster().getNodes();//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
//        Set<HostAndPort> nodes = new HashSet<>();
//
//        for (String ipPort : serverArray) {
//            String[] ipPortPair = ipPort.split(":");
//            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
//        }
//
//        return new JedisCluster(nodes, redisProperties.getTimeout().getNano(), 1);
//    }
//}
