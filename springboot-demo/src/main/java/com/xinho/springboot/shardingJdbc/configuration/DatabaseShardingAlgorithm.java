package com.xinho.springboot.shardingJdbc.configuration;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description:用于分库策略
 * 使用io.shardingjdbc，就应该实现PreciseShardingAlgorithm接口，然后实现doSharding方法，对应SQL中的=, IN，
 * 还有RangeShardingAlgorithm接口中，对应SQL中的BETWEEN AND，因为我只需要=，in操作，
 * 所以只实现了PreciseShardingAlgorithm接口，你如果都需要，你可以都实现（千万不要忽略了一个类可以实现多个接口）。
 * 如果你使用的当当网的sharding jdbc，那么你需要实现SingleKeyDatabaseShardingAlgorithm这个接口，
 * 实现其中的三个方法，我注释到的部分就是原来我用当当网的sharding jdbc的实现。
 * 分表策略的类，DemoTableShardingAlgorithm
 * @date 2018/6/614:47
 */
public class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        for (String each : collection) {
            if (each.endsWith(Long.parseLong(preciseShardingValue.getValue().toString()) % 2+"")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }
}
