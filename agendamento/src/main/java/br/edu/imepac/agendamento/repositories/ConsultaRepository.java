package br.edu.imepac.agendamento.repositories;

import br.edu.imepac.common.entidades.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
