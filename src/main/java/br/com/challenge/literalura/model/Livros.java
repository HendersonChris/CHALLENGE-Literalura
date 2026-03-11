package br.com.challenge.literalura.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "livro")
public class Livros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String idioma;
    private Double numeroDownloads;

    // Informações do autor
    private LocalDate nasc;
    private LocalDate morte;

    public Livros() {
    }

    public Livros(String titulo, String autor, String idioma, Double numeroDownloads) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDownloads = numeroDownloads;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String tituloLivro) {
        this.titulo = tituloLivro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String nomeAutor) {
        this.autor = nomeAutor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idiomaLivro) {
        this.idioma = idiomaLivro;
    }

    public Double getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Double totalDownloads) {
        this.numeroDownloads = totalDownloads;
    }

    public LocalDate getNasc() {
        return nasc;
    }

    public void setNasc(LocalDate dataNascimento) {
        this.nasc = dataNascimento;
    }

    public LocalDate getMorte() {
        return morte;
    }

    public void setMorte(LocalDate dataMorte) {
        this.morte = dataMorte;
    }
}