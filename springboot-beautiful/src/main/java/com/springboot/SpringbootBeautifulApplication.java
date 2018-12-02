package com.springboot;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(value = "com.springboot.mapper") // mybatis mapper 扫描
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true) //开启 事物  Service方法上添加注解 @Transactional 便可
public class SpringbootBeautifulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBeautifulApplication.class, args);
	}

	/**
	 insert into department(name) values('name1');
	 insert into department(name) values('name2');
	 insert into department(name) values('name3');
	 insert into department(name) values('name4');
	 insert into department(name) values('name5');
	 insert into department(name) values('name6');
	 insert into department(name) values('name7');
	 insert into department(name) values('name8');
	 insert into department(name) values('name9');
	 insert into department(name) values('name10');
	 insert into department(name) values('name11');
	 insert into department(name) values('name12');
	 insert into department(name) values('name13');
	 insert into department(name) values('name14');
	 insert into department(name) values('name15');
	 insert into department(name) values('name16');
	 insert into department(name) values('name17');
	 insert into department(name) values('name18');
	 insert into department(name) values('name19');
	 insert into department(name) values('name20');
	 */
}

