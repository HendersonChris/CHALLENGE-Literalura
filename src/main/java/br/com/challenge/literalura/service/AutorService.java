package br.com.challenge.literalura.service;

import br.com.challenge.literalura.model.Autor;
import br.com.challenge.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    private final AutorRepository repositorioAutor;
    private final ConsumoApiService servicoApi;
    private final String ambienteAtivo;

    public AutorService(AutorRepository repositorioAutor,
                        ConsumoApiService servicoApi,
                        @Value("${spring.profiles.active}") String ambienteAtivo) {
        this.repositorioAutor = repositorioAutor;
        this.servicoApi = servicoApi;
        this.ambienteAtivo = ambienteAtivo;
    }

    public void buscaAutores() {
        List<Autor> listaAutores;

        if ("dev".equalsIgnoreCase(ambienteAtivo)) {
            listaAutores = servicoApi.buscarAutoresNaApi()
                    .stream()
                    .map(autorDto -> {
                        Autor autorEntidade = new Autor();
                        autorEntidade.setNome(autorDto.name());

                        if (autorDto.birthYear() != null) {
                            autorEntidade.setNasc(LocalDate.of(autorDto.birthYear(), 1, 1));
                        } else {
                            autorEntidade.setNasc(null);
                        }

                        if (autorDto.deathYear() != null) {
                            autorEntidade.setMorte(LocalDate.of(autorDto.deathYear(), 1, 1));
                        } else {
                            autorEntidade.setMorte(null);
                        }

                        return autorEntidade;
                    })
                    .collect(Collectors.toList());
        } else {
            listaAutores = repositorioAutor.findAll();
        }

        if (listaAutores.isEmpty()) {
            System.out.println("Nenhum autor encontrado.");
            return;
        }

        listaAutores.forEach(autorAtual -> System.out.println(
                "Nome: " + autorAtual.getNome() +
                        " | Nascimento: " + (autorAtual.getNasc() != null ? autorAtual.getNasc() : "Desconhecido") +
                        " | Morte: " + (autorAtual.getMorte() != null ? autorAtual.getMorte() : "Vivo")
        ));
    }
}