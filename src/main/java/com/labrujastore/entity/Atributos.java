package com.labrujastore.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "atributos")
public class Atributos implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer atributoId;

    @Column(nullable = true)
    private String titulo;

    @Column(nullable = true)
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "laptop_id", nullable = true)
    private Laptop laptop;

    @ManyToOne
    @JoinColumn(name = "accesorio_id", nullable = true)
    private Accesorio accesorio;

    @ManyToOne
    @JoinColumn(name = "almacenamiento_id", nullable = true)
    private Almacenamiento almacenamiento;

    @ManyToOne
    @JoinColumn(name = "casse_id", nullable = true)
    private Casse casse;

    @ManyToOne
    @JoinColumn(name = "combo_id", nullable = true)
    private Combo combo;

    @ManyToOne
    @JoinColumn(name = "fuente_id", nullable = true)
    private Fuente fuente;

    @ManyToOne
    @JoinColumn(name = "monitor_id", nullable = true)
    private Monitor monitor;

    @ManyToOne
    @JoinColumn(name = "placa_id", nullable = true)
    private Placa placa;

    @ManyToOne
    @JoinColumn(name = "procesador_id", nullable = true)
    private Procesador procesador;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = true)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "ram_id", nullable = true)
    private Ram ram;

    @ManyToOne
    @JoinColumn(name = "refrigeración_id", nullable = true)
    private Refrigeracion refrigeracion;

    @ManyToOne
    @JoinColumn(name = "tarjeta_id", nullable = true)
    private Tarjeta tarjeta;

    public Atributos() {
    }

    public Atributos(Integer atributoId, String titulo, String contenido) {
        this.atributoId = atributoId;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public Integer getAtributoId() {
        return atributoId;
    }

    public void setAtributoId(Integer atributoId) {
        this.atributoId = atributoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public Accesorio getAccesorio() {
        return accesorio;
    }

    public void setAccesorio(Accesorio accesorio) {
        this.accesorio = accesorio;
    }

    public Almacenamiento getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(Almacenamiento almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public Casse getCasse() {
        return casse;
    }

    public void setCasse(Casse casse) {
        this.casse = casse;
    }

    public Combo getCombo() {
        return combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public Fuente getFuente() {
        return fuente;
    }

    public void setFuente(Fuente fuente) {
        this.fuente = fuente;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public Placa getPlaca() {
        return placa;
    }

    public void setPlaca(Placa placa) {
        this.placa = placa;
    }

    public Procesador getProcesador() {
        return procesador;
    }

    public void setProcesador(Procesador procesador) {
        this.procesador = procesador;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public Refrigeracion getRefrigeracion() {
        return refrigeracion;
    }

    public void setRefrigeracion(Refrigeracion refrigeracion) {
        this.refrigeracion = refrigeracion;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

}
