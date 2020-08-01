package com.pos.springconfigurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.hibernate.SessionFactory;

@Configuration
@ComponentScan({"com.pos.servicesImpl"
				,"com.pos.DAOImpl"
				,"com.pos.springconfigurations"
				,"com.pos.drawer"
				,"com.pos.models"
				,"com.pos.utility"})
public class AppConfig {
}
