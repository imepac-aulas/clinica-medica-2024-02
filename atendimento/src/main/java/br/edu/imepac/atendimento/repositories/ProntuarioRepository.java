package br.edu.imepac.atendimento.repositories;

import br.edu.imepac.common.entidades.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
}
