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
    
    private long idcultivar;
    
    private String nomecultivar;
    
    private String imagem;
    
    private String descricao;
    
    private boolean biofortificado;
    
    private long unidademedida_idunidademedida;
    
    private String grandeza;

    private String valornutricional;
    
    private int tempodecolheita;
    
    private int tempodestinacao;
    
    private double peso_saca;
    
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

    public String getGrandeza() {
        return grandeza;
    }

    public void setGrandeza(String grandeza) {
        this.grandeza = grandeza;
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

    public double getPeso_saca() {
        return peso_saca;
    }

    public void setPeso_saca(double peso_saca) {
        this.peso_saca = peso_saca;
    }

    public int getTempodestinacao() {
        return tempodestinacao;
    }

    public void setTempodestinacao(int tempodestinacao) {
        this.tempodestinacao = tempodestinacao;
    }
    
    
    
    //construtor vazio
    public TOCultivar() {
    }

    public TOCultivar(long idcultivar, String nomecultivar, String imagem, String descricao, boolean biofortificado, long unidademedida_idunidademedida, String grandeza, String valornutricional, int tempodecolheita, float peso_saca, String usuario) {
        this.idcultivar = idcultivar;
        this.nomecultivar = nomecultivar;
        this.imagem = imagem;
        this.descricao = descricao;
        this.biofortificado = biofortificado;
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
        this.grandeza = grandeza;
        this.valornutricional = valornutricional;
        this.tempodecolheita = tempodecolheita;
        this.peso_saca = peso_saca;
        this.usuario = usuario;
    }

    

    
    //retorna consulta do banco de dados tipo resultset
    public TOCultivar (ResultSet rs , String metodo) throws Exception{
        if(metodo.equals("buscarcultivar")){
            this.idcultivar = rs.getLong("idcultivar");
            this.nomecultivar = rs.getString("nomecultivar");
            this.imagem = rs.getString("imagem");
            this.descricao = rs.getString("descricao");
            this.biofortificado = rs.getBoolean("biofortificado");
            this.grandeza = rs.getString("grandeza");
            this.valornutricional = rs.getString("valornutricional");
            this.tempodecolheita = rs.getInt("tempodecolheita");
            this.tempodestinacao = rs.getInt("tempodestinacao");
            this.peso_saca = rs.getInt("peso_saca");
            
        }else if(metodo.equals("listarcultivares")){
            this.idcultivar = rs.getLong("idcultivar");
            this.nomecultivar = rs.getString("nomecultivar");
            this.grandeza = rs.getString("grandeza");

        }
    }
    //classe sobrescrita de tobase
    @Override
    public JSONObject getJson(String metodo) throws Exception {
        //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        if(metodo.equals("buscarcultivar")){
            //populando o objeto j
            j.put("idcultivar", idcultivar);
            j.put("nomecultivar", nomecultivar);
            j.put("imagem", imagem);
            j.put("descricao", descricao);
            j.put("biofortificado", biofortificado);
            j.put("grandeza", grandeza);
            j.put("valornutricional", valornutricional);
            j.put("tempodecolheita", tempodecolheita);
            j.put("tempodestinacao", tempodestinacao);
            j.put("peso_saca", peso_saca);
        }else  if(metodo.equals("listarcultivares")){
            //populando o objeto j
            j.put("idcultivar", idcultivar);
            j.put("nomecultivar", nomecultivar);
            j.put("grandeza", grandeza);
        }
        
        return j;
    }    
    

    
    
}
