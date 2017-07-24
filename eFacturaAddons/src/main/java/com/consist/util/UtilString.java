package com.consist.util;

import org.apache.log4j.Logger;

import com.consist.ConstantesMensajes.MSG_ERROR;

public class UtilString {
	private static final Logger log = Logger.getLogger(UtilString.class) ;
	
	/**Controla que el String sea null*/
	static public Boolean isNull(String pString){
		if (null==pString)
			return null;
		if (pString.trim().length()==0)
			return null;
		return new Boolean(true);
	}
	
	/**Split simple
	 * */
	static public String[] divide(String pString, String pDivisor){
		if (isNull(pString)==null) return null;
		return pString.split(pDivisor);
		
	}

	/**Split controlando el numero de resultados esperados
	 * @throws Exception 
	 * */
	static public String[] divide(String pString, String pDivisor, int pNroPartes) throws Exception{
		if (isNull(pString)==null) return null;
		if (pNroPartes <  2) return null;
		
		String[] xDividido =  pString.split(pDivisor);
		if(xDividido.length!=pNroPartes){
//			log.error(MSG_ERROR.ERROR_SPLIT_CONTROLADO);
			throw new Exception(MSG_ERROR.ERROR_SPLIT_CONTROLADO);
		}
		return xDividido;
	}

	/**Valida que la Snitch este en el Quidditch*/
	static public Boolean contiene(String pQuiddich, String pSnitch){
		if (isNull(pQuiddich)==null) return null;
		if (isNull(pSnitch)==null) return null;
		return new Boolean(pQuiddich.toUpperCase().contains(pSnitch.toUpperCase()));
	}
	
	/**Valida que el string tenga el tamaño adecuado*/
	static public Boolean isXL(String pString, int pSize){
		if (isNull(pString)==null) return false;
		if (0>pSize) return false;
		if (pSize >= pString.length()) return true;
		return false;
	}


	/**Valida que el string tenga el tamaño adecuado*/
	static public String subString(String pString, int pSize){
		if (isNull(pString)==null) return "";
		if (0>pSize) return "";
		if (pString.length()>pSize){ /*System.out.println("pString::"+pString+ "  Tamaño:"+pString.substring(0,pSize).length() );*/return pString.substring(0,pSize); }
		return pString;
		
	}
}
