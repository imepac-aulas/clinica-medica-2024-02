package br.edu.imepac.administrativo.repositories;

import br.edu.imepac.common.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
