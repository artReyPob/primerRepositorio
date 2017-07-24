package com.consist.EmpresaEnrolada.BT;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.consist.EmpresaEnrolada.BO.BOEmpresaEnrolada;
import com.consist.JDBC.ConnectionFactory;
import com.consist.JDBC.DBUtil;
import com.consist.util.EfacturaProperties;

public class BTEmpresaEnrolada {

	private static final Logger log = Logger.getLogger(EfacturaProperties.class);
	private Connection connection;
	private Statement statement;

	private static final String TEST_PROPS = "/media/creyes/HDD500GB/VM246/efactura/WEB-INF/classes/ConsistEnv_es_AR.properties";
	Properties props = EfacturaProperties.getProperties(TEST_PROPS);

	public List<BOEmpresaEnrolada> getLsEmpresasEnroladas(){
		ArrayList<BOEmpresaEnrolada> xEmpresas= new ArrayList<BOEmpresaEnrolada>();
		
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection(props);
			statement = connection.createStatement();
			rs =statement.executeQuery("SELECT CD_RUT,DS_RAZON_SOCIAL,DS_EMAIL,DS_TIPO FROM EMPRESA_ENROLADA"); 
			
			while (rs.next()){
				xEmpresas.add(new BOEmpresaEnrolada(rs.getString("CD_RUT"), rs.getString("DS_RAZON_SOCIAL"), rs.getString("DS_EMAIL")));
			}
			
		} catch (SQLException e) {
			log.error("Error recuperando el listado de empresas", e);
		}finally {
			DBUtil.close(connection);
			DBUtil.close(rs);
			DBUtil.close(statement);
		}
		
		return xEmpresas;
	} 
	
	public BOEmpresaEnrolada getEmpresaEnrolada(String pRut){
		BOEmpresaEnrolada xEmpresa =null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection(props);
			statement = connection.createStatement();
			rs =statement.executeQuery("SELECT CD_RUT,DS_RAZON_SOCIAL,DS_EMAIL,DS_TIPO FROM EMPRESA_ENROLADA WHERE CD_RUT = '"+pRut+"'"); 
			
			while (rs.next()){
				xEmpresa = new BOEmpresaEnrolada(rs.getString("CD_RUT"), rs.getString("DS_RAZON_SOCIAL"), rs.getString("DS_EMAIL"));
			}
			
		} catch (SQLException e) {
			log.error("Error recuperando la empresa enrolada RUT ="+pRut, e);
		}finally {
			DBUtil.close(connection);
			DBUtil.close(rs);
			DBUtil.close(statement);
		}
		return xEmpresa;
	} 
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BTEmpresaEnrolada xBTEmpresa = new BTEmpresaEnrolada();
		List xLsEmpre = xBTEmpresa.getLsEmpresasEnroladas();
		for (Iterator iterator = xLsEmpre.iterator(); iterator.hasNext();) {
			BOEmpresaEnrolada xEmpresaEnrolada = (BOEmpresaEnrolada) iterator.next();
			System.out.println(xEmpresaEnrolada.getRUT());
			
		}
		
	}

}
