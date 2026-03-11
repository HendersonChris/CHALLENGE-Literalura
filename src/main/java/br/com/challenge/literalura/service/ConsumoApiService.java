package br.com.challenge.literalura.service;

import br.com.challenge.literalura.dto.AutorDto;
import br.com.challenge.literalura.dto.GutendexResponse;
import br.com.challenge.literalura.dto.LivroDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ConsumoApiService {

    private static final String URL_BASE_API = "https://gutendex.com/books/";

    private final RestTemplate clienteHttp = new RestTemplate();


    public String buscarLivroPorId(Long idLivro) {
        return clienteHttp.getForObject(URL_BASE_API + idLivro, String.class);
    }


    public List<LivroDto> buscarLivro(String tituloBusca) {

        String enderecoConsulta = UriComponentsBuilder
                .fromHttpUrl(URL_BASE_API)
                .queryParam("search", tituloBusca)
                .toUriString();

        GutendexResponse respostaApi =
                clienteHttp.getForObject(enderecoConsulta, GutendexResponse.class);

        return respostaApi != null ? respostaApi.getResults() : List.of();
    }


    public List<AutorDto> buscarAutoresNaApi() {

        List<LivroDto> listaLivros = buscarLivro("");

        return listaLivros.stream()
                .flatMap(livro -> livro.authors().stream())
                .distinct()
                .toList();
    }
}