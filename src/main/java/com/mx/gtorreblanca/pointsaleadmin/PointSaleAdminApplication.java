package com.mx.gtorreblanca.pointsaleadmin;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PointSaleAdminApplication {
	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication.run(PointSaleAdminApplication.class, args);
	}
}
