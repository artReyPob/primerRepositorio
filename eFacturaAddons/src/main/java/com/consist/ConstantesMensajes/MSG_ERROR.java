package com.consist.ConstantesMensajes;

public class MSG_ERROR {
	
	
	public static final String TUPLAS_RECHAZADAS = "Se encontraron las siguentes tuplas erroneas:\n";
	public static final String ERROR_PAUSA = "ERROR DURANTE LA PAUSA EN LA EJECUCIÓN";
	
	public static final String ERROR_STRING_NULO = "ERROR DURANTE LA PAUSA EN LA EJECUCIÓN";
	
	/*UtilString*/
	public static final String ERROR_SPLIT_CONTROLADO = "El numero de partes resultantes es diferente del esperado";
	
	
	/*EfacturaProperties && ReadFile*/
	static public final String ERROR_FILE_NOT_FOUND(String pString){
		return "El archivo "+pString+" no ha sido encontrado en la ruta indicada, verificar ruta... ";
	}
	static public final String ERROR_IO_EXCEPTION(String pString){
		return "El archivo "+pString+" presenta problemas indeterminados... ";
	}
	
	static public final String ERROR_IO_EXCEPTION(){
		return "Se presentaron problemas indeterminados en el Streaming... ";
	}
	
	static public final String ERROR_EXCEPTION(){
		return "ERROR GENERICO DESCONOCIDO.";
	}
	
	static public final String ERROR_EXCEPTION(String pString){
		return "Algo salio MAL al cargar el archivo de propiedad indicado en la ruta: "+pString + ".";
	}
	
}
