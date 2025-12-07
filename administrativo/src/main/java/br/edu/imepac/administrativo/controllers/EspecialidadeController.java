package br.edu.imepac.administrativo.controllers;

import br.edu.imepac.administrativo.dtos.especialidade.EspecialidadeCreateRequest;
import br.edu.imepac.administrativo.dtos.especialidade.EspecialidadeDTO;
import br.edu.imepac.administrativo.dtos.especialidade.EspecialidadeUpdateRequest;
import br.edu.imepac.administrativo.services.EspecialidadeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
@Slf4j
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;

    public EspecialidadeController(EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EspecialidadeDTO createEspecialidade(@Valid @RequestBody EspecialidadeCreateRequest especialidadeCreateRequest) {
       log.trace("createEspecialidade({})", especialidadeCreateRequest);
        log.info("Entrou no método criar especialidade {}", especialidadeCreateRequest);
        return especialidadeService.createEspecialidade(especialidadeCreateRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<EspecialidadeDTO> findAllEspecialidades() {
        return especialidadeService.findAllEspecialidades();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public EspecialidadeDTO findEspecialidadeById(@PathVariable Long id) {
        return especialidadeService.findEspecialidadeById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public EspecialidadeDTO updateEspecialidade(@PathVariable Long id, @Valid @RequestBody EspecialidadeUpdateRequest especialidadeUpdateRequest) {
        return especialidadeService.updateEspecialidade(id, especialidadeUpdateRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEspecialidade(@PathVariable Long id) {
        log.debug("Deletando especialidade {}", id);
        especialidadeService.deleteEspecialidade(id);

    }
}
