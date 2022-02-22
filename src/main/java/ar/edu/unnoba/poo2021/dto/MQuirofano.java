package ar.edu.unnoba.poo2021.dto;

import ar.edu.unnoba.poo2021.model.entity.Quirofano;

public class MQuirofano {
	private Quirofano quirofano;
	private boolean rango0;
	private boolean rango1;
	private boolean rango2;
	private boolean rango3;
	private boolean rango4;
	private boolean rango5;
	public Quirofano getQuirofano() {
		return quirofano;
	}
	public void setQuirofano(Quirofano quirofano) {
		this.quirofano = quirofano;
	}
	public boolean isRango0() {
		return rango0;
	}
	public void setRango0(boolean rango0) {
		this.rango0 = rango0;
	}
	public boolean isRango1() {
		return rango1;
	}
	public void setRango1(boolean rango1) {
		this.rango1 = rango1;
	}
	public boolean isRango2() {
		return rango2;
	}
	public void setRango2(boolean rango2) {
		this.rango2 = rango2;
	}
	public boolean isRango3() {
		return rango3;
	}
	public void setRango3(boolean rango3) {
		this.rango3 = rango3;
	}
	public boolean isRango4() {
		return rango4;
	}
	public void setRango4(boolean rango4) {
		this.rango4 = rango4;
	}
	public boolean isRango5() {
		return rango5;
	}
	public void setRango5(boolean rango5) {
		this.rango5 = rango5;
	}
	public MQuirofano(Quirofano quirofano, boolean rango0, boolean rango1, boolean rango2, boolean rango3,
			boolean rango4, boolean rango5) {
		super();
		this.quirofano = quirofano;
		this.rango0 = rango0;
		this.rango1 = rango1;
		this.rango2 = rango2;
		this.rango3 = rango3;
		this.rango4 = rango4;
		this.rango5 = rango5;
	}
	
	public MQuirofano() {
		this.rango0 = false;
		this.rango1 = false;
		this.rango2 = false;
		this.rango3 = false;
		this.rango4 = false;
		this.rango5 = false;
	}	
}
