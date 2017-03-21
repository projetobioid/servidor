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
public class TOIOEstoque extends TOBase{
    
    private long unidade_idunidade;
    
    private long cultivar_idcultivar;
        
    private double quantidade;
    
    private String data_io;
    
    private int operacao;
    
    private String login_usuario;

    public long getUnidade_idunidade() {
        return unidade_idunidade;
    }

    public void setUnidade_idunidade(long unidade_idunidade) {
        this.unidade_idunidade = unidade_idunidade;
    }

    public long getCultivar_idcultivar() {
        return cultivar_idcultivar;
    }

    public void setCultivar_idcultivar(long cultivar_idcultivar) {
        this.cultivar_idcultivar = cultivar_idcultivar;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getData_io() {
        return data_io;
    }

    public void setData_io(String data_io) {
        this.data_io = data_io;
    }

    public int getOperacao() {
        return operacao;
    }

    public void setOperacao(int operacao) {
        this.operacao = operacao;
    }

    public String getLogin_usuario() {
        return login_usuario;
    }

    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }

    
    
    public TOIOEstoque() {
    }

    

    
    public TOIOEstoque (ResultSet rs, String metodo) throws Exception{
        
        switch(metodo){
            default:
                this.unidade_idunidade = rs.getLong("unidade_idunidade");
                this.cultivar_idcultivar = rs.getLong("cultivar_idcultivar");
                this.quantidade = rs.getFloat("quantidade");
                this.data_io = rs.getString("data_io");
                this.operacao = rs.getInt("operacao");
                this.login_usuario = rs.getString("login_idlogin");
                break;
        }
    }

    @Override
    public JSONObject buscarJson(String metodo) throws Exception {
        JSONObject j = new JSONObject();
        
        switch(metodo){
            default:
                j.put("unidade_idunidade", unidade_idunidade);
                j.put("cultivar_idcultivar", cultivar_idcultivar);
                j.put("quantidade", quantidade);
                j.put("data_io", data_io);
                j.put("operacao", operacao);
                j.put("login_idlogin", login_usuario);
                break;
        }
        
        
        return j;
                
    }
    
    
}


