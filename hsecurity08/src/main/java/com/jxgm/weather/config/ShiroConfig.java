package com.jxgm.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;

// enum类型的引用
import org.apache.shiro.realm.jdbc.JdbcRealm.SaltStyle;

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

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
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
       
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
       
        return hashedCredentialsMatcher;
    }

	@Bean
    public Realm realm() {
        JdbcRealm realm = new JdbcRealm();
		realm.setDataSource(dataSource);

		// 若不调用，则只有user roles，没有permission
		realm.setPermissionsLookupEnabled(true);

        realm.setSaltStyle(SaltStyle.COLUMN);
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
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
