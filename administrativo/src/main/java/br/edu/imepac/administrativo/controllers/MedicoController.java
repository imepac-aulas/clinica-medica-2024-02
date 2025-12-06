package br.edu.imepac.administrativo.controllers;

import br.edu.imepac.administrativo.dtos.medico.MedicoCreateRequest;
import br.edu.imepac.administrativo.dtos.medico.MedicoDTO;
import br.edu.imepac.administrativo.dtos.medico.MedicoUpdateRequest;
import br.edu.imepac.administrativo.services.MedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> create(@RequestBody MedicoCreateRequest request) {
        return ResponseEntity.ok(medicoService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> findAll() {
        return ResponseEntity.ok(medicoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> update(@PathVariable Long id, @RequestBody MedicoUpdateRequest request) {
        return ResponseEntity.ok(medicoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
