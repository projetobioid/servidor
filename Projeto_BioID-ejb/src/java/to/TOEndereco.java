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
    public int Cidade_idCidade;
    public String rua;
    public int gps_Lat;
    public int gps_Log;
    public int numero;
    public String bairro;
    public String complemento;
    public String cep;
    
    public int getCidade_idCidade() {
        return Cidade_idCidade;
    }

    public void setCidade_idCidade(int Cidade_idCidade) {
        this.Cidade_idCidade = Cidade_idCidade;
    }
    
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getGps_Lat() {
        return gps_Lat;
    }

    public void setGps_Lat(int gps_Lat) {
        this.gps_Lat = gps_Lat;
    }

    public int getGps_Log() {
        return gps_Log;
    }

    public void setGps_Log(int gps_Log) {
        this.gps_Log = gps_Log;
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

    public TOEndereco(String rua, int gps_Lat, int gps_Log, int numero, String bairro, String complemento, String cep, int Cidade_idCidade) {
        this.rua = rua;
        this.gps_Lat = gps_Lat;
        this.gps_Log = gps_Log;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cep = cep;
        this.Cidade_idCidade = Cidade_idCidade;
    }



    //retorna consulta do banco de dados tipo resultset
    public TOEndereco (ResultSet rs) throws Exception{
        this.Cidade_idCidade = rs.getInt("Cidade_idCidade");
        this.rua = rs.getString("rua");
        this.gps_Lat = rs.getInt("gps_Lat");
        this.gps_Log = rs.getInt("gps_Log");
        this.numero = rs.getInt("numero");
        this.bairro = rs.getString("bairro");
        this.complemento = rs.getString("complemento");
        this.cep = rs.getString("cep");
    }

    
    @Override
    public JSONObject getJson() throws Exception {
        //variavel tipo json para retornar no metodo
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("Cidade_idCidade", Cidade_idCidade);
        j.put("rua", rua);
        j.put("gps_Lat", gps_Lat);
        j.put("gps_Log", gps_Log);
        j.put("numero", numero);
        j.put("bairro", bairro);
        j.put("complemento", complemento);
        j.put("cep", cep);
        
        return j;
    }
    
    
}
