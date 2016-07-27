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

public class TOCultivar extends TOBase{
    //id do produto
    private long idcultivar;
    //nome do produto
    private String nomecultivar;
    
    private String imagem;
    //descricao do produto
    private String descricao;
    //tipo do produto se é bio ou não
    private boolean biofortificado;
    //tipo da distribuicao do cultivar
    private String unidademedida;

    private String valornutricional;
    //gets e sets

    public long getIdcultivar() {
        return idcultivar;
    }

    public void setIdcultivar(long idcultivar) {
        this.idcultivar = idcultivar;
    }

    public String getNomecultivar() {
        return nomecultivar;
    }

    public void setNomecultivar(String nomecultivar) {
        this.nomecultivar = nomecultivar;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isBiofortificado() {
        return biofortificado;
    }

    public void setBiofortificado(boolean biofortificado) {
        this.biofortificado = biofortificado;
    }

    public String getUnidademedida() {
        return unidademedida;
    }

    public void setUnidademedida(String unidademedida) {
        this.unidademedida = unidademedida;
    }

    public String getValornutricional() {
        return valornutricional;
    }

    public void setValornutricional(String valornutricional) {
        this.valornutricional = valornutricional;
    }
    
   
    

    //construtor vazio
    public TOCultivar() {
    }

    
    //retorna consulta do banco de dados tipo resultset
    public TOCultivar (ResultSet rs) throws Exception{
        this.idcultivar = rs.getLong("idcultivar");
        this.nomecultivar = rs.getString("nomecultivar");
        this.imagem = rs.getString("imagem");
        this.descricao = rs.getString("descricao");
        this.biofortificado = rs.getBoolean("biofortificado");
        this.unidademedida = rs.getString("unidademedida");
        this.valornutricional = rs.getString("valornutricional");
    }
    //classe sobrescrita de tobase
    @Override
    public JSONObject getJson() throws Exception {
        //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idcultivar", idcultivar);
        j.put("nomecultivar", nomecultivar);
        j.put("imagem", imagem);
        j.put("descricao", descricao);
        j.put("biofortificado", biofortificado);
        j.put("unidademedida", unidademedida);
        j.put("valornutricional", valornutricional);
        
        return j;
    }
    
    

    
    
}
