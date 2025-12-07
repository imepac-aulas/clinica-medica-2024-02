package br.edu.imepac.atendimento.controllers;

import br.edu.imepac.atendimento.dtos.prontuario.ProntuarioCreateRequest;
import br.edu.imepac.atendimento.dtos.prontuario.ProntuarioDTO;
import br.edu.imepac.atendimento.dtos.prontuario.ProntuarioUpdateRequest;
import br.edu.imepac.atendimento.services.ProntuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prontuarios")
public class ProntuarioController {

    private final ProntuarioService service;

    public ProntuarioController(ProntuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ProntuarioDTO criar(@RequestBody ProntuarioCreateRequest request) {
        return service.criar(request);
    }

    @GetMapping
    public List<ProntuarioDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ProntuarioDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ProntuarioDTO atualizar(
            @PathVariable Long id,
            @RequestBody ProntuarioUpdateRequest request
    ) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
