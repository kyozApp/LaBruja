package com.labrujastore.entity;

import java.io.Serializable;
import java.util.Base64;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.tika.Tika;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productoId;

    @Column
    private String nombre;

    @Column
    private String imagenNombre;

    @Column(columnDefinition = "longblob")
    private byte[] imagenArchivo;

    @Column
    private Integer stock;

    @Column(nullable = true)
    private String stock_lima;

    @Column(nullable = true)
    private String stock_arequipa;

    @Column
    private Double precio;

    @Column
    private String referencia;

    @Column(length = 1000)
    private String descripcion;

    @Column
    private String url;

    @Column
    private String estado;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    private Collection<Atributos> itemsAtributos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = true)
    private Marca marca;
    
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

    public Producto() {
    }

    public Producto(
            Integer productoId,
            String nombre,
            String imagenNombre,
            byte[] imagenArchivo,
            Integer stock,
            String stock_lima,
            String stock_arequipa,
            Double precio,
            String descripcion,
            String referencia,
            String url,
            String estado,
            Categoria categoria,
            Marca marca) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.imagenNombre = imagenNombre;
        this.imagenArchivo = imagenArchivo;
        this.stock = stock;
        this.stock_lima = stock_lima;
        this.stock_arequipa = stock_arequipa;
        this.precio = precio;
        this.referencia = referencia;
        this.descripcion = descripcion;
        this.url = url;
        this.estado = estado;
        this.categoria = categoria;
        this.marca = marca;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Integer getProductoId() {
        return this.productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getReferencia() {
        return this.referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getStock_lima() {
        return stock_lima;
    }

    public void setStock_lima(String stock_lima) {
        this.stock_lima = stock_lima;
    }

    public String getStock_arequipa() {
        return stock_arequipa;
    }

    public void setStock_arequipa(String stock_arequipa) {
        this.stock_arequipa = stock_arequipa;
    }

    public Collection<Atributos> getItemsAtributos() {
        return itemsAtributos;
    }

    public void setItemsAtributos(Collection<Atributos> itemsAtributos) {
        this.itemsAtributos = itemsAtributos;
    }
}
