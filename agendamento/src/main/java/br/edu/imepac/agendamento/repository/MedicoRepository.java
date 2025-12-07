package br.edu.imepac.agendamento.repository;

import br.edu.imepac.common.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    // Aqui você pode adicionar métodos customizados, por exemplo:
    // Optional<Medico> findByCrm(String crm);
}
