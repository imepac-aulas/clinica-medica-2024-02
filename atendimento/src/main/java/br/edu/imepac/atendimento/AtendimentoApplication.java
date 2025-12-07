package br.edu.imepac.atendimento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@EntityScan("br.edu.imepac.common.entidades")
@EnableJpaRepositories("br.edu.imepac.atendimento.repositories")
public class AtendimentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtendimentoApplication.class, args);
    }

}
