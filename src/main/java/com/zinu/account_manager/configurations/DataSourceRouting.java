package com.zinu.account_manager.configurations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

@Component
public class DataSourceRouting extends AbstractRoutingDataSource {

	private ShardZeroConfig shardZeroConfig;
	private ShardOneConfig shardOneConfig;
	private ShardTwoConfig shardTwoConfig;
	Map<Object, Object> dataSourceMap = new HashMap<>();
		

	public DataSourceRouting(ShardZeroConfig shardZeroConfig,
			ShardOneConfig shardOneConfig, ShardTwoConfig shardTwoConfig) throws SQLException {
		this.shardZeroConfig = shardZeroConfig;
		this.shardOneConfig = shardOneConfig;
		this.shardTwoConfig = shardTwoConfig;

	

		DataSource dataSource = DataSourceBuilder.create()
				.url(shardZeroConfig.getUrl())
				.username(shardZeroConfig.getUsername())
				.password(shardZeroConfig.getPassword())
				.build();

		// Check that new connection is 'live'. If not - throw exception
		try(Connection c = dataSource.getConnection()) {
			dataSourceMap.put(0, dataSource);
		}


		DataSource dataSource1 = DataSourceBuilder.create()
				.url(shardOneConfig.getUrl())
				.username(shardOneConfig.getUsername())
				.password(shardOneConfig.getPassword())
				.build();

		// Check that new connection is 'live'. If not - throw exception
		try(Connection c = dataSource1.getConnection()) {
			dataSourceMap.put(1, dataSource1);
			
		}

		DataSource dataSource2 = DataSourceBuilder.create()
				.url(shardTwoConfig.getUrl())
				.username(shardTwoConfig.getUsername())
				.password(shardTwoConfig.getPassword())
				.build();
		// Check that new connection is 'live'. If not - throw exception
		try(Connection c = dataSource2.getConnection()) {
			dataSourceMap.put(2, dataSource2);
			
		}

		this.setTargetDataSources(dataSourceMap);
		this.setDefaultTargetDataSource(dataSource);
		this.afterPropertiesSet();
	}

	@Override
	//@Cacheable(cacheNames = "")
	protected Object determineCurrentLookupKey() {
		return TenantContext.getCurrentTenant();
	}


}
