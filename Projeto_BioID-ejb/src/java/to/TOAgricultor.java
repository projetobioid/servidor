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
public class TOAgricultor extends TOBase{
    private long pessoa_idpessoa;
    
    private int qtdedeintegrantes;
    
    private int qtdedecriancas;
    
    private int qtdedegravida;

    public long getPessoa_idpessoa() {
        return pessoa_idpessoa;
    }

    public void setPessoa_idpessoa(long pessoa_idpessoa) {
        this.pessoa_idpessoa = pessoa_idpessoa;
    }

    public int getQtdedeintegrantes() {
        return qtdedeintegrantes;
    }

    public void setQtdedeintegrantes(int qtdedeintegrantes) {
        this.qtdedeintegrantes = qtdedeintegrantes;
    }

    public int getQtdedecriancas() {
        return qtdedecriancas;
    }

    public void setQtdedecriancas(int qtdedecriancas) {
        this.qtdedecriancas = qtdedecriancas;
    }

    public int getQtdedegravida() {
        return qtdedegravida;
    }

    public void setQtdedegravida(int qtdedegravida) {
        this.qtdedegravida = qtdedegravida;
    }

    public TOAgricultor() {
    }

    public TOAgricultor(long pessoa_idpessoa, int qtdedeintegrantes, int qtdedecriancas, int qtdedegravida) {
        this.pessoa_idpessoa = pessoa_idpessoa;
        this.qtdedeintegrantes = qtdedeintegrantes;
        this.qtdedecriancas = qtdedecriancas;
        this.qtdedegravida = qtdedegravida;
    }
    
    
    //retorna consulta do banco de dados tipo resultset
    public TOAgricultor (ResultSet rs) throws Exception{
        this.pessoa_idpessoa = rs.getLong("pessoa_idpessoa");
        this.qtdedeintegrantes = rs.getInt("qtdedeintegrantes");
        this.qtdedecriancas = rs.getInt("qtdedecriancas");
        this.qtdedegravida = rs.getInt("qtdedegravida");
    }
    //classe sobrescrita de tobase
    @Override
    public JSONObject getJson() throws Exception {
        //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("pessoa_idpessoa", pessoa_idpessoa);
        j.put("qtdedeintegrantes", qtdedeintegrantes);
        j.put("qtdedecriancas", qtdedecriancas);
        j.put("qtdedegravida", qtdedegravida);
        
        return j;
    }
}
