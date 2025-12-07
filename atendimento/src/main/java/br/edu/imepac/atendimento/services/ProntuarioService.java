package br.edu.imepac.atendimento.services;

import br.edu.imepac.atendimento.dtos.prontuario.ProntuarioCreateRequest;
import br.edu.imepac.atendimento.dtos.prontuario.ProntuarioDTO;
import br.edu.imepac.atendimento.dtos.prontuario.ProntuarioUpdateRequest;
import br.edu.imepac.atendimento.repositories.ConsultaRepository;
import br.edu.imepac.atendimento.repositories.ProntuarioRepository;
import br.edu.imepac.common.entidades.Consulta;
import br.edu.imepac.common.entidades.Prontuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final ConsultaRepository consultaRepository;
    private final ModelMapper modelMapper;

    public ProntuarioService(ProntuarioRepository prontuarioRepository, ConsultaRepository consultaRepository, ModelMapper modelMapper) {
        this.prontuarioRepository = prontuarioRepository;
        this.consultaRepository = consultaRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public ProntuarioDTO criar(ProntuarioCreateRequest request) {
        // 1. Fetch the managed Consulta entity. This is the owning side.
        Consulta consulta = consultaRepository.findById(request.getConsultaId())
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com o ID: " + request.getConsultaId()));

        if (consulta.getProntuario() != null) {
            throw new IllegalStateException("Esta consulta já possui um prontuário associado.");
        }

        // 2. Create the Prontuario from the DTO.
        Prontuario novoProntuario = modelMapper.map(request, Prontuario.class);

        // 3. *** THIS IS THE FIX ***
        // Manually synchronize both sides of the bidirectional relationship.
        // This replaces the transient Consulta inside novoProntuario with the managed one.
        novoProntuario.setConsulta(consulta);
        consulta.setProntuario(novoProntuario);

        // 4. Save the owning side. Cascade will persist the Prontuario correctly
        // because the object graph is now consistent.
        Consulta consultaSalva = consultaRepository.save(consulta);

        return modelMapper.map(consultaSalva.getProntuario(), ProntuarioDTO.class);
    }

    public List<ProntuarioDTO> listar() {
        return prontuarioRepository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, ProntuarioDTO.class))
                .toList();
    }

    public ProntuarioDTO buscarPorId(Long id) {
        Prontuario p = prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));
        return modelMapper.map(p, ProntuarioDTO.class);
    }

    @Transactional
    public ProntuarioDTO atualizar(Long id, ProntuarioUpdateRequest request) {
        Prontuario existente = prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));
        modelMapper.map(request, existente);
        Prontuario atualizado = prontuarioRepository.save(existente);
        return modelMapper.map(atualizado, ProntuarioDTO.class);
    }

    public void deletar(Long id) {
        if (!prontuarioRepository.existsById(id)) {
            throw new RuntimeException("Prontuário não encontrado");
        }
        prontuarioRepository.deleteById(id);
    }
}
