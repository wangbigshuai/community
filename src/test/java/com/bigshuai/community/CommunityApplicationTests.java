package com.bigshuai.community;

import com.bigshuai.community.dao.AlphaDao;
import com.bigshuai.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@ContextConfiguration(classes = CommunityApplication.class) // 以它配置类
@SpringBootTest
class CommunityApplicationTests implements ApplicationContextAware { //实现接口是为了得到容器
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		//ApplicationContent 接口
		this.applicationContext = applicationContext;
	}
	@Test
	public void testApplicationContest() {
		System.out.println(applicationContext);
		//GenericWebApplicationContext@f79a760 对象的输出形式 类名 @ hashcode
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);//按照接口类型获取
		System.out.println(alphaDao.select());

		alphaDao = applicationContext.getBean("alphaHibernate",AlphaDao.class); //得到这个bean  再在转型
		System.out.println(alphaDao.select());
	}


	@Test
	public void testBeanManagement() {
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);

		//多次调用 spring容器实例化一个bean 也就是单例

	}

	//主动获取Bean的方式 比较麻烦
	@Test
	public void testBeanConfig() {
		SimpleDateFormat simpleDateFormat =
				applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	//依赖注入 声明属性 set方法 通过构造器注解
	@Autowired
	@Qualifier("alphaHibernate") //指定那个bean
	private AlphaDao alphaDao;

	@Test
	public void testDI() {
		System.out.println(alphaDao);
	}




}
