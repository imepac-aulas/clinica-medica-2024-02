package br.edu.imepac.administrativo.controllers;

import br.edu.imepac.administrativo.dtos.especialidade.EspecialidadeCreateRequest;
import br.edu.imepac.administrativo.dtos.especialidade.EspecialidadeDTO;
import br.edu.imepac.administrativo.dtos.especialidade.EspecialidadeUpdateRequest;
import br.edu.imepac.administrativo.dtos.medico.MedicoDTO;
import br.edu.imepac.administrativo.services.EspecialidadeService;
import br.edu.imepac.administrativo.services.MedicoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Especialidades", description = "Operações sobre Especialidades")
@RestController
@RequestMapping("/api/especialidades")
@Slf4j
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;
    private final MedicoService medicoService;

    public EspecialidadeController(EspecialidadeService especialidadeService, MedicoService medicoService) {
        this.especialidadeService = especialidadeService;
        this.medicoService = medicoService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation
    public EspecialidadeDTO create(@Valid @RequestBody EspecialidadeCreateRequest especialidadeRequest) {
        log.info("Criando nova especialidade: {}", especialidadeRequest);
        return especialidadeService.createEspecialidade(especialidadeRequest);
    }

    @GetMapping
    public List<EspecialidadeDTO> findAll() {
        return especialidadeService.findAllEspecialidades();
    }

    @GetMapping("/{id}")
    public EspecialidadeDTO findById(@PathVariable Long id) {
        return especialidadeService.findEspecialidadeById(id);
    }

    @PutMapping("/{id}")
    public EspecialidadeDTO update(@PathVariable Long id, @Valid @RequestBody EspecialidadeUpdateRequest especialidadeRequest) {
        return especialidadeService.updateEspecialidade(id, especialidadeRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.debug("Deletando especialidade com id: {}", id);
        especialidadeService.deleteEspecialidade(id);
    }

    @GetMapping("/{id}/medicos")
    public List<MedicoDTO> findMedicosByEspecialidade(@PathVariable Long id) {
        return medicoService.findMedicosByEspecialidadeId(id);
    }
}
