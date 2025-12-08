package br.edu.imepac.administrativo.controllers;

import br.edu.imepac.administrativo.dtos.perfil.PerfilCreateRequest;
import br.edu.imepac.administrativo.dtos.perfil.PerfilDTO;
import br.edu.imepac.administrativo.dtos.perfil.PerfilUpdateRequest;
import br.edu.imepac.administrativo.services.PerfilService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/perfis")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @PostMapping
    public ResponseEntity<PerfilDTO> criarPerfil(@RequestBody @Valid PerfilCreateRequest perfilCreateRequest) {
        PerfilDTO novoPerfil = perfilService.criarPerfil(perfilCreateRequest);
        return ResponseEntity.created(URI.create("/api/perfis/" + novoPerfil.getId())).body(novoPerfil);
    }

    @GetMapping
    public ResponseEntity<List<PerfilDTO>> listarTodosOsPerfis() {
        List<PerfilDTO> perfis = perfilService.listarTodosOsPerfis();
        return ResponseEntity.ok(perfis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilDTO> buscarPerfilPorId(@PathVariable Long id) {
        PerfilDTO perfil = perfilService.buscarPerfilPorId(id);
        if (perfil != null) {
            return ResponseEntity.ok(perfil);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfilDTO> atualizarPerfil(@PathVariable Long id, @RequestBody @Valid PerfilUpdateRequest perfilUpdateRequest) {
        PerfilDTO perfilAtualizado = perfilService.atualizarPerfil(id, perfilUpdateRequest);
        if (perfilAtualizado != null) {
            return ResponseEntity.ok(perfilAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPerfil(@PathVariable Long id) {
        perfilService.deletarPerfil(id);
        return ResponseEntity.noContent().build();
    }
}
