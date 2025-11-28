package br.edu.imepac.common.entidades;

public class Convenio {

    private Long codigoConvenio;      // Codigo_Convenio
    private String empresaConvenio;   // Empresa_Convenio
    private String cnpj;              // CNPJ
    private String telefone;          // Telefone

    public Convenio() {
    }

    public Convenio(Long codigoConvenio, String empresaConvenio, String cnpj, String telefone) {
        this.codigoConvenio = codigoConvenio;
        this.empresaConvenio = empresaConvenio;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    public Long getCodigoConvenio() {
        return codigoConvenio;
    }

    public void setCodigoConvenio(Long codigoConvenio) {
        this.codigoConvenio = codigoConvenio;
    }

    public String getEmpresaConvenio() {
        return empresaConvenio;
    }

    public void setEmpresaConvenio(String empresaConvenio) {
        this.empresaConvenio = empresaConvenio;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Convenio{" +
                "codigoConvenio=" + codigoConvenio +
                ", empresaConvenio='" + empresaConvenio + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}