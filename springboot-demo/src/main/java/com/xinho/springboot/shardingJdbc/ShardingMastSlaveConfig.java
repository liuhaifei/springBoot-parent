package com.xinho.springboot.shardingJdbc;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/516:57
 */

@PropertySource("shard.properties")
@ConfigurationProperties(prefix = "classpath:sharding.jdbc")
public class ShardingMastSlaveConfig {

    private Map<String,DruidDataSource> dataSources=new HashMap<>();

    private MasterSlaveRuleConfiguration masterSlaveRule;

    public Map<String, DruidDataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Map<String, DruidDataSource> dataSources) {
        this.dataSources = dataSources;
    }

    public MasterSlaveRuleConfiguration getMasterSlaveRule() {
        return masterSlaveRule;
    }

    public void setMasterSlaveRule(MasterSlaveRuleConfiguration masterSlaveRule) {
        this.masterSlaveRule = masterSlaveRule;
    }
}