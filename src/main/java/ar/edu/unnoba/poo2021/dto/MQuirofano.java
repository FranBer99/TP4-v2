package ar.edu.unnoba.poo2021.dto;

import ar.edu.unnoba.poo2021.model.entity.Intervencion;
import ar.edu.unnoba.poo2021.model.entity.Quirofano;

public class MQuirofano {
	private Quirofano quirofano;
	private Intervencion rango0;
	private Intervencion rango1;
	private Intervencion rango2;
	private Intervencion rango3;
	private Intervencion rango4;
	private Intervencion rango5;
	
	public Intervencion getRango0() {
		return rango0;
	}
	public void setRango0(Intervencion rango0) {
		this.rango0 = rango0;
	}
	public Intervencion getRango1() {
		return rango1;
	}
	public void setRango1(Intervencion rango1) {
		this.rango1 = rango1;
	}
	public Intervencion getRango2() {
		return rango2;
	}
	public void setRango2(Intervencion rango2) {
		this.rango2 = rango2;
	}
	public Intervencion getRango3() {
		return rango3;
	}
	public void setRango3(Intervencion rango3) {
		this.rango3 = rango3;
	}
	public Intervencion getRango4() {
		return rango4;
	}
	public void setRango4(Intervencion rango4) {
		this.rango4 = rango4;
	}
	public Intervencion getRango5() {
		return rango5;
	}
	public void setRango5(Intervencion rango5) {
		this.rango5 = rango5;
	}
	public Quirofano getQuirofano() {
		return quirofano;
	}
	public void setQuirofano(Quirofano quirofano) {
		this.quirofano = quirofano;
	}
	
	public MQuirofano(Quirofano quirofano, Intervencion rango0,Intervencion rango1, Intervencion rango2, Intervencion rango3,
			Intervencion rango4, Intervencion rango5) {
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
		this.rango0 = null;
		this.rango1 = null;
		this.rango2 = null;
		this.rango3 = null;
		this.rango4 = null;
		this.rango5 = null;
	}
}
