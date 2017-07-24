package com.consist.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.consist.ConstantesMensajes.MSG_JDBC;
import com.consist.EmpresaEnrolada.MainClass;
import com.consist.EmpresaEnrolada.BO.BOEmpresaEnrolada;
import com.consist.util.UtilString;

public class JDBCEmpresaEnrolada {
	private static final Logger log = Logger.getLogger(JDBCEmpresaEnrolada.class) ;

	Connection CONECTION ;

	public JDBCEmpresaEnrolada(Connection pCon){
		CONECTION = pCon;
	}

	public int insertEmpresaEnroladaFull(BOEmpresaEnrolada pBOEmEn){

		String sql = "INSERT INTO empresa_enrolada_full "
				+ "(CD_RUT, DS_RAZON_SOCIAL, DS_EMAIL, DS_TIPO, CD_USUARIO_ALTA, CD_USUARIO_ACTUAL, DT_ACTUAL, DT_ALTA) "
				+ "VALUES (?, ?, ?, ?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";

		int rowsInserted =0;
		PreparedStatement statement = null;
		try {
			statement = CONECTION.prepareStatement(sql);
			statement.setString(1, pBOEmEn.getRUT());
			statement.setString(2, UtilString.subString(pBOEmEn.getRZNSOC(),50));
			statement.setString(3, pBOEmEn.getEMAIL());
			statement.setString(4, pBOEmEn.getTIPO());
			statement.setString(5, "Consist");
			statement.setString(6, "Consist");

			
			rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				//log.info(MSG_JDBC.MSG_INSERT+rowsInserted);
			}else{
				log.info(MSG_JDBC.ERROR_INSERT + "\n======> Query fallida: "+statement);
			}
		} catch (SQLException e) {
			log.error(MSG_JDBC.ERROR_INSERT,e);
		}finally {
			DBUtil.close(statement);
		}
		return rowsInserted;
	}

	public int deleteEmpresaEnroladaFull(String pRUT) {
		String sql = "DELETE FROM empresa_enrolada_full WHERE cd_rut=?";
		int rowsDeleted =0;

		PreparedStatement statement = null;
		try {
			statement = CONECTION.prepareStatement(sql);
			statement.setString(1, pRUT);

			rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				//log.info(MSG_JDBC.MSG_DELETE+rowsDeleted);
			}else{
			//	log.info(MSG_JDBC.ERROR_DELETE);
			}
		} catch (SQLException e) {
			log.error(MSG_JDBC.ERROR_DELETE,e);
		}finally {
			DBUtil.close(statement);
		}
		return rowsDeleted;
	}


}


