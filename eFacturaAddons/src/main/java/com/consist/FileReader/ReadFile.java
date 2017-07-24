package com.consist.FileReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.consist.ConstantesMensajes.MSG_ERROR;

public class ReadFile {
	private static final Logger log = Logger.getLogger(ReadFile.class) ;

	/**
	 * Lectura de archivos linea a linea. 
	 * */
	 public ArrayList<String> getLine(String pPath, int pStartLine, int pEndLine){
		FileInputStream inputStream = null;
		Scanner sc = null;
		ArrayList<String> xEmpresasEncontradas = new ArrayList<String>();
		try {
			try {
				inputStream = new FileInputStream(pPath);
			} catch (FileNotFoundException e) {
				log.error(MSG_ERROR.ERROR_FILE_NOT_FOUND(pPath), e);
			}
			sc = new Scanner(inputStream, "UTF-8");
			String line = null;
			int xCount =0;
						
			while ((line = sc.nextLine()) != null) {
				if (pStartLine==0){
						xEmpresasEncontradas.add(line);
				}else{
					if (xCount>=pStartLine){
						xEmpresasEncontradas.add(line);
					}
				}
				xCount++;
				if (xCount==pEndLine) break;
				
			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				try {
					throw sc.ioException();
				} catch (IOException e) {
					log.error(MSG_ERROR.ERROR_IO_EXCEPTION(),e);
				}
			}
		} catch (Exception e) {
			log.error(MSG_ERROR.ERROR_EXCEPTION(),e);
		} 
		
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error(MSG_ERROR.ERROR_IO_EXCEPTION(),e);
				}
			}
			if (sc != null) {
				sc.close();
			}
		}
		return xEmpresasEncontradas;
	}

}
