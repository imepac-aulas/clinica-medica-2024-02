package br.edu.imepac.administrativo.services;

import br.edu.imepac.administrativo.dtos.medico.MedicoCreateRequest;
import br.edu.imepac.administrativo.dtos.medico.MedicoDTO;
import br.edu.imepac.administrativo.dtos.medico.MedicoUpdateRequest;
import br.edu.imepac.administrativo.exceptions.ResourceNotFoundException;
import br.edu.imepac.administrativo.repositories.EspecialidadeRepository;
import br.edu.imepac.administrativo.repositories.MedicoRepository;
import br.edu.imepac.common.entidades.Especialidade;
import br.edu.imepac.common.entidades.Medico;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final EspecialidadeRepository especialidadeRepository;
    private final ModelMapper modelMapper;

    public MedicoService(MedicoRepository medicoRepository, EspecialidadeRepository especialidadeRepository, ModelMapper modelMapper) {
        this.medicoRepository = medicoRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public MedicoDTO create(MedicoCreateRequest medicoRequest) {
        Especialidade especialidade = especialidadeRepository.findById(medicoRequest.getEspecialidadeId())
                .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada com id: " + medicoRequest.getEspecialidadeId()));

        Medico medico = modelMapper.map(medicoRequest, Medico.class);
        medico.setEspecialidade(especialidade);

        Medico savedMedico = medicoRepository.save(medico);
        return modelMapper.map(savedMedico, MedicoDTO.class);
    }

    @Transactional(readOnly = true)
    public List<MedicoDTO> findAll() {
        return medicoRepository.findAll().stream()
                .map(medico -> modelMapper.map(medico, MedicoDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MedicoDTO findById(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com id: " + id));
        return modelMapper.map(medico, MedicoDTO.class);
    }

    @Transactional
    public MedicoDTO update(Long id, MedicoUpdateRequest medicoRequest) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com id: " + id));

        Especialidade especialidade = especialidadeRepository.findById(medicoRequest.getEspecialidadeId())
                .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada com id: " + medicoRequest.getEspecialidadeId()));

        modelMapper.map(medicoRequest, medico);
        medico.setEspecialidade(especialidade);

        Medico updatedMedico = medicoRepository.save(medico);
        return modelMapper.map(updatedMedico, MedicoDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Médico não encontrado com id: " + id);
        }
        medicoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<MedicoDTO> findMedicosByEspecialidadeId(Long especialidadeId) {
        List<Medico> medicos = medicoRepository.findByEspecialidadeId(especialidadeId);
        return medicos.stream()
                .map(medico -> modelMapper.map(medico, MedicoDTO.class))
                .collect(Collectors.toList());
    }
}
