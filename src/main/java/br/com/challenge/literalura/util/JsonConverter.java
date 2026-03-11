package br.com.challenge.literalura.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

    private static final ObjectMapper conversorJson = new ObjectMapper();

    public static <T> T fromJson(String conteudoJson, Class<T> tipoClasse) {
        try {
            return conversorJson.readValue(conteudoJson, tipoClasse);
        } catch (JsonProcessingException erroConversao) {
            throw new RuntimeException("Falha ao converter JSON", erroConversao);
        }
    }
}