package br.com.challenge.literalura.principal;

import br.com.challenge.literalura.model.Livros;
import br.com.challenge.literalura.service.AutorService;
import br.com.challenge.literalura.service.EstatisticaService;
import br.com.challenge.literalura.service.LivroService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private final Scanner leitor = new Scanner(System.in);
    private final LivroService servicoLivro;
    private final AutorService servicoAutor;
    private final EstatisticaService servicoEstatistica;

    public Principal(LivroService servicoLivro,
                     AutorService servicoAutor,
                     EstatisticaService servicoEstatistica) {

        this.servicoLivro = servicoLivro;
        this.servicoAutor = servicoAutor;
        this.servicoEstatistica = servicoEstatistica;
    }

    public void exibeMenu() {
        int opcaoSelecionada = -1;

        while (opcaoSelecionada != 0) {
            System.out.println("""
                -------------------------
                LITERALURA - MENU
                -------------------------
                1 - Listar livro por título
                2 - Listar livro por autor
                3 - Listar autores
                4 - Informe um trecho do título do livro:
                5 - Listar livros salvos
                6 - Estatísticas
                0 - Sair
                """);

            opcaoSelecionada = leitor.nextInt();
            leitor.nextLine();

            switch (opcaoSelecionada) {
                case 1 -> pesquisarLivroPorTitulo();
                case 2 -> pesquisarLivroPorAutor();
                case 3 -> servicoAutor.buscaAutores();
                case 4 -> pesquisarLivroPorTituloNaApi();
                case 5 -> exibirLivrosSalvos();
                case 6 -> exibirMenuEstatisticas();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private void pesquisarLivroPorTitulo() {
        System.out.print("Informe o título do livro: ");
        String tituloInformado = leitor.nextLine();

        List<Livros> listaLivros = servicoLivro.buscarPorTitulo(tituloInformado);

        if (listaLivros.isEmpty()) {
            System.out.println("Nenhum livro encontrado com o título: " + tituloInformado);
        } else {
            System.out.println("----- LIVROS ENCONTRADOS -----");
            for (Livros livroAtual : listaLivros) {
                System.out.println(
                        "ID: " + livroAtual.getId() +
                                " | Título: " + livroAtual.getTitulo() +
                                " | Autor: " + livroAtual.getAutor() +
                                " | Idioma: " + livroAtual.getIdioma() +
                                " | Downloads: " + livroAtual.getNumeroDownloads()
                );
            }
        }
    }

    private void pesquisarLivroPorAutor() {
        System.out.print("Informe o nome do autor: ");
        String nomeAutor = leitor.nextLine();

        List<Livros> listaLivros = servicoLivro.buscarPorAutor(nomeAutor);

        if (listaLivros.isEmpty()) {
            System.out.println("Nenhum livro encontrado do autor: " + nomeAutor);
        } else {
            System.out.println("----- LIVROS ENCONTRADOS -----");
            for (Livros livroAtual : listaLivros) {
                System.out.println(
                        "ID: " + livroAtual.getId() +
                                " | Título: " + livroAtual.getTitulo() +
                                " | Autor: " + livroAtual.getAutor() +
                                " | Idioma: " + livroAtual.getIdioma() +
                                " | Downloads: " + livroAtual.getNumeroDownloads()
                );
            }
        }
    }

    private void pesquisarLivroPorTituloNaApi() {
        System.out.print("Informe o título do livro: ");
        String tituloInformado = leitor.nextLine();

        List<Livros> listaLivros = servicoLivro.buscarESalvarLivrosPorTitulo(tituloInformado);

        if (listaLivros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para: " + tituloInformado);
        } else {
            System.out.println("----- LIVROS ENCONTRADOS NO GUTENDEX -----");
            for (Livros livroAtual : listaLivros) {
                System.out.println(
                        "ID: " + livroAtual.getId() +
                                " | Título: " + livroAtual.getTitulo() +
                                " | Autor: " + livroAtual.getAutor() +
                                " | Idioma: " + livroAtual.getIdioma() +
                                " | Downloads: " + livroAtual.getNumeroDownloads()
                );
            }
        }
    }

    private void exibirLivrosSalvos() {
        List<Livros> listaLivros = servicoLivro.listarTodos();

        if (listaLivros.isEmpty()) {
            System.out.println("Nenhum livro salvo.");
        } else {
            System.out.println("----- LIVROS SALVOS -----");
            for (Livros livroAtual : listaLivros) {
                System.out.println(
                        "ID: " + livroAtual.getId() +
                                " | Título: " + livroAtual.getTitulo() +
                                " | Autor: " + livroAtual.getAutor() +
                                " | Idioma: " + livroAtual.getIdioma() +
                                " | Downloads: " + livroAtual.getNumeroDownloads()
                );
            }
        }
    }

    private void exibirMenuEstatisticas() {
        int opcaoEstatistica = -1;

        while (opcaoEstatistica != 0) {
            System.out.println("""
            -------- ESTATÍSTICAS --------
            1 - Autores vivos em determinado ano
            2 - Quantidade de livros por idioma
            3 - Top 05 livros mais baixados
            0 - Voltar
            """);

            opcaoEstatistica = leitor.nextInt();
            leitor.nextLine();

            switch (opcaoEstatistica) {

                case 1 -> {
                    System.out.print("Digite o ano: ");
                    int anoInformado = leitor.nextInt();
                    leitor.nextLine();

                    var listaAutores = servicoEstatistica.autoresVivosNoAno(anoInformado);

                    if (listaAutores.isEmpty()) {
                        System.out.println("Nenhum autor encontrado.");
                    } else {
                        listaAutores.forEach(autorAtual ->
                                System.out.println(
                                        autorAtual.getNome() + " (" +
                                                autorAtual.getNasc() + " - " +
                                                (autorAtual.getMorte() == null ? "Vivo" : autorAtual.getMorte()) +
                                                ")"
                                )
                        );
                    }
                }

                case 2 -> {
                    var dadosIdiomas = servicoEstatistica.quantidadeLivrosPorIdioma();

                    dadosIdiomas.forEach(registro -> {
                        String idiomaLivro = (String) registro[0];
                        Long totalLivros = (Long) registro[1];

                        System.out.println("Idioma: " + idiomaLivro +
                                " | Quantidade: " + totalLivros);
                    });
                }

                case 3 -> {
                    var listaTopLivros = servicoEstatistica.top5LivrosMaisBaixados();

                    if (listaTopLivros.isEmpty()) {
                        System.out.println("Nenhum livro encontrado.");
                    } else {
                        System.out.println("----- TOP 5 LIVROS MAIS BAIXADOS -----");

                        int colocacao = 1;
                        for (var livroAtual : listaTopLivros) {
                            System.out.println(
                                    colocacao++ + "º - " +
                                            livroAtual.getTitulo() +
                                            " | Downloads: " +
                                            livroAtual.getNumeroDownloads()
                            );
                        }
                    }
                }

                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida");
            }
        }
    }
}