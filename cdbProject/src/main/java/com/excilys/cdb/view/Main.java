package com.excilys.cdb.view;

import org.slf4j.*;

public class Main {

	static Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		LOGGER.info("Logger launch");
			
		TerminalUI terminal = new TerminalUI();
		terminal.launchUI();
	}

}
