package br.edu.imepac.administrativo.services;

import br.edu.imepac.administrativo.dtos.medico.MedicoCreateRequest;
import br.edu.imepac.administrativo.dtos.medico.MedicoDTO;
import br.edu.imepac.administrativo.dtos.medico.MedicoUpdateRequest;
import br.edu.imepac.administrativo.repository.MedicoRepository;
import br.edu.imepac.agendamento.exceptions.ResourceNotFoundException;
import br.edu.imepac.common.entidades.Medico;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public MedicoDTO create(MedicoCreateRequest request) {
        Medico medico = new Medico();
        medico.setCrm(request.getCrm());
        medico.setNome(request.getNome());
        medicoRepository.save(medico);

        return toDTO(medico);
    }

    public List<MedicoDTO> findAll() {
        return medicoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public MedicoDTO findById(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
        return toDTO(medico);
    }

    public MedicoDTO update(Long id, MedicoUpdateRequest request) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));

        medico.setCrm(request.getCrm());
        medico.setNome(request.getNome());
        medicoRepository.save(medico);

        return toDTO(medico);
    }

    public void delete(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
        medicoRepository.delete(medico);
    }

    private MedicoDTO toDTO(Medico medico) {
        MedicoDTO dto = new MedicoDTO();
        dto.setId(medico.getId());
        dto.setCrm(medico.getCrm());
        dto.setNome(medico.getNome());
        return dto;
    }
}
