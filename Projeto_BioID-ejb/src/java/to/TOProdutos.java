/*
Classe recebe os valores do banco de dados
 */
package to;

import java.sql.ResultSet;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */

public class TOProdutos extends TOBase{
    //id do produto
    private String id;
    //nome do produto
    private String nome;
    //tipo do produto se é bio ou não
    private String tipo;

    //gets e sets
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //construtor vazio
    public TOProdutos() {
    }

    
    //retorna consulta do banco de dados tipo resultset
    public TOProdutos (ResultSet rs) throws Exception{
        this.id = rs.getString("id");
        this.nome = rs.getString("nome");
        this.tipo = rs.getString("tipo");
        
    }
    //classe sobrescrita de tobase
    @Override
    public JSONObject getJson() throws Exception {
        //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("id", id);
        j.put("nome", nome);
        j.put("tipo", tipo);
        
        return j;
    }
    
    

    
    
}
