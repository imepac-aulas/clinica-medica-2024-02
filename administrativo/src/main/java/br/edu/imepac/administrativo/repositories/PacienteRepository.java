package br.edu.imepac.administrativo.repositories;

import br.edu.imepac.common.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    boolean existsByCpf(String cpf);

    Optional<Paciente> findByCpf(String cpf);

    // método necessário porque sua entidade marca numeroCartaoSUS como unique
    boolean existsByNumeroCartaoSUS(String numeroCartaoSUS);

    Optional<Paciente> findByNumeroCartaoSUS(String numeroCartaoSUS);
}
