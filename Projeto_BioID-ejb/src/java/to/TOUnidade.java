/*
 classe recebe os valores do banco de dados de usuarios do sistema
 */
package to;

/**
 *
 * @author Daniel
 */
public class TOUnidade extends TOBase{
    
    private long idunidade;
    private long endereco_idendereco;
    private String nomeunidade;
    private String telefone1;
    private String telefone2;
    private String email;
    private String cnpj;
    private String razao_social;
    private String nome_fanta;

    public long getIdunidade() {
        return idunidade;
    }

    public void setIdunidade(long idunidade) {
        this.idunidade = idunidade;
    }

    public long getEndereco_idendereco() {
        return endereco_idendereco;
    }

    public void setEndereco_idendereco(long endereco_idendereco) {
        this.endereco_idendereco = endereco_idendereco;
    }

    public String getNomeunidade() {
        return nomeunidade;
    }

    public void setNomeunidade(String nomeunidade) {
        this.nomeunidade = nomeunidade;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getNome_fanta() {
        return nome_fanta;
    }

    public void setNome_fanta(String nome_fanta) {
        this.nome_fanta = nome_fanta;
    }

    //construtor vazio
    public TOUnidade() {
    }

}
