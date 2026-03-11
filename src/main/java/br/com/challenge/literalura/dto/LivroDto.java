package br.com.challenge.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDto(

        Long id,

        // Título do livro retornado pela API
        String title,

        // Lista de autores do livro
        List<AutorDto> authors,

        // Idiomas disponíveis do livro
        List<String> languages,

        // Quantidade de downloads
        @JsonProperty("download_count")
        Integer downloads

) {
}