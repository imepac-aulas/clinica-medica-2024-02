package br.edu.imepac.agendamento.services;

import br.edu.imepac.agendamento.dtos.convenio.ConvenioDTO;
import br.edu.imepac.common.exceptions.ResourceNotFoundException;
import br.edu.imepac.agendamento.repositories.ConvenioRepository;
import br.edu.imepac.common.entidades.Convenio;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConvenioService {

    private final ConvenioRepository convenioRepository;
    private final ModelMapper modelMapper;

    public ConvenioDTO buscarPorId(Long id) {
        Convenio convenio = convenioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convênio não encontrado com id: " + id));
        return modelMapper.map(convenio, ConvenioDTO.class);
    }

    public List<ConvenioDTO> listarTodos() {
        return convenioRepository.findAll().stream()
                .map(convenio -> modelMapper.map(convenio, ConvenioDTO.class))
                .collect(Collectors.toList());
    }
}
