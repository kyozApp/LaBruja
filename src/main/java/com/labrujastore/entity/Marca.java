package com.labrujastore.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import org.apache.tika.Tika;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "marcas")
public class Marca implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer marcaId;

    @Column
    private String nombre;

    @Column(nullable = false, unique = true)
    private String nombreUrl;

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

    @OneToMany(mappedBy = "marca")
    private Collection<Accesorio> itemsAccesorio = new ArrayList<>();
    
    @OneToMany(mappedBy = "marca")
    private Collection<Almacenamiento> itemsAlmacenamiento = new ArrayList<>();

    @OneToMany(mappedBy = "marca")
    private Collection<Combo> itemsCombo = new ArrayList<>();

    @OneToMany(mappedBy = "marca")
    private Collection<Casse> itemsCasse = new ArrayList<>();

    @OneToMany(mappedBy = "marca")
    private Collection<Fuente> itemsFuente = new ArrayList<>();

    @OneToMany(mappedBy = "marca")
    private Collection<Laptop> itemsLaptop = new ArrayList<>();

    @OneToMany(mappedBy = "marca")
    private Collection<Monitor> itemsMonitor = new ArrayList<>();

    @OneToMany(mappedBy = "marca")
    private Collection<Placa> itemsPlaca = new ArrayList<>();

    @OneToMany(mappedBy = "marca")
    private Collection<Procesador> itemsProcesador = new ArrayList<>();

    @OneToMany(mappedBy = "marca")
    private Collection<Ram> itemsRam = new ArrayList<>();

    @OneToMany(mappedBy = "marca")
    private Collection<Refrigeracion> itemsRefrigeracion = new ArrayList<>();

    @OneToMany(mappedBy = "marca")
    private Collection<Tarjeta> itemsTarjeta = new ArrayList<>();

    @OneToMany(mappedBy = "marca")
    private Collection<Producto> itemsProducto = new ArrayList<>();

    public Marca() {
    }

    public Marca(Integer marcaId, String nombre, String nombreUrl, String imagenNombre, byte[] imagenArchivo) {
        this.marcaId = marcaId;
        this.nombre = nombre;
        this.nombreUrl = nombreUrl;
        this.imagenNombre = imagenNombre;
        this.imagenArchivo = imagenArchivo;
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

    public Collection<Combo> getItemsCombo() {
        return itemsCombo;
    }

    public void setItemsCombo(Collection<Combo> itemsCombo) {
        this.itemsCombo = itemsCombo;
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

    public Collection<Producto> getItemsProducto() {
        return itemsProducto;
    }

    public void setItemsProducto(Collection<Producto> itemsProducto) {
        this.itemsProducto = itemsProducto;
    }

    public Integer getMarcaId() {
        return this.marcaId;
    }

    public void setMarcaId(Integer marcaId) {
        this.marcaId = marcaId;
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

    public String getNombreUrl() {
        return nombreUrl;
    }

    public void setNombreUrl(String nombreUrl) {
        this.nombreUrl = nombreUrl;
    }

}
