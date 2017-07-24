package com.consist.EmpresaEnrolada;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.consist.ConstantesMensajes.MSG_ERROR;
import com.consist.ConstantesMensajes.MSG_USUARIO;
import com.consist.EmpresaEnrolada.BO.BOEmpresaEnrolada;
import com.consist.FileReader.ReadFile;
import com.consist.JDBC.ConnectionFactory;
import com.consist.JDBC.DBUtil;
import com.consist.JDBC.JDBCEmpresaEnrolada;
import com.consist.util.EfacturaProperties;
import com.consist.util.UtilString;
import com.consist.util.UtilVectores;

public class MainClass<E> {
	private static String TEST_PROPS = "/media/creyes/HDD500GB/VM246/efactura/WEB-INF/classes/ConsistEnv_es_AR.properties";

	private static final Logger log = Logger.getLogger(MainClass.class) ;
	private static ArrayList TUPLAS_RECHAZADAS= new ArrayList<String>();
	
	public static void main(String[] args) {
		
		//args=new String[]{"/media/creyes/HDD500GB/Consist/NuevosDesarrollosEfactura/EmpresasEnroladas/ConsistEnv_es_AR.properties"};
		
		if (!UtilVectores.isEmptyVector(args)) TEST_PROPS=args[0];
			
		PropertyConfigurator.configure(EfacturaProperties.getProperties(TEST_PROPS).getProperty("LOG4J"));
		
		log.info(MSG_USUARIO.MSG_WELLCOME);

		MainClass xMiClass = new MainClass();
		xMiClass.myOrchestra();
		
		if(!UtilVectores.isEmptyArrayList(TUPLAS_RECHAZADAS)){
			log.error("SE HAN RECHAZADO:"+TUPLAS_RECHAZADAS.size()+" TUPLAS.");
			log.error(MSG_ERROR.TUPLAS_RECHAZADAS+TUPLAS_RECHAZADAS+"\n\n");
		}
		
		log.info(MSG_USUARIO.MSG_BYEBYE);
		System.exit(0);
	}

	
	
	private ArrayList<BOEmpresaEnrolada> getLineFromCSV(int pIni, int pFin){
		String xRuts = "";
		ArrayList xLsEmpresasEnroladas = new ArrayList<String>();//xLsEmpresasEnroladas.add(xRuts);
		ReadFile xReadFile = new ReadFile();
		ArrayList<String> xEmpresasEnroladas = xReadFile.getLine(EfacturaProperties.getProperties(TEST_PROPS).getProperty("EMPRESAS_ENROLADAS"),pIni , pFin);
		String[] xTuplasCorrectas;
		for (String stringEmEn : xEmpresasEnroladas) {
			try {
				xTuplasCorrectas =UtilString.divide(stringEmEn, ";", 5);
				xRuts+=xTuplasCorrectas[0]+",";
				xLsEmpresasEnroladas.add(new BOEmpresaEnrolada(xTuplasCorrectas[0], xTuplasCorrectas[1], xTuplasCorrectas[4]));
			} catch (Exception e) {

				try {
					xTuplasCorrectas =UtilString.divide(stringEmEn, ";", 6);
					xRuts+=xTuplasCorrectas[0]+",";
					xLsEmpresasEnroladas.add(new BOEmpresaEnrolada(xTuplasCorrectas[0], xTuplasCorrectas[1], xTuplasCorrectas[4]));
				}catch (Exception e1) {
					TUPLAS_RECHAZADAS.add(stringEmEn);
				}
			}
		}
		return xLsEmpresasEnroladas;
	}
	
	public void myOrchestra(){
		Connection xCon = ConnectionFactory.getConnection(EfacturaProperties.getProperties(TEST_PROPS));
		JDBCEmpresaEnrolada xJDBC = new JDBCEmpresaEnrolada(xCon);

		int xCountTotalDel = 0;
		int xCountTotalIns = 0;
		int xPuntero = 0 ;
		
		for(int i=0;;i++){
			ArrayList<BOEmpresaEnrolada> xLsLineas = getLineFromCSV(xPuntero, (xPuntero+1000));
			xPuntero +=1000;
			if (UtilVectores.isEmptyArrayList(xLsLineas)) break;
			int xCountDel = 0 ;
			int xCountIns = 0 ;

			for (Iterator iterator = xLsLineas.iterator(); iterator.hasNext();) {
				BOEmpresaEnrolada boEmpresaEnrolada = (BOEmpresaEnrolada) iterator.next();
				xCountDel += xJDBC.deleteEmpresaEnroladaFull(boEmpresaEnrolada.getRUT());
				xCountIns += xJDBC.insertEmpresaEnroladaFull(boEmpresaEnrolada);
			}
			
			try {
				Thread.sleep(1000);
				xCountTotalDel += xCountDel;
				xCountTotalIns += xCountIns;
				log.debug("Eliminados:"+xCountDel +", Acumulados:"+xCountTotalDel+" registros.");
				log.debug("Insertados:"+xCountIns +", Acumulados:"+xCountTotalIns+" registros.");
			} catch (InterruptedException e) {
				log.error(MSG_ERROR.ERROR_PAUSA,e);
			}		
			
//			try {
//				xCon.commit();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				log.error(MSG_JDBC.ERROR_COMMIT,e);
//				DBUtil.close(xCon);
//				System.exit(1);
//			}
		}
		DBUtil.close(xCon);
	} 

}
