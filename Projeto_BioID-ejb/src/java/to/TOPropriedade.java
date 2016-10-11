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
    
    private TOEndereco endereco;

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
    

    public TOPropriedade listarPropriedadeEndereco(ResultSet rs) throws Exception{
        this.endereco = new TOEndereco();
        
        this.endereco.setNomecidade(rs.getString("nomecidade"));
        this.endereco.setRua(rs.getString("rua"));
        this.endereco.setNumero(rs.getInt("numero"));
        this.endereco.setBairro(rs.getString("bairro"));
        this.endereco.setCep(rs.getString("cep"));
        this.endereco.setComplemento(rs.getString("complemento"));
        this.endereco.setGps_lat(rs.getInt("gps_lat"));
        this.endereco.setGps_long(rs.getInt("gps_long"));
        this.nomepropriedade = rs.getString("nomepropriedade");
        this.idpropriedade = rs.getLong("idpropriedade");
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
        j.put("nomecidade", endereco.getNomecidade());
        j.put("rua", endereco.getRua());
        j.put("numero", endereco.getNumero());
        j.put("bairro", endereco.getBairro());
        j.put("cep", endereco.getCep());
        j.put("complemento", endereco.getComplemento());
        j.put("gps_lat", endereco.getGps_lat());
        j.put("gps_long", endereco.getGps_long());
        j.put("nomepropriedade", nomepropriedade);
        j.put("idpropriedade", idpropriedade);
        
        
        return j;
    }
    
    
}
