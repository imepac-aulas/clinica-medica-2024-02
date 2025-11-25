package br.edu.imepac.common.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Codigo_Funcionario")
    private Long id;

    @Column(name = "Nome_Completo", length = 50, nullable = false)
    private String nomeCompleto;

    @Column(name = "Numero_RG", length = 12)
    private String numeroRg;

    @Column(name = "Orgao_Emissor", length = 6)
    private String orgaoEmissor;

    @Column(name = "Numero_CPF", length = 14, unique = true, nullable = false)
    private String numeroCpf;

    @Column(name = "Endereco", length = 50)
    private String endereco;

    @Column(name = "Numero", length = 15)
    private String numero;

    @Column(name = "Complemento", length = 30)
    private String complemento;

    @Column(name = "Bairro", length = 40)
    private String bairro;

    @Column(name = "Cidade", length = 40)
    private String cidade;

    @Column(name = "Estado", length = 2)
    private String estado;

    @Column(name = "Telefone", length = 20)
    private String telefone;

    @Column(name = "Celular", length = 20)
    private String celular;

    @Column(name = "Numero_CTPS", length = 20)
    private String numeroCtps;

    @Column(name = "Numero_PIS", length = 20)
    private String numeroPis;

    @Column(name = "Data_Nascimento")
    private LocalDate dataNascimento;

    // Construtor padrão exigido pela JPA
    public Funcionario() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNumeroRg() {
        return numeroRg;
    }

    public void setNumeroRg(String numeroRg) {
        this.numeroRg = numeroRg;
    }

    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    public String getNumeroCpf() {
        return numeroCpf;
    }

    public void setNumeroCpf(String numeroCpf) {
        this.numeroCpf = numeroCpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNumeroCtps() {
        return numeroCtps;
    }

    public void setNumeroCtps(String numeroCtps) {
        this.numeroCtps = numeroCtps;
    }

    public String getNumeroPis() {
        return numeroPis;
    }

    public void setNumeroPis(String numeroPis) {
        this.numeroPis = numeroPis;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
},