package br.edu.imepac.administrativo.controllers;

import br.edu.imepac.administrativo.dtos.paciente.PacienteCreateRequest;
import br.edu.imepac.administrativo.dtos.paciente.PacienteDTO;
import br.edu.imepac.administrativo.dtos.paciente.PacienteUpdateRequest;
import br.edu.imepac.administrativo.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PacienteDTO create(@Valid @RequestBody PacienteCreateRequest pacienteRequest) {
        return pacienteService.create(pacienteRequest);
    }

    @GetMapping
    public List<PacienteDTO> findAll() {
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public PacienteDTO findById(@PathVariable Long id) {
        return pacienteService.findById(id);
    }

    @PutMapping("/{id}")
    public PacienteDTO update(@PathVariable Long id, @Valid @RequestBody PacienteUpdateRequest pacienteRequest) {
        return pacienteService.update(id, pacienteRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pacienteService.delete(id);
    }
}
