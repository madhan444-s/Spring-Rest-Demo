package main.java.com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@SpringBootApplication
public class DemoApplication {

	private static final Logger logger = LogManager.getLogger(DemoApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

		logger.info("Application Started Successfully on port 8080.");
		logger.debug("This is a debug message.");
		logger.error("This is an error message.");
	}
}
