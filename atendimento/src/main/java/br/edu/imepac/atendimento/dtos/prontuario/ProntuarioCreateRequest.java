package br.edu.imepac.atendimento.dtos.prontuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProntuarioCreateRequest {
    @NotBlank(message = "A queixa principal é obrigatória.")
    private String queixaPrincipal;

    @NotBlank(message = "O diagnóstico é obrigatório.")
    private String diagnostico;

    private String historicoMedico;
    private String alergias;
    private String medicamentosEmUso;
    private String examesSolicitados;
    private String resultadosExames;
    private String observacoes;

    @NotNull(message = "O ID da consulta é obrigatório.")
    private Long consultaId;
}
