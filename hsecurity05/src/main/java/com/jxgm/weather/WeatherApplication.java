package com.jxgm.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;

@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}

	@Bean
    public Realm realm() {
        return new JdbcRealm();
    }
}
