package br.com.challenge.literalura;

import br.com.challenge.literalura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("br.com.challenge.literalura.model")
public class LiteraluraApplication implements CommandLineRunner {

    private final Principal controladorPrincipal;

    public LiteraluraApplication(Principal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
    }

    public static void main(String[] argumentos) {
        SpringApplication.run(LiteraluraApplication.class, argumentos);
    }

    @Override
    public void run(String... parametros) {
        controladorPrincipal.exibeMenu();
    }
}
