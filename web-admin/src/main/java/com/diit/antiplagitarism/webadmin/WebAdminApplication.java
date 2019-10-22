package com.diit.antiplagitarism.webadmin;

import com.diit.antiplagitarism.socket.web.ProxyMasterCommunicator;
import lombok.var;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebAdminApplication.class, args);

	}

}
