package com.bigshuai.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //表示该类是个配置文件
//自动创建spring容器 自动装配
//bean上需要有注解才可以被扫描如 (ctrl + n) ontroller Service Repository 都有component组建
public class CommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

}
