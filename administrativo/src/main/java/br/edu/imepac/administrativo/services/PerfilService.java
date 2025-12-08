package br.edu.imepac.administrativo.services;

import br.edu.imepac.administrativo.dtos.perfil.PerfilCreateRequest;
import br.edu.imepac.administrativo.dtos.perfil.PerfilDTO;
import br.edu.imepac.administrativo.dtos.perfil.PerfilUpdateRequest;
import br.edu.imepac.administrativo.repositories.PerfilRepository;
import br.edu.imepac.common.entidades.Perfil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public PerfilDTO criarPerfil(PerfilCreateRequest perfilCreateRequest) {
        log.info("Criando novo perfil com nome: {}", perfilCreateRequest.getNome());
        Perfil perfil = modelMapper.map(perfilCreateRequest, Perfil.class);
        Perfil savedPerfil = perfilRepository.save(perfil);
        log.info("Perfil criado com sucesso com ID: {}", savedPerfil.getId());
        return modelMapper.map(savedPerfil, PerfilDTO.class);
    }

    public List<PerfilDTO> listarTodosOsPerfis() {
        log.info("Listando todos os perfis.");
        return perfilRepository.findAll().stream()
                .map(perfil -> modelMapper.map(perfil, PerfilDTO.class))
                .collect(Collectors.toList());
    }

    public PerfilDTO buscarPerfilPorId(Long id) {
        log.info("Buscando perfil com ID: {}", id);
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Perfil não encontrado com ID: {}", id);
                    return new RuntimeException("Perfil não encontrado com id: " + id);
                });
        return modelMapper.map(perfil, PerfilDTO.class);
    }

    @Transactional
    public PerfilDTO atualizarPerfil(Long id, PerfilUpdateRequest request) {
        log.info("Atualizando perfil com ID: {}", id);
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado com id: " + id));

        // Atualiza nome e funcionalidades
        perfil.setNome(request.getNome());
        perfil.setFuncionalidades(request.getFuncionalidades());

        Perfil updatedPerfil = perfilRepository.save(perfil);
        log.info("Perfil com ID: {} atualizado com sucesso.", id);
        return modelMapper.map(updatedPerfil, PerfilDTO.class);
    }

    @Transactional
    public void deletarPerfil(Long id) {
        log.info("Deletando perfil com ID: {}", id);
        if (!perfilRepository.existsById(id)) {
            log.error("Tentativa de deletar perfil não existente com ID: {}", id);
            throw new RuntimeException("Perfil não encontrado: " + id);
        }
        perfilRepository.deleteById(id);
        log.info("Perfil com ID: {} deletado com sucesso.", id);
    }
}
