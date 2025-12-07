package br.edu.imepac.administrativo.controllers;

import br.edu.imepac.administrativo.dtos.convenio.ConvenioCreateRequest;
import br.edu.imepac.administrativo.dtos.convenio.ConvenioDTO;
import br.edu.imepac.administrativo.dtos.convenio.ConvenioUpdateRequest;
import br.edu.imepac.administrativo.service.ConvenioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrativo/convenios")
@RequiredArgsConstructor
public class ConvenioController {

    private final ConvenioService convenioService;


    @PostMapping
    public ResponseEntity<ConvenioDTO> criar(
            @RequestBody @Valid ConvenioCreateRequest request
    ) {
        ConvenioDTO dto = convenioService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<ConvenioDTO>> listarTodos() {
        return ResponseEntity.ok(convenioService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ConvenioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(convenioService.buscarPorId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ConvenioDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ConvenioUpdateRequest request
    ) {
        return ResponseEntity.ok(convenioService.atualizar(id, request));
    }


    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        convenioService.inativar(id);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        convenioService.ativar(id);
        return ResponseEntity.noContent().build();
    }
}
