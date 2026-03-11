package br.com.challenge.literalura.service;

import br.com.challenge.literalura.model.Autor;
import br.com.challenge.literalura.model.Livros;
import br.com.challenge.literalura.repository.AutorRepository;
import br.com.challenge.literalura.repository.LivrosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstatisticaService {

    private final LivrosRepository repositorioLivros;
    private final AutorRepository repositorioAutor;

    public EstatisticaService(LivrosRepository repositorioLivros,
                              AutorRepository repositorioAutor){
        this.repositorioLivros = repositorioLivros;
        this.repositorioAutor = repositorioAutor;
    }

    public List<Autor> autoresVivosNoAno(Integer anoConsulta){
        return repositorioAutor.autoresVivosNoAno(anoConsulta);
    }

    public List<Object[]> quantidadeLivrosPorIdioma(){
        return repositorioLivros.quantidadeLivrosPorIdioma();
    }

    public List<Livros> top5LivrosMaisBaixados(){
        return repositorioLivros.findTop5ByOrderByNumeroDownloadsDesc();
    }
}