package br.edu.imepac.administrativo.services;

import br.edu.imepac.administrativo.dtos.perfil.PerfilCreateRequest;
import br.edu.imepac.administrativo.dtos.perfil.PerfilDTO;
import br.edu.imepac.administrativo.dtos.perfil.PerfilUpdateRequest;
import br.edu.imepac.administrativo.repositories.PerfilRepository;
import br.edu.imepac.common.entidades.Perfil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j

public class PerfilService {
    private final PerfilRepository perfilRepository;
    private final ModelMapper modelMapper;

    public PerfilService(PerfilRepository perfilRepository, ModelMapper modelMapper) {
        this.perfilRepository = perfilRepository;
        this.modelMapper = modelMapper;
    }

    public PerfilDTO createPerfil(PerfilCreateRequest perfilCreateRequest) {
        log.info("createPerfil {}", perfilCreateRequest);
        Perfil perfil = modelMapper.map(perfilCreateRequest, Perfil.class);
        Perfil savedPerfil = perfilRepository.save(perfil);
        return modelMapper.map(savedPerfil, PerfilDTO.class);
    }

    public List<PerfilDTO> findAllPerfis() {
        return perfilRepository.findAll().stream().map(perfil -> modelMapper.map(perfil, PerfilDTO.class)).collect(Collectors.toList());
    }

    public PerfilDTO findPerfilById(Long id) {
        Perfil perfil = perfilRepository.findById(id).orElseThrow(() -> {
                    log.error("findPerfilById {}", id);
                    return new RuntimeException("Perfil not found with id: " + id);
                });

        return modelMapper.map(perfil, PerfilDTO.class);
    }

    public PerfilDTO updatePerfil(Long id, PerfilUpdateRequest request) {
        Perfil perfil = perfilRepository.findById(id).orElseThrow(() -> new RuntimeException("Perfil not found with id: " + id));

        perfil.setNome(request.getNome());

        Perfil updatedPerfil = perfilRepository.save(perfil);
        return modelMapper.map(updatedPerfil, PerfilDTO.class);
    }

    public void deletePerfil(Long id) {
        if (!perfilRepository.existsById(id)) {
            throw new RuntimeException("Perfil não encontrado: " + id);
        }
        perfilRepository.deleteById(id);
    }
}
