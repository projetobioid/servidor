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
 * @author daniel
 */
public class TOEndereco extends TOBase{
   
    private long cidade_idcidade;
    
    private String rua;
    
    private int gps_lat;
   
    private int gps_long;
    
    private int numero;
    
    private String bairro;
    
    private String complemento;
    
    private String cep;
    
    private String nomecidade;
    
    private String nomeestado;
    
    private String nomepais;
    
    public long getCidade_idcidade() {
        return cidade_idcidade;
    }

    public void setCidade_idcidade(long cidade_idcidade) {
        this.cidade_idcidade = cidade_idcidade;
    }
    
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getGps_lat() {
        return gps_lat;
    }

    public void setGps_lat(int gps_lat) {
        this.gps_lat = gps_lat;
    }

    public int getGps_long() {
        return gps_long;
    }

    public void setGps_long(int gps_long) {
        this.gps_long = gps_long;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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
   
    

    public TOEndereco() {
    }




    //retorna consulta do banco de dados tipo resultset
    public TOEndereco (ResultSet rs, String metodo) throws Exception{
        
        switch(metodo){
            default:
                this.cidade_idcidade = rs.getLong("cidade_idcidade");
                this.rua = rs.getString("rua");
                this.gps_lat = rs.getInt("gps_lat");
                this.gps_long = rs.getInt("gps_long");
                this.numero = rs.getInt("numero");
                this.bairro = rs.getString("bairro");
                this.complemento = rs.getString("complemento");
                this.cep = rs.getString("cep"); 
                break;
        }
        
    }

    
    @Override
    public JSONObject buscarJson(String metodo) throws Exception {
        //variavel tipo json para retornar no metodo
        JSONObject j = new JSONObject();
        
        switch(metodo){
            default:
                //populando o objeto j
                j.put("cidade_idcidade", cidade_idcidade);
                j.put("rua", rua);
                j.put("gps_lat", gps_lat);
                j.put("gps_long", gps_long);
                j.put("numero", numero);
                j.put("bairro", bairro);
                j.put("complemento", complemento);
                j.put("cep", cep);
                break;
        }
        
        
        return j;
    }
    
    
}
