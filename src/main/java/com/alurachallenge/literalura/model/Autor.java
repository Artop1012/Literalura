package com.alurachallenge.literalura.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private String fechaNacimiento;
    private String fechaDeFallecimiento;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Libros> libros = new HashSet<>();

    public Autor() {
    }

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = datosAutor.fechaNacimiento();
        this.fechaDeFallecimiento = datosAutor.fechaDefuncion();
    }

    @Override
    public String toString() {
        return
                "AUTOR: " + nombre + '\n' +
                        "Fecha de Nacimiento: " + fechaNacimiento + '\n' +
                        "Fecha de Defuncion: " + fechaDeFallecimiento + '\n' +
                        "Libros: " + (libros != null ?libros.stream()
                        .map(Libros::getTitulo)
                        .collect(Collectors.joining(", ")) : "N/A") +'\n' +
                        '\n';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimineto() {
        return fechaNacimiento;
    }

    public void setFechaNacimineto(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(String fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public Set<Libros> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libros> libros) {
        this.libros = libros;
        for (Libros libro : libros) {
            libro.setAutor(this);
        }
    }
}
