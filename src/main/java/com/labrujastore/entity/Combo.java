package com.labrujastore.entity;

import java.io.Serializable;
import java.util.Base64;

import org.apache.tika.Tika;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "combos")
public class Combo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comboId;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private String imagenNombre;

    @Column(columnDefinition = "longblob")
    private byte[] imagenArchivo;

    // convertir file en String base64
    public String getBase64Image() {
        String base64 = Base64.getEncoder().encodeToString(this.imagenArchivo);
        return base64;
    }

    // obtener tipo de imagen (jpeg,jpg,png,etc)
    public String getTypeImage() {
        String typeImage = new Tika().detect(this.imagenArchivo);
        return typeImage;
    }

    public Combo() {
    }

    public Combo(Integer comboId, String nombre, String descripcion, String imagenNombre, byte[] imagenArchivo) {
        this.comboId = comboId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenNombre = imagenNombre;
        this.imagenArchivo = imagenArchivo;
    }

    public Integer getComboId() {
        return this.comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenNombre() {
        return this.imagenNombre;
    }

    public void setImagenNombre(String imagenNombre) {
        this.imagenNombre = imagenNombre;
    }

    public byte[] getImagenArchivo() {
        return this.imagenArchivo;
    }

    public void setImagenArchivo(byte[] imagenArchivo) {
        this.imagenArchivo = imagenArchivo;
    }

}
