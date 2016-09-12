/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

import java.sql.ResultSet;
import org.json.JSONObject;

/**
 *
 * @author Aimee
 */
public class TOPropriedade extends TOBase {
    private long idpropriedade;
    
    private long endereco_idendereco;
    
    private long unidade_idunidade;
    
    private String nomepropriedade;
    
    private double area;
    
    private long unidadedemedida;
    
    private double areautilizavel;
    
    private long unidadedemedidaau;
    
    private String usuario;
    
    private String cpf;

    public long getIdpropriedade() {
        return idpropriedade;
    }

    public void setIdpropriedade(long idpropriedade) {
        this.idpropriedade = idpropriedade;
    }

    public long getEndereco_idendereco() {
        return endereco_idendereco;
    }

    public void setEndereco_idendereco(long endereco_idendereco) {
        this.endereco_idendereco = endereco_idendereco;
    }

    public long getUnidade_idunidade() {
        return unidade_idunidade;
    }

    public void setUnidade_idunidade(long unidade_idunidade) {
        this.unidade_idunidade = unidade_idunidade;
    }

    public String getNomepropriedade() {
        return nomepropriedade;
    }

    public void setNomepropriedade(String nomepropriedade) {
        this.nomepropriedade = nomepropriedade;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public long getUnidadedemedida() {
        return unidadedemedida;
    }

    public void setUnidadedemedida(long unidadedemedida) {
        this.unidadedemedida = unidadedemedida;
    }

    public double getAreautilizavel() {
        return areautilizavel;
    }

    public void setAreautilizavel(double areautilizavel) {
        this.areautilizavel = areautilizavel;
    }

    public long getUnidadedemedidaau() {
        return unidadedemedidaau;
    }

    public void setUnidadedemedidaau(long unidadedemedidaau) {
        this.unidadedemedidaau = unidadedemedidaau;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
    
    
    public TOPropriedade() {
    }

    public TOPropriedade(long idpropriedade, long endereco_idendereco, long unidade_idunidade, String nomepropriedade, double area, long unidadedemedida, double areautilizavel, long unidadedemedidaau, String usuario, String cpf) {
        this.idpropriedade = idpropriedade;
        this.endereco_idendereco = endereco_idendereco;
        this.unidade_idunidade = unidade_idunidade;
        this.nomepropriedade = nomepropriedade;
        this.area = area;
        this.unidadedemedida = unidadedemedida;
        this.areautilizavel = areautilizavel;
        this.unidadedemedidaau = unidadedemedidaau;
        this.usuario = usuario;
        this.cpf = cpf;
    }
    

    public TOPropriedade listarNome(ResultSet rs) throws Exception{
        this.nomepropriedade = rs.getString("nomepropriedade");
        return this;
    }
    
    public TOPropriedade(ResultSet rs) throws Exception{
        this.idpropriedade = rs.getLong("idpropriedade");
        this.endereco_idendereco = rs.getLong("endereco_idendereco");
        this.unidade_idunidade = rs.getLong("unidade_idunidade");
        this.nomepropriedade = rs.getString("nomepropriedade");
        this.area = rs.getDouble("area");
        this.unidadedemedida = rs.getLong("unidadedemedida");
        this.areautilizavel = rs.getDouble("areautilizavel");
        this.unidadedemedidaau = rs.getLong("unidadedemedidaau");
        
    }
    
    @Override
    public JSONObject getJson() throws Exception {
         //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idpropriedade", idpropriedade);
        j.put("endereco_idendereco", endereco_idendereco);
        j.put("unidade_idunidade", unidade_idunidade);
        j.put("nomepropriedade", nomepropriedade);
        j.put("area", area);
        j.put("unidadedemedida", unidadedemedida);
        j.put("areautilizavel", areautilizavel);
        j.put("unidadedemedidaau", unidadedemedidaau);
        
        return j;
    }

    @Override
    public JSONObject getJsonSimples() throws Exception {
                 //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("nomepropriedade", nomepropriedade);
        
        return j;
    }
    
    
}
