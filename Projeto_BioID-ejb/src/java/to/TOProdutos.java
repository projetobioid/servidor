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
    //descricao do produto
    private String descricao;
    //tipo do produto se é bio ou não
    private boolean biofortificado;
    //tipo da distribuicao do cultivar
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getBiofortificado() {
        return biofortificado;
    }

    public void setBiofortificado(boolean biofortificado) {
        this.biofortificado = biofortificado;
    }
    

    //construtor vazio
    public TOProdutos() {
    }

    
    //retorna consulta do banco de dados tipo resultset
    public TOProdutos (ResultSet rs) throws Exception{
        this.id = rs.getString("id");
        this.nome = rs.getString("nome");
        this.descricao = rs.getString("descricao");
        this.biofortificado = rs.getBoolean("biofortificado");
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
        j.put("descricao", descricao);
        j.put("biofortificado", biofortificado);
        j.put("tipo", tipo);
        
        return j;
    }
    
    

    
    
}
