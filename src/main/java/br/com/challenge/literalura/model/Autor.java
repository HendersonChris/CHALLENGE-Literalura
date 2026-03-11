package br.com.challenge.literalura.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // Informações de vida do autor
    private LocalDate nasc;
    private LocalDate morte;

    public Autor() {
        // Construtor vazio exigido pelo JPA
    }

    public Autor(String nome, LocalDate dataNascimento, LocalDate dataFalecimento) {
        this.nome = nome;
        this.nasc = dataNascimento;
        this.morte = dataFalecimento;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nomeAutor) {
        this.nome = nomeAutor;
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

    public void setMorte(LocalDate dataFalecimento) {
        this.morte = dataFalecimento;
    }
}