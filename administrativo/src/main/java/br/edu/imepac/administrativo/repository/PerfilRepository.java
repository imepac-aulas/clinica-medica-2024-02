package br.edu.imepac.administrativo.repository;

import br.edu.imepac.common.entidades.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    boolean existsByNome(String nome);
    Perfil findByNome(String nome);
}
