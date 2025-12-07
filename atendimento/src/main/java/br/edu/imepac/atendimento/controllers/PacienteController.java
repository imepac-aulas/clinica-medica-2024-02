package br.edu.imepac.atendimento.controllers;

import br.edu.imepac.atendimento.dtos.paciente.PacienteCreateRequest;
import br.edu.imepac.atendimento.dtos.paciente.PacienteDTO;
import br.edu.imepac.atendimento.dtos.paciente.PacienteUpdateRequest;
import br.edu.imepac.atendimento.services.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDTO> criar(@Valid @RequestBody PacienteCreateRequest request) {
        PacienteDTO criado = pacienteService.criar(request);
        URI location = URI.create(String.format("/atendimento/pacientes/%d", criado.getId()));
        return ResponseEntity.created(location).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listar() {
        return ResponseEntity.ok(pacienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PacienteUpdateRequest request
    ) {
        return ResponseEntity.ok(pacienteService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        pacienteService.remover(id);
        return ResponseEntity.noContent().build();
    }
}