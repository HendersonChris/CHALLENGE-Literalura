package br.com.challenge.literalura.service;

import br.com.challenge.literalura.dto.LivroDto;
import br.com.challenge.literalura.model.Livros;
import br.com.challenge.literalura.repository.LivrosRepository;
import br.com.challenge.literalura.util.JsonConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Service
public class LivroService {

    private final ConsumoApiService servicoApi;
    private final LivrosRepository repositorioLivros;

    public LivroService(ConsumoApiService servicoApi,
                        LivrosRepository repositorioLivros) {
        this.servicoApi = servicoApi;
        this.repositorioLivros = repositorioLivros;
    }

    @Transactional
    public List<Livros> buscarESalvarLivrosPorTitulo(String tituloBusca) {

        List<LivroDto> listaDto = servicoApi.buscarLivro(tituloBusca);
        if (listaDto.isEmpty()) return List.of();

        String[] palavrasChave = tituloBusca.toLowerCase().split("\\s+");

        List<LivroDto> livrosFiltrados = listaDto.stream()
                .filter(dto -> {
                    String tituloDto = dto.title().toLowerCase();
                    for (String palavra : palavrasChave) {
                        if (!tituloDto.contains(palavra)) return false;
                    }
                    return true;
                })
                .toList();

        List<Livros> listaLivrosSalvos = new ArrayList<>();

        for (LivroDto dtoAtual : livrosFiltrados) {

            if (!repositorioLivros.existsByTitulo(dtoAtual.title())) {

                Livros livroConvertido = converterDtoParaEntidade(dtoAtual);

                repositorioLivros.save(livroConvertido);

                listaLivrosSalvos.add(livroConvertido);
            }
        }

        return listaLivrosSalvos;
    }

    public List<Livros> listarTodos() {
        return repositorioLivros.findAll();
    }

    public List<Livros> buscarPorTitulo(String tituloBusca) {
        return repositorioLivros.findByTituloContainingIgnoreCase(tituloBusca);
    }

    public List<Livros> buscarPorAutor(String nomeAutor) {
        return repositorioLivros.findByAutorContainingIgnoreCase(nomeAutor);
    }

    private Livros converterDtoParaEntidade(LivroDto dtoLivro) {

        Livros entidadeLivro = new Livros();

        entidadeLivro.setTitulo(dtoLivro.title());

        entidadeLivro.setIdioma(dtoLivro.languages() != null && !dtoLivro.languages().isEmpty()
                ? dtoLivro.languages().get(0)
                : "Desconhecido");

        if (dtoLivro.authors() != null && !dtoLivro.authors().isEmpty()) {

            var autorDto = dtoLivro.authors().get(0);

            entidadeLivro.setAutor(autorDto.name());

            if (autorDto.birthYear() != null) {
                entidadeLivro.setNasc(LocalDate.of(autorDto.birthYear(), 1, 1));
            }

            if (autorDto.deathYear() != null) {
                entidadeLivro.setMorte(LocalDate.of(autorDto.deathYear(), 1, 1));
            }

        } else {

            entidadeLivro.setAutor("Desconhecido");
            entidadeLivro.setNasc(null);
            entidadeLivro.setMorte(null);
        }

        entidadeLivro.setNumeroDownloads(dtoLivro.downloads() != null
                ? dtoLivro.downloads().doubleValue()
                : 0.0);

        return entidadeLivro;
    }
}