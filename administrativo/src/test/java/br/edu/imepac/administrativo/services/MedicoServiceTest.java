package br.edu.imepac.administrativo.services;

import br.edu.imepac.administrativo.dtos.medico.MedicoCreateRequest;
import br.edu.imepac.administrativo.dtos.medico.MedicoDTO;
import br.edu.imepac.administrativo.dtos.medico.MedicoUpdateRequest;
import br.edu.imepac.administrativo.exceptions.ResourceNotFoundException;
import br.edu.imepac.administrativo.repositories.EspecialidadeRepository;
import br.edu.imepac.administrativo.repositories.MedicoRepository;
import br.edu.imepac.common.entidades.Especialidade;
import br.edu.imepac.common.entidades.Medico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private EspecialidadeRepository especialidadeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MedicoService medicoService;

    private Medico medico;
    private MedicoDTO medicoDTO;
    private MedicoCreateRequest medicoCreateRequest;
    private MedicoUpdateRequest medicoUpdateRequest;
    private Especialidade especialidade;

    @BeforeEach
    void setUp() {
        especialidade = new Especialidade();
        especialidade.setId(1L);
        especialidade.setNome("Cardiologia");

        medico = new Medico();
        medico.setId(1L);
        medico.setNome("Dr. House");
        medico.setTelefone("123456789");
        medico.setEmail("house@example.com");
        medico.setDataNascimento(LocalDate.of(1980, 1, 1));
        medico.setCrm("123456");
        medico.setEspecialidade(especialidade);

        medicoDTO = new MedicoDTO();
        medicoDTO.setId(1L);
        medicoDTO.setNome("Dr. House");
        medicoDTO.setTelefone("123456789");
        medicoDTO.setEmail("house@example.com");
        medicoDTO.setDataNascimento(LocalDate.of(1980, 1, 1));
        medicoDTO.setCrm("123456");
        medicoDTO.setEspecialidadeId(1L);

        medicoCreateRequest = new MedicoCreateRequest();
        medicoCreateRequest.setNome("Dr. House");
        medicoCreateRequest.setTelefone("123456789");
        medicoCreateRequest.setEmail("house@example.com");
        medicoCreateRequest.setDataNascimento(LocalDate.of(1980, 1, 1));
        medicoCreateRequest.setCrm("123456");
        medicoCreateRequest.setEspecialidadeId(1L);

        medicoUpdateRequest = new MedicoUpdateRequest();
        medicoUpdateRequest.setNome("Dr. Gregory House");
        medicoUpdateRequest.setTelefone("987654321");
        medicoUpdateRequest.setEmail("g.house@example.com");
        medicoUpdateRequest.setDataNascimento(LocalDate.of(1979, 2, 2));
        medicoUpdateRequest.setCrm("654321");
        medicoUpdateRequest.setEspecialidadeId(1L);
    }

    @Test
    void testCreate() {
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.of(especialidade));
        when(modelMapper.map(medicoCreateRequest, Medico.class)).thenReturn(medico);
        when(medicoRepository.save(any(Medico.class))).thenReturn(medico);
        when(modelMapper.map(medico, MedicoDTO.class)).thenReturn(medicoDTO);

        MedicoDTO result = medicoService.create(medicoCreateRequest);

        assertNotNull(result);
        assertEquals(medicoDTO.getId(), result.getId());
        assertEquals(medicoDTO.getNome(), result.getNome());
        verify(medicoRepository, times(1)).save(any(Medico.class));
    }

    @Test
    void testCreate_EspecialidadeNotFound() {
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            medicoService.create(medicoCreateRequest);
        });

        verify(medicoRepository, never()).save(any(Medico.class));
    }

    @Test
    void testFindAll() {
        when(medicoRepository.findAll()).thenReturn(Collections.singletonList(medico));
        when(modelMapper.map(medico, MedicoDTO.class)).thenReturn(medicoDTO);

        List<MedicoDTO> result = medicoService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(medicoDTO.getNome(), result.get(0).getNome());
        verify(medicoRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(modelMapper.map(medico, MedicoDTO.class)).thenReturn(medicoDTO);

        MedicoDTO result = medicoService.findById(1L);

        assertNotNull(result);
        assertEquals(medicoDTO.getId(), result.getId());
        verify(medicoRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            medicoService.findById(1L);
        });

        verify(medicoRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdate() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.of(especialidade));
        when(medicoRepository.save(any(Medico.class))).thenReturn(medico);

        medicoDTO.setNome(medicoUpdateRequest.getNome());
        when(modelMapper.map(medico, MedicoDTO.class)).thenReturn(medicoDTO);

        doAnswer(invocation -> {
            MedicoUpdateRequest request = invocation.getArgument(0);
            Medico entity = invocation.getArgument(1);
            entity.setNome(request.getNome());
            entity.setTelefone(request.getTelefone());
            entity.setEmail(request.getEmail());
            entity.setDataNascimento(request.getDataNascimento());
            entity.setCrm(request.getCrm());
            return null;
        }).when(modelMapper).map(medicoUpdateRequest, medico);

        MedicoDTO result = medicoService.update(1L, medicoUpdateRequest);

        assertNotNull(result);
        assertEquals(medicoDTO.getId(), result.getId());
        assertEquals("Dr. Gregory House", result.getNome());
        verify(medicoRepository, times(1)).findById(1L);
        verify(medicoRepository, times(1)).save(medico);
    }

    @Test
    void testUpdate_MedicoNotFound() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            medicoService.update(1L, medicoUpdateRequest);
        });

        verify(medicoRepository, times(1)).findById(1L);
        verify(medicoRepository, never()).save(any(Medico.class));
    }

    @Test
    void testUpdate_EspecialidadeNotFound() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(especialidadeRepository.findById(medicoUpdateRequest.getEspecialidadeId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            medicoService.update(1L, medicoUpdateRequest);
        });

        verify(medicoRepository, times(1)).findById(1L);
        verify(especialidadeRepository, times(1)).findById(medicoUpdateRequest.getEspecialidadeId());
        verify(medicoRepository, never()).save(any(Medico.class));
    }

    @Test
    void testDelete() {
        when(medicoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(medicoRepository).deleteById(1L);

        medicoService.delete(1L);

        verify(medicoRepository, times(1)).existsById(1L);
        verify(medicoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        when(medicoRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            medicoService.delete(1L);
        });

        verify(medicoRepository, times(1)).existsById(1L);
        verify(medicoRepository, never()).deleteById(1L);
    }

    @Test
    void testFindMedicosByEspecialidadeId() {
        when(medicoRepository.findByEspecialidadeId(1L)).thenReturn(Collections.singletonList(medico));
        when(modelMapper.map(medico, MedicoDTO.class)).thenReturn(medicoDTO);

        List<MedicoDTO> result = medicoService.findMedicosByEspecialidadeId(1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(medicoDTO.getEspecialidadeId(), result.get(0).getEspecialidadeId());
        verify(medicoRepository, times(1)).findByEspecialidadeId(1L);
    }
}
