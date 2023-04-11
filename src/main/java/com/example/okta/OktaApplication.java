package com.example.okta;

import com.example.okta.servicewrapper.service.OktaBuilder;
import com.example.okta.utils.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OktaApplication {

	public static void main(String[] args) {

		SpringApplication.run(OktaApplication.class, args);
	}

}
