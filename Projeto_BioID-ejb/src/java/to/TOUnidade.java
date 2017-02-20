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
    
    //amostragem
    private String nomecidade;
    private String nomeestado;
    private String nomepais;
    private TOEndereco endereco;
    


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

    public String getNomecidade() {
        return nomecidade;
    }

    public void setNomecidade(String nomecidade) {
        this.nomecidade = nomecidade;
    }

    public String getNomeestado() {
        return nomeestado;
    }

    public void setNomeestado(String nomeestado) {
        this.nomeestado = nomeestado;
    }

    public String getNomepais() {
        return nomepais;
    }

    public void setNomepais(String nomepais) {
        this.nomepais = nomepais;
    }
    
    
    

    //construtor vazio
    public TOUnidade() {
    }

    
    //retorna consulta do banco de dados tipo resultset
    public TOUnidade(ResultSet rs, String metodo) throws Exception{
        
        switch(metodo){
            case "get_unidade":
                this.endereco = new TOEndereco();
                this.idunidade = rs.getLong("idunidade");
                this.nomecidade = rs.getString("nomecidade");
                this.nomeestado = rs.getString("nomeestado");
                this.nomepais = rs.getString("nomepais");
                this.nomeunidade = rs.getString("nomeunidade");
                this.telefone1 = rs.getString("telefone1");
                this.telefone2 = rs.getString("telefone2");
                this.email = rs.getString("email");
                this.cnpj = rs.getString("cnpj");
                this.razao_social = rs.getString("razao_social");
                this.nome_fanta = rs.getString("nome_fanta");
                this.endereco.setRua(rs.getString("rua"));
                this.endereco.setBairro(rs.getString("bairro"));
                this.endereco.setComplemento(rs.getString("complemento"));
                this.endereco.setNumero(rs.getInt("numero"));
                this.endereco.setGps_lat(rs.getInt("gps_lat"));
                this.endereco.setGps_long(rs.getInt("gps_long"));
                break;
            case "todas":
            case "unidades":
                this.idunidade = rs.getLong("idunidade");
                this.nomecidade = rs.getString("nomecidade");
                this.nomeestado = rs.getString("nomeestado");
                this.nomepais = rs.getString("nomepais");
                this.nomeunidade = rs.getString("nomeunidade");
                this.telefone1 = rs.getString("telefone1");
                this.email = rs.getString("email");
                this.cnpj = rs.getString("cnpj");   
                break;
            default:
                this.idunidade = rs.getLong("idunidade");
                this.nomecidade = rs.getString("nomecidade");
                this.nomeestado = rs.getString("nomeestado");
                this.nomepais = rs.getString("nomepais");
                this.nomeunidade = rs.getString("nomeunidade");
                this.telefone1 = rs.getString("telefone1");
                this.telefone2 = rs.getString("telefone2");
                this.email = rs.getString("email");
                this.cnpj = rs.getString("cnpj");   
                break;
        }
        
    }


    //retorna um json
    @Override
    public JSONObject getJson(String metodo) throws Exception {
        //variavel tipo json para retornar no metodo
        JSONObject j = new JSONObject();
        
        switch(metodo){
            case "get_unidade":
                //populando o objeto j
                j.put("idunidade", idunidade);
                j.put("nomecidade", nomecidade);
                j.put("nomeestado", nomeestado);
                j.put("nomepais", nomepais);
                j.put("nomeunidade", nomeunidade);
                j.put("telefone1", telefone1);
                j.put("telefone2", telefone2);
                j.put("email", email);
                j.put("cnpj", cnpj);
                j.put("razao_social", razao_social);
                j.put("nome_fanta", nome_fanta);
                j.put("rua", endereco.getRua());
                j.put("bairro", endereco.getBairro());
                j.put("numero", endereco.getNumero());
                j.put("complemento", endereco.getComplemento());
                j.put("gps_lat", endereco.getGps_lat());
                j.put("gps_long", endereco.getGps_long());
                break;
            case "unidades":
            case "todas":
                //populando o objeto j
                j.put("idunidade", idunidade);
                j.put("nomecidade", nomecidade);
                j.put("nomeestado", nomeestado);
                j.put("nomepais", nomepais);
                j.put("nomeunidade", nomeunidade);
                j.put("telefone1", telefone1);
                j.put("email", email);
                j.put("cnpj", cnpj);
                break;
            default:
                //populando o objeto j
                j.put("idunidade", idunidade);
                j.put("nomecidade", nomecidade);
                j.put("nomeestado", nomeestado);
                j.put("nomepais", nomepais);
                j.put("nomeunidade", nomeunidade);
                j.put("telefone1", telefone1);
                j.put("telefone2", telefone2);
                j.put("email", email);
                j.put("cnpj", cnpj);
                break;
        }
      

        return j;
   
    }
    

    
    
}
