package br.edu.imepac.agendamento.services;

import br.edu.imepac.agendamento.dtos.convenio.ConvenioDTO;
import br.edu.imepac.agendamento.repositories.ConvenioRepository;
import br.edu.imepac.common.entidades.Convenio;
import br.edu.imepac.common.exceptions.ResourceNotFoundException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConvenioServiceTest {

    @Mock
    private ConvenioRepository convenioRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ConvenioService convenioService;

    private Convenio convenio;
    private ConvenioDTO convenioDTO;

    @BeforeEach
    void setUp() {
        convenio = new Convenio();
        convenio.setId(1L);
        convenio.setNome("Unimed");

        convenioDTO = new ConvenioDTO();
        convenioDTO.setId(1L);
        convenioDTO.setNome("Unimed");
    }

    @Test
    void testBuscarPorId() {
        when(convenioRepository.findById(1L)).thenReturn(Optional.of(convenio));
        when(modelMapper.map(convenio, ConvenioDTO.class)).thenReturn(convenioDTO);

        ConvenioDTO result = convenioService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(convenioDTO.getId(), result.getId());
        verify(convenioRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorId_NotFound() {
        when(convenioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> convenioService.buscarPorId(1L));

        verify(convenioRepository, times(1)).findById(1L);
    }

    @Test
    void testListarTodos() {
        when(convenioRepository.findAll()).thenReturn(Collections.singletonList(convenio));
        when(modelMapper.map(convenio, ConvenioDTO.class)).thenReturn(convenioDTO);

        List<ConvenioDTO> result = convenioService.listarTodos();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(convenioRepository, times(1)).findAll();
    }
}
