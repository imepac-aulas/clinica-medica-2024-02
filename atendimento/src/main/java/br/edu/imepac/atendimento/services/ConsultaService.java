package br.edu.imepac.atendimento.services;

import br.edu.imepac.atendimento.dtos.consulta.ConsultaDTO;
import br.edu.imepac.atendimento.repositories.ConsultaRepository;
import br.edu.imepac.common.entidades.Consulta;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultaService {
    private ConsultaRepository repository;
    private ModelMapper modelMapper;

    public ConsultaService(ConsultaRepository repository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    public ConsultaDTO getConsultaPorId(Long id) {
        Optional<Consulta> consulta = repository.findById(id);
        if (consulta.isPresent()) {
            Consulta consultaEntity = consulta.get();
            return this.modelMapper.map(consultaEntity, ConsultaDTO.class);
        }
        return null;
    }
}
