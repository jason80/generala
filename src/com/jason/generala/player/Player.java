package com.jason.generala.player;

public class Player {
	private String name;
	private Integer[] values = {null, null, null, null, null, null};
	private Integer generalaDoble = null;
	private Integer generala = null;
	private Integer escalera = null;
	private Integer full = null;
	private Integer poker = null;
	private Integer total;
	
	private boolean forceVictory = false;
	
	private String str1, str2, str3, str4, str5, str6;
	private String strEscalera, strFull, strPoker, strGenerala, strGeneralaDoble;
	
	public Player(String name) {
		this.name = name;
		this.update();
	}
	
	public void update() {
		
		total = 0;
		
		for (Integer v : values) {
			if (v != null) total += v;
		}
		
		if (escalera != null) total += escalera;
		if (full != null) total += full;
		if (poker != null) total += poker;
		if (generala != null) total += generala;
		if (generalaDoble != null) total += generalaDoble;
		
		if (values[0] == null) str1 = "";
		else if (values[0] == 0) str1 = "--";
		else str1 = values[0] + "";
		
		if (values[1] == null) str2 = "";
		else if (values[1] == 0) str2 = "--";
		else str2 = values[1] + "";
		
		if (values[2] == null) str3 = "";
		else if (values[2] == 0) str3 = "--";
		else str3 = values[2] + "";
		
		if (values[3] == null) str4 = "";
		else if (values[3] == 0) str4 = "--";
		else str4 = values[3] + "";
		
		if (values[4] == null) str5 = "";
		else if (values[4] == 0) str5 = "--";
		else str5 = values[4] + "";
		
		if (values[5] == null) str6 = "";
		else if (values[5] == 0) str6 = "--";
		else str6 = values[5] + "";
		
		if (escalera == null) strEscalera = "";
		else if (escalera == 0) strEscalera = "--";
		else strEscalera = escalera + "";
		
		if (full == null) strFull = "";
		else if (full == 0) strFull = "--";
		else strFull = full + "";
		
		if (poker == null) strPoker = "";
		else if (poker == 0) strPoker = "--";
		else strPoker = poker + "";
		
		if (generala == null) strGenerala = "";
		else if (generala == 0) strGenerala = "--";
		else strGenerala = generala + "";
		
		if (generalaDoble == null) strGeneralaDoble = "";
		else if (generalaDoble == 0) strGeneralaDoble = "--";
		else strGeneralaDoble = generalaDoble + "";
	}
	
	public Integer getValue(int index) {
		return values[index - 1];
	}
	
	public void setValue(int index, int value) {
		values[index - 1] = value; 
	}
	
	public Integer getEscalera() {
		return escalera;
	}
	
	public void setEscalera(int escalera) {
		this.escalera = escalera;
	}
	
	public Integer getFull() {
		return full;
	}
	
	public void setFull(int full) {
		this.full = full;
	}
	
	public Integer getPoker() {
		return poker;
	}
	
	public void setPoker(int poker) {
		this.poker = poker;
	}
	
	public Integer getGenerala() {
		return generala;
	}
	
	public void setGenerala(int generala) {
		this.generala = generala;
	}
	
	public Integer getGeneralaDoble() {
		return generalaDoble;
	}
	
	public void setGeneralaDoble(Integer generalaDoble) {
		this.generalaDoble = generalaDoble;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStr1() {
		return str1;
	}

	public String getStr2() {
		return str2;
	}
	
	public String getStr3() {
		return str3;
	}
	
	public String getStr4() {
		return str4;
	}
	
	public String getStr5() {
		return str5;
	}
	
	public String getStr6() {
		return str6;
	}
	
	public String getStrEscalera() {
		return strEscalera;
	}
	
	public String getStrFull() {
		return strFull;
	}
	
	public String getStrPoker() {
		return strPoker;
	}
	
	public String getStrGenerala() {
		return strGenerala;
	}
	
	public String getStrGeneralaDoble() {
		return strGeneralaDoble;
	}
	
	public Integer getTotal() {
		return total;
	}
	
	public boolean isGameCompleted() {
		for (Integer val : values) {
			if (val == null) return false;
		}
		
		if (escalera == null) return false;
		if (full == null) return false;
		if (poker == null) return false;
		if (generala == null) return false;
		if (generalaDoble == null) return false;
		
		return true;
	}
	
	public boolean isForceVictory() {
		return forceVictory;
	}
	
	public void setForceVictory(boolean forceVictory) {
		this.forceVictory = forceVictory;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
