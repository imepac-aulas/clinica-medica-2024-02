package br.edu.imepac.administrativo.controllers;

import br.edu.imepac.administrativo.dtos.convenio.ConvenioCreateRequest;
import br.edu.imepac.administrativo.dtos.convenio.ConvenioDTO;
import br.edu.imepac.administrativo.dtos.convenio.ConvenioUpdateRequest;
import br.edu.imepac.administrativo.services.ConvenioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/convenios")
public class ConvenioController {

    private final ConvenioService convenioService;

    public ConvenioController(ConvenioService convenioService) {
        this.convenioService = convenioService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ConvenioDTO create(@Valid @RequestBody ConvenioCreateRequest convenioRequest) {
        return convenioService.create(convenioRequest);
    }

    @GetMapping
    public List<ConvenioDTO> findAll() {
        return convenioService.findAll();
    }

    @GetMapping("/{id}")
    public ConvenioDTO findById(@PathVariable Long id) {
        return convenioService.findById(id);
    }

    @PutMapping("/{id}")
    public ConvenioDTO update(@PathVariable Long id, @Valid @RequestBody ConvenioUpdateRequest convenioRequest) {
        return convenioService.update(id, convenioRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        // A lógica de negócio no service trata a deleção como uma inativação
        convenioService.delete(id);
    }
}
