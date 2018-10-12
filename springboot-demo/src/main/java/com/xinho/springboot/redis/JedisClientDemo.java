package com.xinho.springboot.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName JedisClientDemo
 * @Description TODO
 * @Author 刘海飞
 * @Date 2018/9/27 13:57
 * @Version 1.0
 **/
public class JedisClientDemo {

    public static void main(String[] args) {
//        JedisPool jedisPool=new JedisPool("47.96.119.178",7000);
//        String key=jedisPool.getResource().get("lhf");
//        System.out.println(key);

//        Set<HostAndPort> hostAndPorts=new HashSet<>();
//        hostAndPorts.add(new HostAndPort("47.96.119.178",6379));
//        hostAndPorts.add(new HostAndPort("47.96.119.178",7000));
//
//        JedisCluster jedisCluster=new JedisCluster(hostAndPorts);
//
//        System.out.println(jedisCluster.get("lhf"));


        Set<HostAndPort> hostAndPorts=new HashSet<>();
        HostAndPort hostAndPort=new HostAndPort("192.168.11.153",7000);
        HostAndPort hostAndPort1=new HostAndPort("192.168.11.153",7001);
        HostAndPort hostAndPort2=new HostAndPort("192.168.11.154",7003);
        HostAndPort hostAndPort3=new HostAndPort("192.168.11.157",7006);
        hostAndPorts.add(hostAndPort);
        hostAndPorts.add(hostAndPort1);
        hostAndPorts.add(hostAndPort2);
        hostAndPorts.add(hostAndPort3);
        JedisCluster jedisCluster=new JedisCluster(hostAndPorts,6000);
        jedisCluster.set("mic","hello");

    }
}
