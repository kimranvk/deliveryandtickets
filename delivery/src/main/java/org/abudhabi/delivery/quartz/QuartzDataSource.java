package org.abudhabi.delivery.quartz;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzDataSource {
	private static final Logger logger = LoggerFactory.getLogger(QuartzDataSource.class);
	// to separate the physical locations for each instance of microservice.
	@Value("${app.key}")
	private String h2DirectoryName;

	// private String TMP_DIR = // System.getProperty("java.io.tmpdir");

	@Bean
	public DataSource dataSource() {
		JdbcDataSource ds = new JdbcDataSource();
		String url = "jdbc:h2:" + System.getProperty("java.io.tmpdir") + "/datasource/" + h2DirectoryName + System.currentTimeMillis()+ "/test";
		ds.setURL(url);
		logger.debug("H2 URL:{}", url);
		return ds;
	}
}