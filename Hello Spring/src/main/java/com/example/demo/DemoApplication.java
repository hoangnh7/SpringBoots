package com.example.demo;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(DemoApplication.class, args);
//		Dress dress1 = context.getBean(Dress.class);
//		Dress dress2 = context.getBean(Dress.class);
//
//		System.out.println("Dress 1 " +dress1);
//		System.out.println("Dress 2 " +dress2);

		GirlFriend girlFriend = context.getBean(GirlFriend.class);

		System.out.println("GirlFriend: " + girlFriend);
		System.out.println("Outfit of girlfriend: " + girlFriend.outfit);


	}

}
