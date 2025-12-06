package br.edu.imepac.agendamento.controllers;

import br.edu.imepac.agendamento.dtos.consulta.ConsultaCreateRequest;
import br.edu.imepac.agendamento.dtos.consulta.ConsultaDTO;
import br.edu.imepac.agendamento.dtos.consulta.ConsultaUpdateRequest;
import br.edu.imepac.agendamento.services.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
@RequiredArgsConstructor  // Injeta automaticamente o service
public class ConsultaController {

    private final ConsultaService consultaService;

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<ConsultaDTO> criarConsulta(@Valid @RequestBody ConsultaCreateRequest request) {
        ConsultaDTO dto = consultaService.criarConsulta(request);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // ================= READ =================
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> buscarPorId(@PathVariable Long id) {
        ConsultaDTO dto = consultaService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listarTodas() {
        List<ConsultaDTO> lista = consultaService.listarTodas();
        return ResponseEntity.ok(lista);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTO> atualizarConsulta(
            @PathVariable Long id,
            @RequestBody ConsultaUpdateRequest request) {
        ConsultaDTO dto = consultaService.atualizarConsulta(id, request);
        return ResponseEntity.ok(dto);
    }

    // ================= CANCEL =================
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable Long id) {
        consultaService.cancelarConsulta(id);
        return ResponseEntity.noContent().build();
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConsulta(@PathVariable Long id) {
        consultaService.deletarConsulta(id);
        return ResponseEntity.noContent().build();
    }
}