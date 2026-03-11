package br.com.challenge.literalura.repository;

import br.com.challenge.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Buscar autores pelo nome (ignorando maiúsculas e minúsculas)
    List<Autor> findByNomeContainingIgnoreCase(String nomeAutor);

    @Query("""
        SELECT a FROM Autor a
        WHERE YEAR(a.nasc) <= :anoConsulta
        AND (a.morte IS NULL OR YEAR(a.morte) >= :anoConsulta)
    """)
    List<Autor> autoresVivosNoAno(@Param("anoConsulta") Integer anoConsulta);
}