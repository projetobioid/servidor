/*
 classe recebe os valores do banco de dados de usuarios do sistema
 */
package to;

import java.sql.ResultSet;
import org.json.JSONObject;

/**
 *
 * @author daniel
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

    public TOUnidade(long idunidade, long endereco_idendereco, String nomeunidade, String telefone1, String telefone2, String email, String cnpj, String razao_social, String nome_fanta) {
        this.idunidade = idunidade;
        this.endereco_idendereco = endereco_idendereco;
        this.nomeunidade = nomeunidade;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.email = email;
        this.cnpj = cnpj;
        this.razao_social = razao_social;
        this.nome_fanta = nome_fanta;
    }
    
    
    //retorna consulta do banco de dados tipo resultset
    public TOUnidade (ResultSet rs) throws Exception{
        this.idunidade = rs.getLong("idunidade");
        this.endereco_idendereco = rs.getLong("endereco_idendereco");
        this.nomeunidade = rs.getString("nomeunidade");
        this.telefone1 = rs.getString("telefone1");
        this.telefone2 = rs.getString("telefone2");
        this.email = rs.getString("email");
        this.cnpj = rs.getString("cnpj");
        this.razao_social = rs.getString("razao_social");
        this.nome_fanta = rs.getString("email");
    }


    //retorna um json
    @Override
    public JSONObject getJson() throws Exception {
        //variavel tipo json para retornar no metodo
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idunidade", idunidade);
        j.put("endereco_idendereco", endereco_idendereco);
        j.put("nomeunidade", nomeunidade);
        j.put("telefone1", telefone1);
        j.put("telefone2", telefone2);
        j.put("email", email);
        j.put("cnpj", cnpj);
        j.put("razao_social", razao_social);
        j.put("nome_fanta", nome_fanta);
        
        return j;
   
    }
    

    
    
}
