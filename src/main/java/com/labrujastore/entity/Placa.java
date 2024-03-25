package com.labrujastore.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "placas")
public class Placa implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer placaId;
	
	@Column
	private String nombre;
	
	@Column
	private String imagenNombre;
	
	@Column
	private String imagenArchivo;
	
	@Column
	private Integer stock;
	
	@Column
	private Double precio;
	
	@Column
	private String descripcion;
	
	@Column
	private String url;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;
	
	@ManyToMany(mappedBy = "itemsPlaca")
	private Set<Procesador> itemsProcesador = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "placas_rams", 
			joinColumns = @JoinColumn(name = "placa_id"), 
			inverseJoinColumns = @JoinColumn(name = "ram_id"))
	private Set<Ram> itemsRam = new HashSet<>();
	
	public Placa() {
		// TODO Auto-generated constructor stub
	}

	public Placa(Integer placaId, String nombre, String imagenNombre, String imagenArchivo, Integer stock,
			Double precio, String descripcion, String url, Categoria categoria, Set<Procesador> itemsProcesador,
			Set<Ram> itemsRam) {
		this.placaId = placaId;
		this.nombre = nombre;
		this.imagenNombre = imagenNombre;
		this.imagenArchivo = imagenArchivo;
		this.stock = stock;
		this.precio = precio;
		this.descripcion = descripcion;
		this.url = url;
		this.categoria = categoria;
		this.itemsProcesador = itemsProcesador;
		this.itemsRam = itemsRam;
	}

	public Integer getPlacaId() {
		return placaId;
	}

	public void setPlacaId(Integer placaId) {
		this.placaId = placaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagenNombre() {
		return imagenNombre;
	}

	public void setImagenNombre(String imagenNombre) {
		this.imagenNombre = imagenNombre;
	}

	public String getImagenArchivo() {
		return imagenArchivo;
	}

	public void setImagenArchivo(String imagenArchivo) {
		this.imagenArchivo = imagenArchivo;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Set<Procesador> getItemsProcesador() {
		return itemsProcesador;
	}

	public void setItemsProcesador(Set<Procesador> itemsProcesador) {
		this.itemsProcesador = itemsProcesador;
	}

	public Set<Ram> getItemsRam() {
		return itemsRam;
	}

	public void setItemsRam(Set<Ram> itemsRam) {
		this.itemsRam = itemsRam;
	}

	

}
