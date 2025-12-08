package br.edu.imepac.agendamento.services;

import br.edu.imepac.agendamento.repositories.MedicoRepository;
import br.edu.imepac.common.entidades.Especialidade;
import br.edu.imepac.common.entidades.Medico;
import br.edu.imepac.common.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @InjectMocks
    private MedicoService medicoService;

    private Medico medico;

    @BeforeEach
    void setUp() {
        Especialidade especialidade = new Especialidade();
        especialidade.setId(1L);
        especialidade.setNome("Cardiologia");

        medico = new Medico();
        medico.setId(1L);
        medico.setNome("Dr. House");
        medico.setCrm("123456");
        medico.setEspecialidade(especialidade);
    }

    @Test
    void testBuscarPorId() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));

        Medico result = medicoService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(medico.getId(), result.getId());
        verify(medicoRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorId_NotFound() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> medicoService.buscarPorId(1L));

        verify(medicoRepository, times(1)).findById(1L);
    }

    @Test
    void testListarTodos() {
        when(medicoRepository.findAll()).thenReturn(Collections.singletonList(medico));

        List<Medico> result = medicoService.listarTodos();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(medicoRepository, times(1)).findAll();
    }

    @Test
    void testCriarMedico() {
        when(medicoRepository.save(any(Medico.class))).thenReturn(medico);

        Medico result = medicoService.criarMedico(new Medico());

        assertNotNull(result);
        assertEquals(medico.getId(), result.getId());
        verify(medicoRepository, times(1)).save(any(Medico.class));
    }

    @Test
    void testAtualizarMedico() {
        Medico medicoAtualizado = new Medico();
        medicoAtualizado.setNome("Dr. Gregory House");
        medicoAtualizado.setCrm("654321");
        medicoAtualizado.setEspecialidade(medico.getEspecialidade());

        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(medicoRepository.save(any(Medico.class))).thenReturn(medicoAtualizado);

        Medico result = medicoService.atualizarMedico(1L, medicoAtualizado);

        assertNotNull(result);
        assertEquals("Dr. Gregory House", result.getNome());
        assertEquals("654321", result.getCrm());
        verify(medicoRepository, times(1)).findById(1L);
        verify(medicoRepository, times(1)).save(medico);
    }

    @Test
    void testAtualizarMedico_NotFound() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> medicoService.atualizarMedico(1L, new Medico()));

        verify(medicoRepository, times(1)).findById(1L);
        verify(medicoRepository, never()).save(any(Medico.class));
    }

    @Test
    void testDeletarMedico() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        doNothing().when(medicoRepository).delete(medico);

        medicoService.deletarMedico(1L);

        verify(medicoRepository, times(1)).findById(1L);
        verify(medicoRepository, times(1)).delete(medico);
    }

    @Test
    void testDeletarMedico_NotFound() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> medicoService.deletarMedico(1L));

        verify(medicoRepository, times(1)).findById(1L);
        verify(medicoRepository, never()).delete(any(Medico.class));
    }
}
