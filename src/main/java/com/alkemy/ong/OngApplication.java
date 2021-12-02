package com.alkemy.ong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;




@SpringBootApplication
@EnableJpaRepositories("com.alkemy.ong.repository")
@EntityScan("com.alkemy.ong.model.entity")
@ComponentScans({@ComponentScan("com.alkemy.ong.controller"),@ComponentScan("com.alkemy.ong.auth.config")})
public class OngApplication {

	public static void main(String[] args) {SpringApplication.run(OngApplication.class, args);
	}

}
