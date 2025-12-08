package br.edu.imepac.agendamento.services;

import br.edu.imepac.agendamento.dtos.paciente.PacienteCreateRequest;
import br.edu.imepac.agendamento.dtos.paciente.PacienteDTO;
import br.edu.imepac.agendamento.dtos.paciente.PacienteUpdateRequest;
import br.edu.imepac.agendamento.repositories.PacienteRepository;
import br.edu.imepac.common.entidades.Paciente;
import br.edu.imepac.common.exceptions.BusinessException;
import br.edu.imepac.common.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    private Paciente paciente;
    private PacienteCreateRequest createRequest;
    private PacienteUpdateRequest updateRequest;

    @BeforeEach
    void setUp() {
        paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("John Doe");
        paciente.setCpf("123.456.789-00");
        paciente.setNumeroCartaoSUS("700123456789012");
        paciente.setDataNascimento(LocalDate.of(1990, 1, 1));
        paciente.setEmail("john.doe@example.com");
        paciente.setTelefone("123456789");

        createRequest = new PacienteCreateRequest();
        createRequest.setNome("John Doe");
        createRequest.setCpf("123.456.789-00");
        createRequest.setNumeroCartaoSUS("700123456789012");
        createRequest.setDataNascimento(LocalDate.of(1990, 1, 1));
        createRequest.setEmail("john.doe@example.com");
        createRequest.setTelefone("123456789");

        updateRequest = new PacienteUpdateRequest();
        updateRequest.setNome("John Doe Updated");
        updateRequest.setCpf("111.222.333-44");
        updateRequest.setNumeroCartaoSUS("700987654321098");
    }

    @Test
    void testCriar() {
        when(pacienteRepository.existsByCpf(anyString())).thenReturn(false);
        when(pacienteRepository.existsByNumeroCartaoSUS(anyString())).thenReturn(false);
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        PacienteDTO result = pacienteService.criar(createRequest);

        assertNotNull(result);
        assertEquals(paciente.getNome(), result.getNome());
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    @Test
    void testCriar_CpfJaExiste() {
        when(pacienteRepository.existsByCpf(anyString())).thenReturn(true);

        assertThrows(BusinessException.class, () -> pacienteService.criar(createRequest));

        verify(pacienteRepository, never()).save(any(Paciente.class));
    }

    @Test
    void testCriar_CartaoSusJaExiste() {
        when(pacienteRepository.existsByCpf(anyString())).thenReturn(false);
        when(pacienteRepository.existsByNumeroCartaoSUS(anyString())).thenReturn(true);

        assertThrows(BusinessException.class, () -> pacienteService.criar(createRequest));

        verify(pacienteRepository, never()).save(any(Paciente.class));
    }

    @Test
    void testListar() {
        when(pacienteRepository.findAll()).thenReturn(Collections.singletonList(paciente));

        List<PacienteDTO> result = pacienteService.listar();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(pacienteRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        PacienteDTO result = pacienteService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(paciente.getId(), result.getId());
        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorId_NotFound() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> pacienteService.buscarPorId(1L));
    }

    @Test
    void testAtualizar() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(pacienteRepository.existsByCpf(updateRequest.getCpf())).thenReturn(false);
        when(pacienteRepository.existsByNumeroCartaoSUS(updateRequest.getNumeroCartaoSUS())).thenReturn(false);
        when(pacienteRepository.save(any(Paciente.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PacienteDTO result = pacienteService.atualizar(1L, updateRequest);

        assertNotNull(result);
        assertEquals(updateRequest.getNome(), result.getNome());
        assertEquals(updateRequest.getCpf(), result.getCpf());
        assertEquals(updateRequest.getNumeroCartaoSUS(), result.getNumeroCartaoSUS());
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    @Test
    void testAtualizar_CpfJaExiste() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(pacienteRepository.existsByCpf(updateRequest.getCpf())).thenReturn(true);

        assertThrows(BusinessException.class, () -> pacienteService.atualizar(1L, updateRequest));

        verify(pacienteRepository, never()).save(any(Paciente.class));
    }

    @Test
    void testAtualizar_CartaoSusJaExiste() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(pacienteRepository.existsByCpf(updateRequest.getCpf())).thenReturn(false);
        when(pacienteRepository.existsByNumeroCartaoSUS(updateRequest.getNumeroCartaoSUS())).thenReturn(true);

        assertThrows(BusinessException.class, () -> pacienteService.atualizar(1L, updateRequest));

        verify(pacienteRepository, never()).save(any(Paciente.class));
    }

    @Test
    void testRemover() {
        when(pacienteRepository.existsById(1L)).thenReturn(true);
        doNothing().when(pacienteRepository).deleteById(1L);

        pacienteService.remover(1L);

        verify(pacienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRemover_NotFound() {
        when(pacienteRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> pacienteService.remover(1L));

        verify(pacienteRepository, never()).deleteById(1L);
    }
}
