package br.edu.imepac.atendimento.repositories;

import br.edu.imepac.common.entidades.Consulta;
import br.edu.imepac.common.entidades.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
