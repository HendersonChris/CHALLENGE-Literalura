# 📚 Literalura

Literalura é uma aplicação Java desenvolvida com **Spring Boot** que permite buscar, armazenar e analisar informações de livros utilizando a API pública **Gutendex**, baseada no acervo do **Project Gutenberg**.

O sistema permite consultar livros por título, listar autores, armazenar livros no banco de dados e gerar estatísticas sobre os dados armazenados.

---

## 🚀 Funcionalidades

O sistema possui um menu interativo no terminal com as seguintes opções:

1. Buscar livro por título
2. Buscar livros por autor
3. Listar autores
4. Buscar livros na API por trecho do título
5. Listar livros salvos no banco de dados
6. Exibir estatísticas
7. Sair do sistema

### Estatísticas disponíveis

- Autores vivos em determinado ano
- Quantidade de livros por idioma
- Top 5 livros mais baixados

---

## 🌐 API utilizada

Este projeto utiliza a API pública:

**Gutendex API**

https://gutendex.com/

A API fornece acesso ao catálogo de livros do **Project Gutenberg**.

Não é necessário utilizar chave de API para realizar as consultas.

---

## 🛠 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Jackson (JSON)
- RestTemplate
- Maven

---

## 📂 Estrutura do projeto
