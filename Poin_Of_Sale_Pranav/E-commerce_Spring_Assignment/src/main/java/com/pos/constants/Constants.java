package com.pos.constants;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Constants {
	public static ApplicationContext context= new AnnotationConfigApplicationContext(com.pos.springconfigurations.AppConfig.class);
}
