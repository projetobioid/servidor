/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

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

    public TOEndereco() {
    } 
    
}
