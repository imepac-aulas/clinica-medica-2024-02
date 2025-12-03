package br.edu.imepac.common.apis;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/common/pacientes")
public interface PacienteApi {

    @GetMapping("/{id}")
    PacienteResponse buscarPorId(@PathVariable Long id);
}