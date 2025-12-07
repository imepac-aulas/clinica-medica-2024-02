package br.edu.imepac.administrativo.service;


import br.edu.imepac.common.entidades.Convenio;
import br.edu.imepac.common.utils.StatusConvenioEnum;
import br.edu.imepac.administrativo.dtos.convenio.ConvenioCreateRequest;
import br.edu.imepac.administrativo.dtos.convenio.ConvenioDTO;
import br.edu.imepac.administrativo.dtos.convenio.ConvenioUpdateRequest;
import br.edu.imepac.administrativo.repository.ConvenioRepository;
import br.edu.imepac.administrativo.exceptions.ConvenioDuplicadoException;
import br.edu.imepac.administrativo.exceptions.ConvenioNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConvenioService {

    private final ConvenioRepository convenioRepository;

    @Transactional
    public ConvenioDTO criar(ConvenioCreateRequest request) {

        if (convenioRepository.existsByCnpj(request.getCnpj())) {
            throw new ConvenioDuplicadoException("Já existe convênio cadastrado com este CNPJ");
        }

        Convenio convenio = Convenio.builder()
                .nome(request.getNome())
                .cnpj(request.getCnpj())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .status(StatusConvenioEnum.ATIVO)
                .build();

        convenioRepository.save(convenio);

        return toDTO(convenio);
    }

    @Transactional
    public ConvenioDTO atualizar(Long id, ConvenioUpdateRequest request) {

        Convenio convenio = buscarEntidade(id);

        convenio.setNome(request.getNome());
        convenio.setEmail(request.getEmail());
        convenio.setTelefone(request.getTelefone());

        return toDTO(convenio);
    }

    @Transactional(readOnly = true)
    public ConvenioDTO buscarPorId(Long id) {
        return toDTO(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public List<ConvenioDTO> listarTodos() {
        return convenioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void inativar(Long id) {
        Convenio convenio = buscarEntidade(id);
        convenio.setStatus(StatusConvenioEnum.INATIVO);
    }

    @Transactional
    public void ativar(Long id) {
        Convenio convenio = buscarEntidade(id);
        convenio.setStatus(StatusConvenioEnum.ATIVO);
    }



    private Convenio buscarEntidade(Long id) {
        return convenioRepository.findById(id)
                .orElseThrow(() -> new ConvenioNaoEncontradoException("Convênio não encontrado"));
    }

    private ConvenioDTO toDTO(Convenio convenio) {
        ConvenioDTO dto = new ConvenioDTO();
        dto.setId(convenio.getId());
        dto.setNome(convenio.getNome());
        dto.setCnpj(convenio.getCnpj());
        dto.setEmail(convenio.getEmail());
        dto.setTelefone(convenio.getTelefone());
        return dto;
    }
}
