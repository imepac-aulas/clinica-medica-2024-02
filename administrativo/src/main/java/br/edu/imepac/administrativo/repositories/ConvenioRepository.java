package br.edu.imepac.administrativo.repositories;

import br.edu.imepac.common.entidades.Convenio;
import br.edu.imepac.common.utils.StatusConvenioEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Long> {


    boolean existsByNomeIgnoreCase(String nome);


    Optional<Convenio> findByNomeIgnoreCase(String nome);

    List<Convenio> findByStatus(StatusConvenioEnum status);

    boolean existsByCnpj(String cnpj);
}
