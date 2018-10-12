package com.xinho.springboot.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/1315:54
 */
public class CuratorWatcherDemo {

    public static void main(String[] args) throws Exception {

        CuratorFramework curatorFramework= CuratorFrameworkFactory
                    .builder()
                    .connectString("47.96.119.178:2181")
                    .sessionTimeoutMs(4000)
                    .retryPolicy(new ExponentialBackoffRetry(1000,3))
                    .namespace("curator")
                    .build();
        curatorFramework.start();

        /**
         * PathChildCache 监听一个节点下子节点的创建、删除、更新
         * NodeCache  监听一个节点的更新和创建事件
         * TreeCache  综合PatchChildCache和NodeCache的特性
         */
//        addListenerWithNodeCache(curatorFramework,"/mic");

//        addListenerWithPathChildCache(curatorFramework,"/mic");

        addListenerWithTreeCache(curatorFramework,"/mic");

        System.in.read();
    }
    public static void addListenerWithTreeCache(CuratorFramework curatorFramework,String path) throws Exception {
        TreeCache treeCache=new TreeCache(curatorFramework,path);
        TreeCacheListener treeCacheListener=new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                System.out.println(treeCacheEvent.getType()+"->"+treeCacheEvent.getData().getPath());
            }
        };
        treeCache.getListenable().addListener(treeCacheListener);
        treeCache.start();
    }


    public static void addListenerWithPathChildCache(CuratorFramework curatorFramework,String path) throws Exception {
        PathChildrenCache pathChildrenCache=new PathChildrenCache(curatorFramework,path,true);
        PathChildrenCacheListener pathChildrenCacheListener=
                new PathChildrenCacheListener() {
                    @Override
                    public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                        System.out.println("Receive Event:"+pathChildrenCacheEvent.getType());
                    }
                };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start();
    }


    public static void addListenerWithNodeCache(CuratorFramework curatorFramework,String path) throws Exception {
        final NodeCache nodeCache=new NodeCache(curatorFramework,path,false);
        NodeCacheListener nodeCacheListener=new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("Receive Event:"+nodeCache.getCurrentData().getPath());
            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();

    }

}
