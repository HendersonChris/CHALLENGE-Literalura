package br.com.challenge.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDto(

        // Nome do autor retornado pela API
        String name,

        // Ano de nascimento
        @JsonProperty("birth_year")
        Integer birthYear,

        // Ano de falecimento
        @JsonProperty("death_year")
        Integer deathYear

) {
}