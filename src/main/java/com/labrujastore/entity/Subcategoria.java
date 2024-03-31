package com.labrujastore.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private String nombre_url;

    /*FALTA TERMINAR ME GANO EL SUEÑO*/

}
