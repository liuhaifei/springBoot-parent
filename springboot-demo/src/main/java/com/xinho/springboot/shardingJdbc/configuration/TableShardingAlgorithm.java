package com.xinho.springboot.shardingJdbc.configuration;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description:分表策略
 * @date 2018/6/614:54
 */
public class TableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        for (String each : collection) {
            System.out.println(preciseShardingValue.getValue().toString());
            System.out.println(Long.parseLong(preciseShardingValue.getValue().toString()) % 2+"");
            if (each.endsWith(Long.parseLong(preciseShardingValue.getValue().toString()) % 2+"")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

}
