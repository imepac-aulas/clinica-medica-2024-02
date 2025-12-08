package br.edu.imepac.administrativo.services;

import br.edu.imepac.administrativo.dtos.perfil.PerfilCreateRequest;
import br.edu.imepac.administrativo.dtos.perfil.PerfilDTO;
import br.edu.imepac.administrativo.dtos.perfil.PerfilUpdateRequest;
import br.edu.imepac.administrativo.repositories.PerfilRepository;
import br.edu.imepac.common.entidades.Perfil;
import br.edu.imepac.common.utils.EnumFuncionalidades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PerfilServiceTest {

    @Mock
    private PerfilRepository perfilRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PerfilService perfilService;

    private Perfil perfil;
    private PerfilDTO perfilDTO;
    private PerfilCreateRequest perfilCreateRequest;
    private PerfilUpdateRequest perfilUpdateRequest;

    @BeforeEach
    void setUp() {
        Set<EnumFuncionalidades> funcionalidades = new HashSet<>();
        funcionalidades.add(EnumFuncionalidades.AGENDAMENTO_CONSULTA);

        perfil = new Perfil(1L, "Admin", funcionalidades);

        perfilDTO = new PerfilDTO();
        perfilDTO.setId(1L);
        perfilDTO.setNome("Admin");
        perfilDTO.setFuncionalidades(funcionalidades);

        perfilCreateRequest = new PerfilCreateRequest();
        perfilCreateRequest.setNome("Admin");
        perfilCreateRequest.setFuncionalidades(funcionalidades);

        Set<EnumFuncionalidades> funcionalidadesUpdate = new HashSet<>();
        funcionalidadesUpdate.add(EnumFuncionalidades.CADASTRO_PACIENTE);

        perfilUpdateRequest = new PerfilUpdateRequest();
        perfilUpdateRequest.setNome("Admin Updated");
        perfilUpdateRequest.setFuncionalidades(funcionalidadesUpdate);
    }

    @Test
    void testCriarPerfil() {
        when(modelMapper.map(perfilCreateRequest, Perfil.class)).thenReturn(perfil);
        when(perfilRepository.save(any(Perfil.class))).thenReturn(perfil);
        when(modelMapper.map(perfil, PerfilDTO.class)).thenReturn(perfilDTO);

        PerfilDTO result = perfilService.criarPerfil(perfilCreateRequest);

        assertNotNull(result);
        assertEquals(perfilDTO.getId(), result.getId());
        assertEquals(perfilDTO.getNome(), result.getNome());
        verify(perfilRepository, times(1)).save(any(Perfil.class));
    }

    @Test
    void testListarTodosOsPerfis() {
        when(perfilRepository.findAll()).thenReturn(Collections.singletonList(perfil));
        when(modelMapper.map(perfil, PerfilDTO.class)).thenReturn(perfilDTO);

        List<PerfilDTO> result = perfilService.listarTodosOsPerfis();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(perfilDTO.getNome(), result.get(0).getNome());
        verify(perfilRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPerfilPorId() {
        when(perfilRepository.findById(1L)).thenReturn(Optional.of(perfil));
        when(modelMapper.map(perfil, PerfilDTO.class)).thenReturn(perfilDTO);

        PerfilDTO result = perfilService.buscarPerfilPorId(1L);

        assertNotNull(result);
        assertEquals(perfilDTO.getId(), result.getId());
        verify(perfilRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPerfilPorId_NotFound() {
        when(perfilRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            perfilService.buscarPerfilPorId(1L);
        });

        verify(perfilRepository, times(1)).findById(1L);
    }

    @Test
    void testAtualizarPerfil() {
        when(perfilRepository.findById(1L)).thenReturn(Optional.of(perfil));
        when(perfilRepository.save(any(Perfil.class))).thenReturn(perfil);

        perfilDTO.setNome(perfilUpdateRequest.getNome());
        perfilDTO.setFuncionalidades(perfilUpdateRequest.getFuncionalidades());
        when(modelMapper.map(perfil, PerfilDTO.class)).thenReturn(perfilDTO);

        PerfilDTO result = perfilService.atualizarPerfil(1L, perfilUpdateRequest);

        assertNotNull(result);
        assertEquals(perfilDTO.getId(), result.getId());
        assertEquals("Admin Updated", result.getNome());
        assertTrue(result.getFuncionalidades().contains(EnumFuncionalidades.CADASTRO_PACIENTE));
        verify(perfilRepository, times(1)).findById(1L);
        verify(perfilRepository, times(1)).save(perfil);
    }

    @Test
    void testAtualizarPerfil_NotFound() {
        when(perfilRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            perfilService.atualizarPerfil(1L, perfilUpdateRequest);
        });

        verify(perfilRepository, times(1)).findById(1L);
        verify(perfilRepository, never()).save(any(Perfil.class));
    }

    @Test
    void testDeletarPerfil() {
        when(perfilRepository.existsById(1L)).thenReturn(true);
        doNothing().when(perfilRepository).deleteById(1L);

        perfilService.deletarPerfil(1L);

        verify(perfilRepository, times(1)).existsById(1L);
        verify(perfilRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletarPerfil_NotFound() {
        when(perfilRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> {
            perfilService.deletarPerfil(1L);
        });

        verify(perfilRepository, times(1)).existsById(1L);
        verify(perfilRepository, never()).deleteById(1L);
    }
}
