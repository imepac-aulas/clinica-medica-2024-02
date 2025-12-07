package br.edu.imepac.agendamento.services;

import br.edu.imepac.agendamento.dtos.paciente.PacienteCreateRequest;
import br.edu.imepac.agendamento.dtos.paciente.PacienteDTO;
import br.edu.imepac.agendamento.dtos.paciente.PacienteUpdateRequest;
import br.edu.imepac.common.exceptions.BusinessException;
import br.edu.imepac.common.exceptions.ResourceNotFoundException;
import br.edu.imepac.common.apis.PacienteResponse;
import br.edu.imepac.common.entidades.Paciente;
import br.edu.imepac.agendamento.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Transactional
    public PacienteDTO criar(PacienteCreateRequest request) {
        // validar CPF
        if (request.getCpf() == null || request.getCpf().isBlank()) {
            throw new BusinessException("CPF é obrigatório.");
        }
        if (pacienteRepository.existsByCpf(request.getCpf())) {
            throw new BusinessException("Já existe um paciente cadastrado com este CPF.");
        }

        // validar Cartão SUS
        if (request.getNumeroCartaoSUS() == null || request.getNumeroCartaoSUS().isBlank()) {
            throw new BusinessException("Número do cartão SUS é obrigatório.");
        }
        if (pacienteRepository.existsByNumeroCartaoSUS(request.getNumeroCartaoSUS())) {
            throw new BusinessException("Já existe um paciente cadastrado com este número de cartão SUS.");
        }

        Paciente paciente = new Paciente();
        paciente.setNome(request.getNome());
        paciente.setTelefone(request.getTelefone());
        paciente.setEmail(request.getEmail());
        paciente.setDataNascimento(request.getDataNascimento());
        paciente.setCpf(request.getCpf());
        paciente.setNumeroCartaoSUS(request.getNumeroCartaoSUS());

        paciente = pacienteRepository.save(paciente);
        return PacienteDTO.fromEntity(paciente);
    }

    public List<PacienteDTO> listar() {
        return pacienteRepository.findAll()
                .stream()
                .map(PacienteDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public PacienteDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + id));
        return PacienteDTO.fromEntity(paciente);
    }

    @Transactional
    public PacienteDTO atualizar(Long id, PacienteUpdateRequest request) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + id));

        // Se o CPF foi alterado, validar unicidade
        if (request.getCpf() != null && !request.getCpf().equals(paciente.getCpf())) {
            if (pacienteRepository.existsByCpf(request.getCpf())) {
                throw new BusinessException("Outro paciente já possui este CPF.");
            }
            paciente.setCpf(request.getCpf());
        }

        // Se o número do SUS foi alterado, validar unicidade
        if (request.getNumeroCartaoSUS() != null && !request.getNumeroCartaoSUS().equals(paciente.getNumeroCartaoSUS())) {
            if (pacienteRepository.existsByNumeroCartaoSUS(request.getNumeroCartaoSUS())) {
                throw new BusinessException("Outro paciente já possui este número de cartão SUS.");
            }
            paciente.setNumeroCartaoSUS(request.getNumeroCartaoSUS());
        }

        // Atualiza campos mutáveis
        paciente.setNome(request.getNome());
        paciente.setTelefone(request.getTelefone());
        paciente.setEmail(request.getEmail());
        paciente.setDataNascimento(request.getDataNascimento());

        paciente = pacienteRepository.save(paciente);
        return PacienteDTO.fromEntity(paciente);
    }

    @Transactional
    public void remover(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente não encontrado com id: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    public PacienteResponse buscarParaIntegracao(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

        PacienteResponse response = new PacienteResponse();
        response.setId(paciente.getId());
        response.setNome(paciente.getNome());
        response.setTelefone(paciente.getTelefone());
        response.setEmail(paciente.getEmail());
        response.setCpf(paciente.getCpf());
        response.setNumeroCartaoSUS(paciente.getNumeroCartaoSUS());
        response.setDataNascimento(paciente.getDataNascimento());
        return response;
    }
    @Transactional(readOnly = true)
    public Paciente buscarEntidadePorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + id));
    }

}
