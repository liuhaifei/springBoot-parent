package com.xinho.springboot.shardingJdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Maps;
import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/518:04
 */


@Configuration
@EnableConfigurationProperties(ShardingMastSlaveConfig.class)
@ConditionalOnProperty({"sharding.jdbc.data-sources.ds_master.url", "sharding.jdbc.master-slave-rule.master-data-source-name"})
public class ShardingDataSourceConfig {

    private Logger logger= LoggerFactory.getLogger(ShardingDataSourceConfig.class);
    @Autowired(required = false)
    private ShardingMastSlaveConfig shardingMastSlaveConfig;


    @Bean("dataSource")
    public DataSource masterSlaveDataSource() throws SQLException {
        shardingMastSlaveConfig.getDataSources().forEach((k, v) -> configDataSource(v));
        //设置分库映射
        Map<String, DataSource> dataSourceMap = Maps.newHashMap();
        //添加两个数据库ds_0,ds_1到map里
        dataSourceMap.putAll(shardingMastSlaveConfig.getDataSources());
        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, shardingMastSlaveConfig.getMasterSlaveRule(), Maps.newHashMap());
        logger.info("masterSlaveDataSource config complete");
        return dataSource;
    }

    private void configDataSource(DruidDataSource druidDataSource) {
        druidDataSource.setMaxActive(20);
        druidDataSource.setInitialSize(1);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setMinIdle(1);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setValidationQuery("select 'x'");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxOpenPreparedStatements(20);
        druidDataSource.setUseGlobalDataSourceStat(true);
        try {
            druidDataSource.setFilters("stat,wall,slf4j");
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
    }
}
