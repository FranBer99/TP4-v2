package ar.edu.unnoba.poo2021.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="quirofano")
public class Quirofano implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quirofano")
    private List<Intervencion> intervenciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private static final long serialVersionUID = 1L;

    public Quirofano(){}
    
    public Quirofano(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

	public List<Intervencion> getIntervenciones() {
		return intervenciones;
	}

	public void setIntervenciones(List<Intervencion> intervenciones) {
		this.intervenciones = intervenciones;
	}
}