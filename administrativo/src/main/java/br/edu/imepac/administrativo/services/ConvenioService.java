package br.edu.imepac.administrativo.services;

import br.edu.imepac.administrativo.dtos.convenio.ConvenioCreateRequest;
import br.edu.imepac.administrativo.dtos.convenio.ConvenioDTO;
import br.edu.imepac.administrativo.dtos.convenio.ConvenioUpdateRequest;
import br.edu.imepac.administrativo.exceptions.ResourceNotFoundException;
import br.edu.imepac.administrativo.repositories.ConvenioRepository;
import br.edu.imepac.common.entidades.Convenio;
import br.edu.imepac.common.utils.StatusConvenioEnum;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConvenioService {

    private final ConvenioRepository convenioRepository;
    private final ModelMapper modelMapper;

    public ConvenioService(ConvenioRepository convenioRepository, ModelMapper modelMapper) {
        this.convenioRepository = convenioRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public ConvenioDTO create(ConvenioCreateRequest convenioRequest) {
        Convenio convenio = modelMapper.map(convenioRequest, Convenio.class);
        convenio.setStatus(StatusConvenioEnum.ATIVO); // Define o status inicial
        Convenio savedConvenio = convenioRepository.save(convenio);
        return modelMapper.map(savedConvenio, ConvenioDTO.class);
    }

    @Transactional(readOnly = true)
    public List<ConvenioDTO> findAll() {
        return convenioRepository.findAll().stream()
                .map(convenio -> modelMapper.map(convenio, ConvenioDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ConvenioDTO findById(Long id) {
        Convenio convenio = convenioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convênio não encontrado com id: " + id));
        return modelMapper.map(convenio, ConvenioDTO.class);
    }

    @Transactional
    public ConvenioDTO update(Long id, ConvenioUpdateRequest convenioRequest) {
        Convenio convenio = convenioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convênio não encontrado com id: " + id));

        modelMapper.map(convenioRequest, convenio);
        Convenio updatedConvenio = convenioRepository.save(convenio);
        return modelMapper.map(updatedConvenio, ConvenioDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        // A "deleção" de convênio será uma inativação
        Convenio convenio = convenioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convênio não encontrado com id: " + id));
        convenio.setStatus(StatusConvenioEnum.INATIVO);
        convenioRepository.save(convenio);
    }
}
