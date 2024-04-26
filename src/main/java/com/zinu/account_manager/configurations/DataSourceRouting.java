package com.zinu.account_manager.configurations;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

@Component
public class DataSourceRouting extends AbstractRoutingDataSource {

    private ShardZeroConfig shardZeroConfig;
    private ShardOneConfig shardOneConfig;
	private ShardTwoConfig shardTwoConfig;

    public  DataSourceRouting(ShardZeroConfig shardZeroConfig,
    ShardOneConfig shardOneConfig, ShardTwoConfig shardTwoConfig) {
		this.shardZeroConfig = shardZeroConfig;
		this.shardOneConfig = shardOneConfig;
        this.shardTwoConfig = shardTwoConfig;

		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put(0, shardZeroDataSource());
		dataSourceMap.put(1, shardOneDataSource());
        dataSourceMap.put(2, shardTwoDataSource());
		this.setTargetDataSources(dataSourceMap);
		this.setDefaultTargetDataSource(shardZeroDataSource());
	}
    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getCurrentTenant();
    }

    public DataSource shardZeroDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(shardZeroConfig.getUrl());
		dataSource.setUsername(shardZeroConfig.getUsername());
		dataSource.setPassword(shardZeroConfig.getPassword());
		return dataSource;
	}

    public DataSource shardOneDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(shardOneConfig.getUrl());
		dataSource.setUsername(shardOneConfig.getUsername());
		dataSource.setPassword(shardOneConfig.getPassword());
		return dataSource;
	}

	public DataSource shardTwoDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(shardTwoConfig.getUrl());
		dataSource.setUsername(shardTwoConfig.getUsername());
		dataSource.setPassword(shardTwoConfig.getPassword());
		return dataSource;
	}


}

