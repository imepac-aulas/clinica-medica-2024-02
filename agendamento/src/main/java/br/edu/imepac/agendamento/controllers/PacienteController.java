package br.edu.imepac.agendamento.controllers;

import br.edu.imepac.agendamento.dtos.paciente.PacienteCreateRequest;
import br.edu.imepac.agendamento.dtos.paciente.PacienteDTO;
import br.edu.imepac.agendamento.dtos.paciente.PacienteUpdateRequest;
import br.edu.imepac.agendamento.services.paciente.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agendamento/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDTO> criar(@Valid @RequestBody PacienteCreateRequest request) {
        PacienteDTO criado = pacienteService.criar(request);
        // Location: /agendamento/pacientes/{id}
        URI location = URI.create(String.format("/agendamento/pacientes/%d", criado.getId()));
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