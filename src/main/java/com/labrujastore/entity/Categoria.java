package com.labrujastore.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoriaId;

	@Column
	private String nombre;

	@OneToMany(mappedBy = "categoria")
	private Collection<Accesorio> itemsAccesorio = new ArrayList<>();

	@OneToMany(mappedBy = "categoria")
	private Collection<Almacenamiento> itemsAlmacenamiento = new ArrayList<>();

	@OneToMany(mappedBy = "categoria")
	private Collection<Casse> itemsCasse = new ArrayList<>();

	@OneToMany(mappedBy = "categoria")
	private Collection<Fuente> itemsFuente = new ArrayList<>();

	@OneToMany(mappedBy = "categoria")
	private Collection<Laptop> itemsLaptop = new ArrayList<>();

	@OneToMany(mappedBy = "categoria")
	private Collection<Monitor> itemsMonitor = new ArrayList<>();

	@OneToMany(mappedBy = "categoria")
	private Collection<Placa> itemsPlaca = new ArrayList<>();

	@OneToMany(mappedBy = "categoria")
	private Collection<Procesador> itemsProcesador = new ArrayList<>();

	@OneToMany(mappedBy = "categoria")
	private Collection<Ram> itemsRam = new ArrayList<>();

	@OneToMany(mappedBy = "categoria")
	private Collection<Refrigeracion> itemsRefrigeracion = new ArrayList<>();

	@OneToMany(mappedBy = "categoria")
	private Collection<Tarjeta> itemsTarjeta = new ArrayList<>();

	public Categoria() {
	}

	public Categoria(Integer categoriaId, String nombre, Collection<Accesorio> itemsAccesorio,
			Collection<Almacenamiento> itemsAlmacenamiento, Collection<Casse> itemsCasse,
			Collection<Fuente> itemsFuente, Collection<Laptop> itemsLaptop, Collection<Monitor> itemsMonitor,
			Collection<Placa> itemsPlaca, Collection<Procesador> itemsProcesador, Collection<Ram> itemsRam,
			Collection<Refrigeracion> itemsRefrigeracion, Collection<Tarjeta> itemsTarjeta) {
		this.categoriaId = categoriaId;
		this.nombre = nombre;
		this.itemsAccesorio = itemsAccesorio;
		this.itemsAlmacenamiento = itemsAlmacenamiento;
		this.itemsCasse = itemsCasse;
		this.itemsFuente = itemsFuente;
		this.itemsLaptop = itemsLaptop;
		this.itemsMonitor = itemsMonitor;
		this.itemsPlaca = itemsPlaca;
		this.itemsProcesador = itemsProcesador;
		this.itemsRam = itemsRam;
		this.itemsRefrigeracion = itemsRefrigeracion;
		this.itemsTarjeta = itemsTarjeta;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Accesorio> getItemsAccesorio() {
		return itemsAccesorio;
	}

	public void setItemsAccesorio(Collection<Accesorio> itemsAccesorio) {
		this.itemsAccesorio = itemsAccesorio;
	}

	public Collection<Almacenamiento> getItemsAlmacenamiento() {
		return itemsAlmacenamiento;
	}

	public void setItemsAlmacenamiento(Collection<Almacenamiento> itemsAlmacenamiento) {
		this.itemsAlmacenamiento = itemsAlmacenamiento;
	}

	public Collection<Casse> getItemsCasse() {
		return itemsCasse;
	}

	public void setItemsCasse(Collection<Casse> itemsCasse) {
		this.itemsCasse = itemsCasse;
	}

	public Collection<Fuente> getItemsFuente() {
		return itemsFuente;
	}

	public void setItemsFuente(Collection<Fuente> itemsFuente) {
		this.itemsFuente = itemsFuente;
	}

	public Collection<Laptop> getItemsLaptop() {
		return itemsLaptop;
	}

	public void setItemsLaptop(Collection<Laptop> itemsLaptop) {
		this.itemsLaptop = itemsLaptop;
	}

	public Collection<Monitor> getItemsMonitor() {
		return itemsMonitor;
	}

	public void setItemsMonitor(Collection<Monitor> itemsMonitor) {
		this.itemsMonitor = itemsMonitor;
	}

	public Collection<Placa> getItemsPlaca() {
		return itemsPlaca;
	}

	public void setItemsPlaca(Collection<Placa> itemsPlaca) {
		this.itemsPlaca = itemsPlaca;
	}

	public Collection<Procesador> getItemsProcesador() {
		return itemsProcesador;
	}

	public void setItemsProcesador(Collection<Procesador> itemsProcesador) {
		this.itemsProcesador = itemsProcesador;
	}

	public Collection<Ram> getItemsRam() {
		return itemsRam;
	}

	public void setItemsRam(Collection<Ram> itemsRam) {
		this.itemsRam = itemsRam;
	}

	public Collection<Refrigeracion> getItemsRefrigeracion() {
		return itemsRefrigeracion;
	}

	public void setItemsRefrigeracion(Collection<Refrigeracion> itemsRefrigeracion) {
		this.itemsRefrigeracion = itemsRefrigeracion;
	}

	public Collection<Tarjeta> getItemsTarjeta() {
		return itemsTarjeta;
	}

	public void setItemsTarjeta(Collection<Tarjeta> itemsTarjeta) {
		this.itemsTarjeta = itemsTarjeta;
	}

}
