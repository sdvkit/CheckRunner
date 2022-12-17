package com.sdv.kit.checkrunner;

import com.sdv.kit.checkrunner.model.Check;
import com.sdv.kit.checkrunner.service.CheckService;
import com.sdv.kit.checkrunner.util.FileCheckWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CheckRunnerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(CheckRunnerApplication.class, args);

		Check check = context.getBean(CheckService.class).build(args);
		context.getBean(FileCheckWriter.class).write(check);
	}
}
