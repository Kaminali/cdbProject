package com.excilys.cdb.view;

import org.slf4j.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	static Logger LOGGER = LoggerFactory.getLogger(Main.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		LOGGER.info("Logger launch");

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		TerminalUI terminal = (TerminalUI) context.getBean("terminalUI");
		terminal.launchUI();
	}

}
