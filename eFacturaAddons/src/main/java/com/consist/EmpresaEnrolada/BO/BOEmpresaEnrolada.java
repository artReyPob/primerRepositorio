package com.consist.EmpresaEnrolada.BO;

public class BOEmpresaEnrolada {

	private String RUT ;
	private String RZNSOC ;
	private String NRORES ;
	private String FCHRES ;
	private String EMAIL;
	private String URL ;
	private String TIPO="ELECTRONICO";
	
	public BOEmpresaEnrolada(String pRUT, String pRZNSOC, String pEMAIL) {
		//CD_RUT,DS_RAZON_SOCIAL,DS_EMAIL,DS_TIPO
		setRUT(pRUT);
		setRZNSOC(pRZNSOC);
		setEMAIL(pEMAIL);
	}
	
	public String getRUT() {
		return RUT;
	}
	public void setRUT(String rUT) {
		RUT = rUT;
	}
	public String getRZNSOC() {
		return RZNSOC;
	}
	public void setRZNSOC(String rZNSOC) {
		RZNSOC = rZNSOC;
	}
	public String getNRORES() {
		return NRORES;
	}
	public void setNRORES(String nRORES) {
		NRORES = nRORES;
	}
	public String getFCHRES() {
		return FCHRES;
	}
	public void setFCHRES(String fCHRES) {
		FCHRES = fCHRES;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getTIPO() {
		return TIPO;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}
	
	

}
