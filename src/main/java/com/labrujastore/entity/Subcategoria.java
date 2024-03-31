package com.labrujastore.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "subcategorias")
public class Subcategoria implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subCategoriaId;

    @Column
	private String nombre;

    @Column
	private String nombreUrl;

	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;

	public Subcategoria(){}

	public Subcategoria(
		Integer subCategoriaId, 
		String nombre, 
		String nombreUrl,
		Categoria categoria)
	{
		this.subCategoriaId = subCategoriaId;
		this.nombre = nombre;
		this.nombreUrl = nombreUrl;
		this.categoria = categoria;
	}

	public Integer getSubCategoriaId() {
		return subCategoriaId;
	}

	public void setSubCategoriaId(Integer subCategoriaId) {
		this.subCategoriaId = subCategoriaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUrl() {
		return nombreUrl;
	}

	public void setNombreUrl(String nombreUrl) {
		this.nombreUrl = nombreUrl;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
