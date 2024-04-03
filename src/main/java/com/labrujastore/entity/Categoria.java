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

	@Column
	private String nombreUrl;

	@OneToMany(mappedBy = "categoria")
	private Collection<Accesorio> itemsAccesorio = new ArrayList<>();

	@OneToMany(mappedBy = "categoria")
	private Collection<Almacenamiento> itemsAlmacenamiento = new ArrayList<>();

	@OneToMany(mappedBy = "categoria")
	private Collection<Combo> itemsCombo = new ArrayList<>();

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

	@OneToMany(mappedBy = "categoria")
	private Collection<Producto> itemsProducto = new ArrayList<>();

	@OneToMany(mappedBy = "categoria")
	private Collection<Subcategoria> subcategorias = new ArrayList<>();

	public Categoria() {
	}

	public Categoria(Integer categoriaId, String nombre, String nombreUrl, Collection<Accesorio> itemsAccesorio,
			Collection<Almacenamiento> itemsAlmacenamiento,
			Collection<Combo> itemsCombo,
			Collection<Casse> itemsCasse,
			Collection<Fuente> itemsFuente, Collection<Laptop> itemsLaptop, Collection<Monitor> itemsMonitor,
			Collection<Placa> itemsPlaca, Collection<Procesador> itemsProcesador, Collection<Ram> itemsRam,
			Collection<Refrigeracion> itemsRefrigeracion, Collection<Tarjeta> itemsTarjeta,
			Collection<Producto> itemsProducto, Collection<Subcategoria> subcategorias) {
		this.categoriaId = categoriaId;
		this.nombre = nombre;
		this.nombreUrl = nombreUrl;
		this.itemsAccesorio = itemsAccesorio;
		this.itemsAlmacenamiento = itemsAlmacenamiento;
		this.itemsCombo = itemsCombo;
		this.itemsCasse = itemsCasse;
		this.itemsFuente = itemsFuente;
		this.itemsLaptop = itemsLaptop;
		this.itemsMonitor = itemsMonitor;
		this.itemsPlaca = itemsPlaca;
		this.itemsProcesador = itemsProcesador;
		this.itemsRam = itemsRam;
		this.itemsRefrigeracion = itemsRefrigeracion;
		this.itemsTarjeta = itemsTarjeta;
		this.itemsProducto = itemsProducto;
		this.subcategorias = subcategorias;
	}

	public Integer getCategoriaId() {
		return this.categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUrl() {
		return this.nombreUrl;
	}

	public void setNombreUrl(String nombreUrl) {
		this.nombreUrl = nombreUrl;
	}

	public Collection<Accesorio> getItemsAccesorio() {
		return this.itemsAccesorio;
	}

	public void setItemsAccesorio(Collection<Accesorio> itemsAccesorio) {
		this.itemsAccesorio = itemsAccesorio;
	}

	public Collection<Almacenamiento> getItemsAlmacenamiento() {
		return this.itemsAlmacenamiento;
	}

	public void setItemsAlmacenamiento(Collection<Almacenamiento> itemsAlmacenamiento) {
		this.itemsAlmacenamiento = itemsAlmacenamiento;
	}

	public Collection<Casse> getItemsCasse() {
		return this.itemsCasse;
	}

	public void setItemsCasse(Collection<Casse> itemsCasse) {
		this.itemsCasse = itemsCasse;
	}

	public Collection<Fuente> getItemsFuente() {
		return this.itemsFuente;
	}

	public void setItemsFuente(Collection<Fuente> itemsFuente) {
		this.itemsFuente = itemsFuente;
	}

	public Collection<Laptop> getItemsLaptop() {
		return this.itemsLaptop;
	}

	public void setItemsLaptop(Collection<Laptop> itemsLaptop) {
		this.itemsLaptop = itemsLaptop;
	}

	public Collection<Monitor> getItemsMonitor() {
		return this.itemsMonitor;
	}

	public void setItemsMonitor(Collection<Monitor> itemsMonitor) {
		this.itemsMonitor = itemsMonitor;
	}

	public Collection<Placa> getItemsPlaca() {
		return this.itemsPlaca;
	}

	public void setItemsPlaca(Collection<Placa> itemsPlaca) {
		this.itemsPlaca = itemsPlaca;
	}

	public Collection<Procesador> getItemsProcesador() {
		return this.itemsProcesador;
	}

	public void setItemsProcesador(Collection<Procesador> itemsProcesador) {
		this.itemsProcesador = itemsProcesador;
	}

	public Collection<Ram> getItemsRam() {
		return this.itemsRam;
	}

	public void setItemsRam(Collection<Ram> itemsRam) {
		this.itemsRam = itemsRam;
	}

	public Collection<Refrigeracion> getItemsRefrigeracion() {
		return this.itemsRefrigeracion;
	}

	public void setItemsRefrigeracion(Collection<Refrigeracion> itemsRefrigeracion) {
		this.itemsRefrigeracion = itemsRefrigeracion;
	}

	public Collection<Tarjeta> getItemsTarjeta() {
		return this.itemsTarjeta;
	}

	public void setItemsTarjeta(Collection<Tarjeta> itemsTarjeta) {
		this.itemsTarjeta = itemsTarjeta;
	}

	public Collection<Producto> getItemsProducto() {
		return this.itemsProducto;
	}

	public void setItemsProducto(Collection<Producto> itemsProducto) {
		this.itemsProducto = itemsProducto;
	}

	public Collection<Subcategoria> getSubcategorias() {
		return this.subcategorias;
	}

	public void setSubcategorias(Collection<Subcategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

	public Collection<Combo> getItemsCombo() {
		return this.itemsCombo;
	}

	public void setgetItemsCombo(Collection<Combo> itemsCombo) {
		this.itemsCombo = itemsCombo;
	}
}
