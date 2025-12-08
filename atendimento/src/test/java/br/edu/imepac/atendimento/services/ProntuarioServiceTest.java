package br.edu.imepac.atendimento.services;

import br.edu.imepac.atendimento.dtos.prontuario.ProntuarioCreateRequest;
import br.edu.imepac.atendimento.dtos.prontuario.ProntuarioDTO;
import br.edu.imepac.atendimento.dtos.prontuario.ProntuarioUpdateRequest;
import br.edu.imepac.atendimento.repositories.ConsultaRepository;
import br.edu.imepac.atendimento.repositories.ProntuarioRepository;
import br.edu.imepac.common.entidades.Consulta;
import br.edu.imepac.common.entidades.Prontuario;
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
class ProntuarioServiceTest {

    @Mock
    private ProntuarioRepository prontuarioRepository;

    @Mock
    private ConsultaRepository consultaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProntuarioService prontuarioService;

    private Prontuario prontuario;
    private Consulta consulta;
    private ProntuarioCreateRequest createRequest;
    private ProntuarioUpdateRequest updateRequest;
    private ProntuarioDTO prontuarioDTO;

    @BeforeEach
    void setUp() {
        consulta = new Consulta();
        consulta.setId(1L);

        prontuario = new Prontuario();
        prontuario.setId(1L);
        prontuario.setQueixaPrincipal("Dor de cabeça");
        prontuario.setDiagnostico("Enxaqueca");
        prontuario.setConsulta(consulta);

        createRequest = new ProntuarioCreateRequest();
        createRequest.setConsultaId(1L);
        createRequest.setQueixaPrincipal("Dor de cabeça");
        createRequest.setDiagnostico("Enxaqueca");

        updateRequest = new ProntuarioUpdateRequest();
        updateRequest.setDiagnostico("Enxaqueca crônica");

        prontuarioDTO = new ProntuarioDTO();
        prontuarioDTO.setId(1L);
        prontuarioDTO.setQueixaPrincipal("Dor de cabeça");
        prontuarioDTO.setDiagnostico("Enxaqueca");
    }

    @Test
    void testCriar() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));
        when(modelMapper.map(createRequest, Prontuario.class)).thenReturn(prontuario);
        when(consultaRepository.save(any(Consulta.class))).thenAnswer(invocation -> {
            Consulta c = invocation.getArgument(0);
            c.getProntuario().setId(1L); // Simula o save do prontuário
            return c;
        });
        when(modelMapper.map(prontuario, ProntuarioDTO.class)).thenReturn(prontuarioDTO);

        ProntuarioDTO result = prontuarioService.criar(createRequest);

        assertNotNull(result);
        assertEquals(prontuario.getId(), result.getId());
        verify(consultaRepository, times(1)).save(consulta);
    }

    @Test
    void testCriar_ConsultaNaoEncontrada() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> prontuarioService.criar(createRequest));

        verify(consultaRepository, never()).save(any(Consulta.class));
    }

    @Test
    void testCriar_ConsultaJaPossuiProntuario() {
        consulta.setProntuario(new Prontuario());
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        assertThrows(IllegalStateException.class, () -> prontuarioService.criar(createRequest));

        verify(consultaRepository, never()).save(any(Consulta.class));
    }

    @Test
    void testListar() {
        when(prontuarioRepository.findAll()).thenReturn(Collections.singletonList(prontuario));
        when(modelMapper.map(prontuario, ProntuarioDTO.class)).thenReturn(prontuarioDTO);

        List<ProntuarioDTO> result = prontuarioService.listar();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(prontuarioRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId() {
        when(prontuarioRepository.findById(1L)).thenReturn(Optional.of(prontuario));
        when(modelMapper.map(prontuario, ProntuarioDTO.class)).thenReturn(prontuarioDTO);

        ProntuarioDTO result = prontuarioService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(prontuario.getId(), result.getId());
        verify(prontuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorId_NotFound() {
        when(prontuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> prontuarioService.buscarPorId(1L));
    }

    @Test
    void testAtualizar() {
        when(prontuarioRepository.findById(1L)).thenReturn(Optional.of(prontuario));
        when(prontuarioRepository.save(any(Prontuario.class))).thenReturn(prontuario);
        prontuarioDTO.setDiagnostico(updateRequest.getDiagnostico());
        when(modelMapper.map(prontuario, ProntuarioDTO.class)).thenReturn(prontuarioDTO);

        doAnswer(invocation -> {
            ProntuarioUpdateRequest request = invocation.getArgument(0);
            Prontuario entity = invocation.getArgument(1);
            entity.setDiagnostico(request.getDiagnostico());
            return null;
        }).when(modelMapper).map(updateRequest, prontuario);

        ProntuarioDTO result = prontuarioService.atualizar(1L, updateRequest);

        assertNotNull(result);
        assertEquals(updateRequest.getDiagnostico(), result.getDiagnostico());
        verify(prontuarioRepository, times(1)).save(prontuario);
    }

    @Test
    void testAtualizar_NotFound() {
        when(prontuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> prontuarioService.atualizar(1L, updateRequest));

        verify(prontuarioRepository, never()).save(any(Prontuario.class));
    }

    @Test
    void testDeletar() {
        when(prontuarioRepository.existsById(1L)).thenReturn(true);
        doNothing().when(prontuarioRepository).deleteById(1L);

        prontuarioService.deletar(1L);

        verify(prontuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletar_NotFound() {
        when(prontuarioRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> prontuarioService.deletar(1L));

        verify(prontuarioRepository, never()).deleteById(1L);
    }
}
