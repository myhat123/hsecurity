package com.jxgm.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.authz.AuthorizationException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class ShiroConfig {

	private static Logger log = LoggerFactory.getLogger(ShiroConfig.class);

    // 由spring jdbc提供DataSource Bean
	@Autowired
	DataSource dataSource;
	
	@Bean
    public Realm realm() {
        JdbcRealm realm = new JdbcRealm();
		realm.setDataSource(dataSource);

		// 若不调用，则只有user roles，没有permission
		realm.setPermissionsLookupEnabled(true);
		return realm;
	}

	@Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition filter
          = new DefaultShiroFilterChainDefinition();

        filter.addPathDefinition("/secure", "authc");
        filter.addPathDefinition("/**", "anon");

        return filter;
    }

}
