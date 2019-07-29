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

import org.springframework.http.HttpStatus;
// import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@ControllerAdvice
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
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/login", "authc"); // need to accept POSTs from the login form
        chainDefinition.addPathDefinition("/logout", "logout");
        return chainDefinition;
    }
}
