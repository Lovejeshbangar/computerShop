package ca.sheridancollege.databases;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {
	
	@Bean
	public NamedParameterJdbcTemplate namesParameterJdbcTemplate(DataSource dataSource)
	{
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dd = new DriverManagerDataSource();
		dd.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dd.setUrl("jdbc:mysql://localhost/assignment4");
		dd.setUsername("root");
		dd.setPassword("root");
		return dd;
	}

}
