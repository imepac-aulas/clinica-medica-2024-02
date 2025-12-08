package br.edu.imepac.administrativo.services;

import br.edu.imepac.administrativo.dtos.convenio.ConvenioCreateRequest;
import br.edu.imepac.administrativo.dtos.convenio.ConvenioDTO;
import br.edu.imepac.administrativo.dtos.convenio.ConvenioUpdateRequest;
import br.edu.imepac.administrativo.exceptions.ResourceNotFoundException;
import br.edu.imepac.administrativo.repositories.ConvenioRepository;
import br.edu.imepac.common.entidades.Convenio;
import br.edu.imepac.common.utils.StatusConvenioEnum;
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
class ConvenioServiceTest {

    @Mock
    private ConvenioRepository convenioRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ConvenioService convenioService;

    private Convenio convenio;
    private ConvenioDTO convenioDTO;
    private ConvenioCreateRequest convenioCreateRequest;
    private ConvenioUpdateRequest convenioUpdateRequest;

    @BeforeEach
    void setUp() {
        convenio = new Convenio();
        convenio.setId(1L);
        convenio.setNome("Test Corp");
        convenio.setCnpj("12345678901234");
        convenio.setEmail("test@corp.com");
        convenio.setTelefone("1234567890");
        convenio.setStatus(StatusConvenioEnum.ATIVO);

        convenioDTO = new ConvenioDTO();
        convenioDTO.setId(1L);
        convenioDTO.setNome("Test Corp");
        convenioDTO.setCnpj("12345678901234");
        convenioDTO.setEmail("test@corp.com");
        convenioDTO.setTelefone("1234567890");

        convenioCreateRequest = new ConvenioCreateRequest();
        convenioCreateRequest.setNome("Test Corp");
        convenioCreateRequest.setCnpj("12345678901234");
        convenioCreateRequest.setEmail("test@corp.com");
        convenioCreateRequest.setTelefone("1234567890");

        convenioUpdateRequest = new ConvenioUpdateRequest();
        convenioUpdateRequest.setNome("Test Corp Updated");
        convenioUpdateRequest.setTelefone("0987654321");
    }

    @Test
    void testCreate() {
        when(modelMapper.map(convenioCreateRequest, Convenio.class)).thenReturn(convenio);
        when(convenioRepository.save(any(Convenio.class))).thenReturn(convenio);
        when(modelMapper.map(convenio, ConvenioDTO.class)).thenReturn(convenioDTO);

        ConvenioDTO result = convenioService.create(convenioCreateRequest);

        assertNotNull(result);
        assertEquals(convenioDTO.getId(), result.getId());
        assertEquals(convenioDTO.getNome(), result.getNome());
        verify(convenioRepository, times(1)).save(any(Convenio.class));
    }

    @Test
    void testFindAll() {
        when(convenioRepository.findAll()).thenReturn(Collections.singletonList(convenio));
        when(modelMapper.map(convenio, ConvenioDTO.class)).thenReturn(convenioDTO);

        List<ConvenioDTO> result = convenioService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(convenioDTO.getNome(), result.get(0).getNome());
        verify(convenioRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(convenioRepository.findById(1L)).thenReturn(Optional.of(convenio));
        when(modelMapper.map(convenio, ConvenioDTO.class)).thenReturn(convenioDTO);

        ConvenioDTO result = convenioService.findById(1L);

        assertNotNull(result);
        assertEquals(convenioDTO.getId(), result.getId());
        verify(convenioRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(convenioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            convenioService.findById(1L);
        });

        verify(convenioRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdate() {
        when(convenioRepository.findById(1L)).thenReturn(Optional.of(convenio));
        when(convenioRepository.save(any(Convenio.class))).thenReturn(convenio);

        // Configura o DTO de retorno do ModelMapper
        convenioDTO.setNome(convenioUpdateRequest.getNome());
        convenioDTO.setTelefone(convenioUpdateRequest.getTelefone());
        when(modelMapper.map(convenio, ConvenioDTO.class)).thenReturn(convenioDTO);

        // Simula a ação do ModelMapper de mapear os dados do request para a entidade
        doAnswer(invocation -> {
            ConvenioUpdateRequest request = invocation.getArgument(0);
            Convenio entity = invocation.getArgument(1);
            entity.setNome(request.getNome());
            entity.setTelefone(request.getTelefone());
            return null;
        }).when(modelMapper).map(convenioUpdateRequest, convenio);


        ConvenioDTO result = convenioService.update(1L, convenioUpdateRequest);

        assertNotNull(result);
        assertEquals(convenioDTO.getId(), result.getId());
        assertEquals("Test Corp Updated", result.getNome());
        assertEquals("0987654321", result.getTelefone());
        verify(convenioRepository, times(1)).findById(1L);
        verify(convenioRepository, times(1)).save(convenio);
    }

    @Test
    void testUpdate_NotFound() {
        when(convenioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            convenioService.update(1L, convenioUpdateRequest);
        });

        verify(convenioRepository, times(1)).findById(1L);
        verify(convenioRepository, never()).save(any(Convenio.class));
    }

    @Test
    void testDelete() {
        when(convenioRepository.findById(1L)).thenReturn(Optional.of(convenio));
        when(convenioRepository.save(convenio)).thenReturn(convenio);

        convenioService.delete(1L);

        assertEquals(StatusConvenioEnum.INATIVO, convenio.getStatus());
        verify(convenioRepository, times(1)).findById(1L);
        verify(convenioRepository, times(1)).save(convenio);
    }

    @Test
    void testDelete_NotFound() {
        when(convenioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            convenioService.delete(1L);
        });

        verify(convenioRepository, times(1)).findById(1L);
        verify(convenioRepository, never()).save(any(Convenio.class));
    }
}
