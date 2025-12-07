package br.edu.imepac.administrativo.repositories;

import br.edu.imepac.common.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    public List<Medico> findByEspecialidadeId(Long id);
}
