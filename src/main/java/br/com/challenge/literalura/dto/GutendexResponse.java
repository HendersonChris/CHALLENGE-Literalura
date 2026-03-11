package br.com.challenge.literalura.dto;

import java.util.List;

public class GutendexResponse {

    // Lista de livros retornados pela API
    private List<LivroDto> results;

    public List<LivroDto> getResults() {
        return results;
    }

    public void setResults(List<LivroDto> listaResultados) {
        this.results = listaResultados;
    }
}