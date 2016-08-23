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
    private long unidademedida_idunidademedida;
    
    private String um_descricao;

    private String valornutricional;
    
    private int tempodecolheita;
    
    //para pesquisa no banco
    private String usuario;
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

    public long getUnidademedida_idunidademedida() {
        return unidademedida_idunidademedida;
    }

    public void setUnidademedida_idunidademedida(long unidademedida_idunidademedida) {
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
    }

    public String getUm_descricao() {
        return um_descricao;
    }

    public void setUm_descricao(String um_descricao) {
        this.um_descricao = um_descricao;
    }

    public String getValornutricional() {
        return valornutricional;
    }

    public void setValornutricional(String valornutricional) {
        this.valornutricional = valornutricional;
    }

    public int getTempodecolheita() {
        return tempodecolheita;
    }

    public void setTempodecolheita(int tempodecolheita) {
        this.tempodecolheita = tempodecolheita;
    }

    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    //construtor vazio
    public TOCultivar() {
    }

    public TOCultivar(long idcultivar, String nomecultivar, String imagem, String descricao, boolean biofortificado, long unidademedida_idunidademedida, String um_descricao, String valornutricional, int tempodecolheita, String usuario) {
        this.idcultivar = idcultivar;
        this.nomecultivar = nomecultivar;
        this.imagem = imagem;
        this.descricao = descricao;
        this.biofortificado = biofortificado;
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
        this.um_descricao = um_descricao;
        this.valornutricional = valornutricional;
        this.tempodecolheita = tempodecolheita;
        this.usuario = usuario;
    }

    
    //retorna consulta do banco de dados tipo resultset
    public TOCultivar (ResultSet rs) throws Exception{
        this.idcultivar = rs.getLong("idcultivar");
        this.nomecultivar = rs.getString("nomecultivar");
        this.imagem = rs.getString("imagem");
        this.descricao = rs.getString("descricao");
        this.biofortificado = rs.getBoolean("biofortificado");
        this.um_descricao = rs.getString("um_descricao");
        this.valornutricional = rs.getString("valornutricional");
        this.tempodecolheita = rs.getInt("tempodecolheita");
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
        j.put("um_descricao", um_descricao);
        j.put("valornutricional", valornutricional);
        j.put("tempodecolheita", tempodecolheita);
        
        return j;
    }
    
    

    
    
}
