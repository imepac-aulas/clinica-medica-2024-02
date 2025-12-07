package br.edu.imepac.administrativo.services;

import br.edu.imepac.administrativo.dtos.paciente.PacienteCreateRequest;
import br.edu.imepac.administrativo.dtos.paciente.PacienteDTO;
import br.edu.imepac.administrativo.dtos.paciente.PacienteUpdateRequest;
import br.edu.imepac.administrativo.exceptions.ResourceNotFoundException;
import br.edu.imepac.administrativo.repositories.PacienteRepository;
import br.edu.imepac.common.entidades.Paciente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public PacienteDTO create(PacienteCreateRequest pacienteRequest) {
        Paciente paciente = modelMapper.map(pacienteRequest, Paciente.class);
        Paciente savedPaciente = pacienteRepository.save(paciente);
        return modelMapper.map(savedPaciente, PacienteDTO.class);
    }

    @Transactional(readOnly = true)
    public List<PacienteDTO> findAll() {
        return pacienteRepository.findAll().stream()
                .map(paciente -> modelMapper.map(paciente, PacienteDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PacienteDTO findById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + id));
        return modelMapper.map(paciente, PacienteDTO.class);
    }

    @Transactional
    public PacienteDTO update(Long id, PacienteUpdateRequest pacienteRequest) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + id));
        
        modelMapper.map(pacienteRequest, paciente);
        Paciente updatedPaciente = pacienteRepository.save(paciente);
        return modelMapper.map(updatedPaciente, PacienteDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente não encontrado com id: " + id);
        }
        pacienteRepository.deleteById(id);
    }
}
