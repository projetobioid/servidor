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
    
    private long unidademedida_idunidademedida;
    
    private double quantidade;
    
    private String data_io;
    
    private int operacao;
    
    private long login_idlogin;

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

    public long getUnidademedida_idunidademedida() {
        return unidademedida_idunidademedida;
    }

    public void setUnidademedida_idunidademedida(long unidademedida_idunidademedida) {
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
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

    public long getLogin_idlogin() {
        return login_idlogin;
    }

    public void setLogin_idlogin(long login_idlogin) {
        this.login_idlogin = login_idlogin;
    }

    
    
    public TOIOEstoque() {
    }

    

    
    public TOIOEstoque (ResultSet rs) throws Exception{
        this.unidade_idunidade = rs.getLong("unidade_idunidade");
        this.cultivar_idcultivar = rs.getLong("cultivar_idcultivar");
        this.unidademedida_idunidademedida = rs.getLong("unidademedida_idunidademedida");
        this.quantidade = rs.getFloat("quantidade");
        this.data_io = rs.getString("data_io");
        this.operacao = rs.getInt("operacao");
        this.login_idlogin = rs.getLong("login_idlogin");
        
    }

    @Override
    public JSONObject getJson() throws Exception {
        JSONObject j = new JSONObject();
        
        j.put("unidade_idunidade", unidade_idunidade);
        j.put("cultivar_idcultivar", cultivar_idcultivar);
        j.put("unidademedida_idunidademedida", unidademedida_idunidademedida);
        j.put("quantidade", quantidade);
        j.put("data_io", data_io);
        j.put("operacao", operacao);
        j.put("login_idlogin", login_idlogin);
        
        return j;
                
    }
    
    
}


