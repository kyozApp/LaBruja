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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rams")
public class Ram implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ramId;
	
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
	
	@ManyToMany(mappedBy = "itemsRam")
	private Set<Placa> itemsPlaca = new HashSet<>();
	
	public Ram() {
		// TODO Auto-generated constructor stub
	}

	public Ram(Integer ramId, String nombre, String imagenNombre, String imagenArchivo, Integer stock, Double precio,
			String descripcion, String url, Categoria categoria, Set<Placa> itemsPlaca) {
		this.ramId = ramId;
		this.nombre = nombre;
		this.imagenNombre = imagenNombre;
		this.imagenArchivo = imagenArchivo;
		this.stock = stock;
		this.precio = precio;
		this.descripcion = descripcion;
		this.url = url;
		this.categoria = categoria;
		this.itemsPlaca = itemsPlaca;
	}

	public Integer getRamId() {
		return ramId;
	}

	public void setRamId(Integer ramId) {
		this.ramId = ramId;
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

	public Set<Placa> getItemsPlaca() {
		return itemsPlaca;
	}

	public void setItemsPlaca(Set<Placa> itemsPlaca) {
		this.itemsPlaca = itemsPlaca;
	}

	

}
