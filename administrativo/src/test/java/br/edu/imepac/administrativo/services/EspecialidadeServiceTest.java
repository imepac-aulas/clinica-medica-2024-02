package br.edu.imepac.administrativo.services;

import br.edu.imepac.administrativo.dtos.especialidade.EspecialidadeCreateRequest;
import br.edu.imepac.administrativo.dtos.especialidade.EspecialidadeDTO;
import br.edu.imepac.administrativo.dtos.especialidade.EspecialidadeUpdateRequest;
import br.edu.imepac.administrativo.exceptions.ResourceNotFoundException;
import br.edu.imepac.administrativo.repositories.EspecialidadeRepository;
import br.edu.imepac.common.entidades.Especialidade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EspecialidadeServiceTest {

    @Mock
    private EspecialidadeRepository especialidadeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EspecialidadeService especialidadeService;

    private Especialidade especialidade;
    private EspecialidadeDTO especialidadeDTO;
    private EspecialidadeCreateRequest especialidadeCreateRequest;
    private EspecialidadeUpdateRequest especialidadeUpdateRequest;

    @BeforeEach
    void setUp() {
        // Baseado em common/src/main/java/br/edu/imepac/common/entidades/Especialidade.java
        especialidade = new Especialidade();
        especialidade.setId(1L);
        especialidade.setNome("Cardiologia");
        especialidade.setDescricao("Especialidade médica que se ocupa do diagnóstico e tratamento das doenças que acometem o coração.");
        especialidade.setMedicos(Collections.emptyList());

        // Baseado em administrativo/src/main/java/br/edu/imepac/administrativo/dtos/especialidade/EspecialidadeDTO.java
        especialidadeDTO = new EspecialidadeDTO();
        especialidadeDTO.setId(1L);
        especialidadeDTO.setNome("Cardiologia");
        especialidadeDTO.setDescricao("Especialidade médica que se ocupa do diagnóstico e tratamento das doenças que acometem o coração.");
        especialidadeDTO.setMedicos(Collections.emptyList());

        // Baseado em administrativo/src/main/java/br/edu/imepac/administrativo/dtos/especialidade/EspecialidadeCreateRequest.java
        especialidadeCreateRequest = new EspecialidadeCreateRequest();
        especialidadeCreateRequest.setNome("Cardiologia");
        especialidadeCreateRequest.setDescricao("Especialidade médica que se ocupa do diagnóstico e tratamento das doenças que acometem o coração.");

        // Baseado em administrativo/src/main/java/br/edu/imepac/administrativo/dtos/especialidade/EspecialidadeUpdateRequest.java
        especialidadeUpdateRequest = new EspecialidadeUpdateRequest();
        especialidadeUpdateRequest.setNome("Cardiologia Pediátrica");
        especialidadeUpdateRequest.setDescricao("Subespecialidade da pediatria e da cardiologia que se ocupa do diagnóstico e tratamento das cardiopatias na criança.");
    }

    @Test
    void testCreateEspecialidade() {
        when(modelMapper.map(especialidadeCreateRequest, Especialidade.class)).thenReturn(especialidade);
        when(especialidadeRepository.save(any(Especialidade.class))).thenReturn(especialidade);
        when(modelMapper.map(especialidade, EspecialidadeDTO.class)).thenReturn(especialidadeDTO);

        EspecialidadeDTO result = especialidadeService.createEspecialidade(especialidadeCreateRequest);

        assertNotNull(result);
        assertEquals(especialidadeDTO.getId(), result.getId());
        assertEquals(especialidadeDTO.getNome(), result.getNome());
        verify(especialidadeRepository, times(1)).save(any(Especialidade.class));
    }

    @Test
    void testFindAllEspecialidades() {
        when(especialidadeRepository.findAll()).thenReturn(Collections.singletonList(especialidade));
        when(modelMapper.map(especialidade, EspecialidadeDTO.class)).thenReturn(especialidadeDTO);

        List<EspecialidadeDTO> result = especialidadeService.findAllEspecialidades();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(especialidadeDTO.getNome(), result.get(0).getNome());
        verify(especialidadeRepository, times(1)).findAll();
    }

    @Test
    void testFindEspecialidadeById() {
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.of(especialidade));
        when(modelMapper.map(especialidade, EspecialidadeDTO.class)).thenReturn(especialidadeDTO);

        EspecialidadeDTO result = especialidadeService.findEspecialidadeById(1L);

        assertNotNull(result);
        assertEquals(especialidadeDTO.getId(), result.getId());
        verify(especialidadeRepository, times(1)).findById(1L);
    }

    @Test
    void testFindEspecialidadeById_NotFound() {
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            especialidadeService.findEspecialidadeById(1L);
        });

        verify(especialidadeRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateEspecialidade() {
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.of(especialidade));
        when(especialidadeRepository.save(any(Especialidade.class))).thenReturn(especialidade);

        especialidadeDTO.setNome(especialidadeUpdateRequest.getNome());
        especialidadeDTO.setDescricao(especialidadeUpdateRequest.getDescricao());
        when(modelMapper.map(especialidade, EspecialidadeDTO.class)).thenReturn(especialidadeDTO);

        doAnswer(invocation -> {
            EspecialidadeUpdateRequest request = invocation.getArgument(0);
            Especialidade entity = invocation.getArgument(1);
            entity.setNome(request.getNome());
            entity.setDescricao(request.getDescricao());
            return null;
        }).when(modelMapper).map(especialidadeUpdateRequest, especialidade);

        EspecialidadeDTO result = especialidadeService.updateEspecialidade(1L, especialidadeUpdateRequest);

        assertNotNull(result);
        assertEquals(especialidadeDTO.getId(), result.getId());
        assertEquals("Cardiologia Pediátrica", result.getNome());
        verify(especialidadeRepository, times(1)).findById(1L);
        verify(especialidadeRepository, times(1)).save(especialidade);
    }

    @Test
    void testUpdateEspecialidade_NotFound() {
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            especialidadeService.updateEspecialidade(1L, especialidadeUpdateRequest);
        });

        verify(especialidadeRepository, times(1)).findById(1L);
        verify(especialidadeRepository, never()).save(any(Especialidade.class));
    }

    @Test
    void testDeleteEspecialidade() {
        when(especialidadeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(especialidadeRepository).deleteById(1L);

        especialidadeService.deleteEspecialidade(1L);

        verify(especialidadeRepository, times(1)).existsById(1L);
        verify(especialidadeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteEspecialidade_NotFound() {
        when(especialidadeRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            especialidadeService.deleteEspecialidade(1L);
        });

        verify(especialidadeRepository, times(1)).existsById(1L);
        verify(especialidadeRepository, never()).deleteById(1L);
    }
}
