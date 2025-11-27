package br.edu.imepac.common.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType; //
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 * Entidade que representa uma Consulta Médica no sistema.
 * Esta classe é mapeada para uma tabela no banco de dados.
 */
@Entity // Indica que esta classe é uma entidade JPA (Java Persistence API)
@Table(name = "consultas") // Mapeia esta entidade para a tabela chamada 'consultas' no BD
public class Consulta {

    // --- CHAVE PRIMÁRIA ---
    @Id // Marca o campo 'id' como a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura o BD para auto-incrementar o ID
    private Long id;

    // --- CAMPOS DE RELACIONAMENTO ---
    /**
     * ID do paciente que será atendido na consulta.
     * (Em um projeto completo, isso seria um objeto Paciente)
     */
    private Long pacienteId;

    /**
     * ID do médico responsável por realizar a consulta.
     * (Em um projeto completo, isso seria um objeto Medico)
     */
    private Long medicoId;

    // --- DETALHES DA CONSULTA ---
    /**
     * Data e hora exatas de início da consulta.
     */
    private LocalDateTime dataHora;

    /**
     * O status atual da consulta (ex: Agendada, Concluída, Cancelada).
     */
    private String status;

    // --- CONSTRUTOR PADRÃO (Necessário para JPA) ---
    public Consulta() {
        // Construtor vazio exigido pelo Hibernate/JPA
    }

    // --- GETTERS E SETTERS (Métodos de Acesso) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Dica para quem for responsável por essa task: :)
    // Nota: Se estiver usando a biblioteca Lombok, você pode substituir todos
    // os Getters, Setters e o Construtor vazio pelas anotações:
    // @Data (ou @Getter e @Setter), @NoArgsConstructor e @AllArgsConstructor
}
