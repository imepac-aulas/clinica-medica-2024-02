package br.edu.imepac.atendimento.services;

import br.edu.imepac.atendimento.dtos.consulta.ConsultaDTO;
import br.edu.imepac.atendimento.dtos.prontuario.ProntuarioCreateRequest;
import br.edu.imepac.atendimento.dtos.prontuario.ProntuarioDTO;
import br.edu.imepac.atendimento.dtos.prontuario.ProntuarioUpdateRequest;
import br.edu.imepac.atendimento.repositories.ProntuarioRepository;
import br.edu.imepac.common.entidades.Consulta;
import br.edu.imepac.common.entidades.Prontuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProntuarioService {

    private final ProntuarioRepository repository;
    private final ModelMapper modelMapper;

    private final ConsultaService consultaService;

    public ProntuarioService(ProntuarioRepository repository, ModelMapper modelMapper, ConsultaService consultaService) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.consultaService = consultaService;
    }

    public ProntuarioDTO criar(ProntuarioCreateRequest request) {
        ConsultaDTO consultaDTO = this.consultaService.getConsultaPorId(request.getConsultaId());
        Consulta consulta = this.modelMapper.map(consultaDTO, Consulta.class);

        Prontuario entidade = modelMapper.map(request, Prontuario.class);
        entidade.setConsulta(consulta);

        Prontuario salvo = repository.save(entidade);

        return modelMapper.map(salvo, ProntuarioDTO.class);
    }

    public List<ProntuarioDTO> listar() {
        return repository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, ProntuarioDTO.class))
                .toList();
    }

    public ProntuarioDTO buscarPorId(Long id) {
        Prontuario p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));
        return modelMapper.map(p, ProntuarioDTO.class);
    }

    public ProntuarioDTO atualizar(Long id, ProntuarioUpdateRequest request) {
        Prontuario existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));
        modelMapper.map(request, existente);
        Prontuario atualizado = repository.save(existente);
        return modelMapper.map(atualizado, ProntuarioDTO.class);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Prontuário não encontrado");
        }
        repository.deleteById(id);
    }
}
