package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Override
	protected String getKeyspaceName() {
		return "stock_keyspace";
	}

	@Override
	protected String getLocalDataCenter() {
		return "datacenter1";
	}
}
