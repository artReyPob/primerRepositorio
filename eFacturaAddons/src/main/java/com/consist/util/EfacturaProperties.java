package com.consist.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.consist.ConstantesMensajes.MSG_ERROR;

public class EfacturaProperties {

	private static Properties PROP = null;

	private static final Logger log = Logger.getLogger("EfacturaProperties");

	static public Properties getProperties(String pPathFile){
		if (PROP==null){
			PROP = new Properties();
			try {
				PROP.load(new FileReader(pPathFile));
			} catch (FileNotFoundException e) {
				log.error(MSG_ERROR.ERROR_FILE_NOT_FOUND(pPathFile), e);
				System.exit(1);
			} catch (IOException e) {
				log.error(MSG_ERROR.ERROR_IO_EXCEPTION(pPathFile), e);
				System.exit(1);
			} catch (Exception e) {
				log.error(MSG_ERROR.ERROR_EXCEPTION(pPathFile), e);
				System.exit(1);
			}
			return PROP;
		}else{
			return PROP;
		}
	}

}
