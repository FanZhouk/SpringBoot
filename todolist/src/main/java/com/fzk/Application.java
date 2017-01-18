package com.fzk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

@SpringBootApplication
public class Application implements EmbeddedServletContainerCustomizer {

	private static int port = 9090;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(port);
	}
}
