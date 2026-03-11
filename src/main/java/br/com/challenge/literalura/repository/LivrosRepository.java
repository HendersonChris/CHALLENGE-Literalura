package br.com.challenge.literalura.repository;

import br.com.challenge.literalura.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivrosRepository extends JpaRepository<Livros, Long> {

    boolean existsByTitulo(String tituloLivro);

    // Buscar livros pelo título (contendo parte do texto, ignorando maiúsculas/minúsculas)
    List<Livros> findByTituloContainingIgnoreCase(String tituloLivro);

    // Buscar livros pelo nome do autor
    List<Livros> findByAutorContainingIgnoreCase(String nomeAutor);

    @Query("""
        SELECT l.idioma, COUNT(l)
        FROM Livros l
        GROUP BY l.idioma
    """)
    List<Object[]> quantidadeLivrosPorIdioma();

    List<Livros> findTop5ByOrderByNumeroDownloadsDesc();
}