package br.edu.imepac.agendamento.repository;

import br.edu.imepac.common.entidades.Convenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Long> {
}
