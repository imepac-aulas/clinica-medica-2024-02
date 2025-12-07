package br.edu.imepac.administrativo.controllers;

import br.edu.imepac.administrativo.dtos.medico.MedicoCreateRequest;
import br.edu.imepac.administrativo.dtos.medico.MedicoDTO;
import br.edu.imepac.administrativo.dtos.medico.MedicoUpdateRequest;
import br.edu.imepac.administrativo.services.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MedicoDTO create(@Valid @RequestBody MedicoCreateRequest medicoRequest) {
        return medicoService.create(medicoRequest);
    }

    @GetMapping
    public List<MedicoDTO> findAll() {
        return medicoService.findAll();
    }

    @GetMapping("/{id}")
    public MedicoDTO findById(@PathVariable Long id) {
        return medicoService.findById(id);
    }

    @PutMapping("/{id}")
    public MedicoDTO update(@PathVariable Long id, @Valid @RequestBody MedicoUpdateRequest medicoRequest) {
        return medicoService.update(id, medicoRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        medicoService.delete(id);
    }
}
