package com.consist.util;

import java.util.ArrayList;
import java.util.List;

public class UtilVectores {
	
	public static Boolean isEmptyList(List pList){
		if (pList==null){return new Boolean(false);}
		if (pList.size()==0){return new Boolean(false);}
		return new Boolean(true);
	}
	
	public static Boolean isEmptyArrayList(ArrayList pList){
		if (pList==null){return new Boolean(true);}
		if (pList.size()==0){return new Boolean(true);}
		return new Boolean(false);
	}

	public static Boolean isEmptyVector(String[] pVector){
		if (pVector==null){return new Boolean(true);}
		if (pVector.length==0){return new Boolean(true);}
		return new Boolean(false);
	}

	
	
}
