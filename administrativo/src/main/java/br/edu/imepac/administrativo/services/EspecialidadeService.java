package br.edu.imepac.administrativo.services;

import br.edu.imepac.administrativo.dtos.especialidade.EspecialidadeCreateRequest;
import br.edu.imepac.administrativo.dtos.especialidade.EspecialidadeDTO;
import br.edu.imepac.administrativo.dtos.especialidade.EspecialidadeUpdateRequest;
import br.edu.imepac.administrativo.repository.EspecialidadeRepository;
import br.edu.imepac.common.entidades.Especialidade;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EspecialidadeService {

    private final EspecialidadeRepository especialidadeRepository;
    private final ModelMapper modelMapper;

    public EspecialidadeService(EspecialidadeRepository especialidadeRepository, ModelMapper modelMapper) {
        this.especialidadeRepository = especialidadeRepository;
        this.modelMapper = modelMapper;
    }

    public EspecialidadeDTO createEspecialidade(EspecialidadeCreateRequest especialidadeCreateRequest) {
        log.info("createEspecialidade {}", especialidadeCreateRequest);
        Especialidade especialidade = modelMapper.map(especialidadeCreateRequest, Especialidade.class);
        Especialidade savedEspecialidade = especialidadeRepository.save(especialidade);
        return modelMapper.map(savedEspecialidade, EspecialidadeDTO.class);
    }

    public List<EspecialidadeDTO> findAllEspecialidades() {
        return especialidadeRepository.findAll().stream()
                .map(especialidade -> modelMapper.map(especialidade, EspecialidadeDTO.class))
                .collect(Collectors.toList());
    }

    public EspecialidadeDTO findEspecialidadeById(Long id) {
        Especialidade especialidade = especialidadeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("findEspecialidadeById {}", id);
                   return new RuntimeException("Especialidade not found with id: " + id);
                });
        return modelMapper.map(especialidade, EspecialidadeDTO.class);
    }

    public EspecialidadeDTO updateEspecialidade(Long id, EspecialidadeUpdateRequest especialidadeUpdateRequest) {
        Especialidade especialidade = especialidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidade not found with id: " + id));

        especialidade.setDescricao(especialidadeUpdateRequest.getDescricao());
        Especialidade updatedEspecialidade = especialidadeRepository.save(especialidade);
        return modelMapper.map(updatedEspecialidade, EspecialidadeDTO.class);
    }

    public void deleteEspecialidade(Long id) {
        if (!especialidadeRepository.existsById(id)) {
            throw new RuntimeException("Especialidade not found with id: " + id);
        }
        especialidadeRepository.deleteById(id);
    }
}
