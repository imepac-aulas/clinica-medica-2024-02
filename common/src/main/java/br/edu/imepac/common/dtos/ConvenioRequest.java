package br.edu.imepac.common.dtos;

public class ConvenioRequest {

    private String NomeConvenio;

    private String EmpresaConvenio;

    public ConvenioRequest() {}

    public String getNomeConvenio() {
        return NomeConvenio;
    }
    public void setNomeConvenio(String NomeConvenio) {
        this.NomeConvenio = NomeConvenio;
    }
    public String getEmpresaConvenio() {
        return EmpresaConvenio;
    }
    public void setEmpresaConvenio(String EmpresaConvenio) {
        this.EmpresaConvenio = EmpresaConvenio;
    }
}