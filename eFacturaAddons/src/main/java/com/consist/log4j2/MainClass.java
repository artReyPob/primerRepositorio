package com.consist.log4j2;

import org.apache.log4j.Logger;

public class MainClass {

	private static final Logger log = Logger.getLogger(MainClass.class.getName()) ;
	public static void main(String[] args) {

		log.debug("Debug Message Logged !!!");
		log.info("Info Message Logged !!!");
		log.error("Error Message Logged !!!", new NullPointerException("NullError"));
	}
}
