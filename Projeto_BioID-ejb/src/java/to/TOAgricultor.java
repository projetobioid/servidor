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
public class TOAgricultor extends TOBase{
    //atributos da tabela do banco de dados
    public int id;
    public int usuario;
    public int propriedade;
    public int qnt_integrantes;
    public int qnt_criancas;
    public int qnt_gravidas_am;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(int propriedade) {
        this.propriedade = propriedade;
    }

    public int getQnt_integrantes() {
        return qnt_integrantes;
    }

    public void setQnt_integrantes(int qnt_integrantes) {
        this.qnt_integrantes = qnt_integrantes;
    }

    public int getQnt_criancas() {
        return qnt_criancas;
    }

    public void setQnt_criancas(int qnt_criancas) {
        this.qnt_criancas = qnt_criancas;
    }

    public int getQnt_gravidas_am() {
        return qnt_gravidas_am;
    }

    public void setQnt_gravidas_am(int qnt_gravidas_am) {
        this.qnt_gravidas_am = qnt_gravidas_am;
    }

    public TOAgricultor() {
    }
    
    public TOAgricultor(ResultSet rs) throws Exception{
        this.id = rs.getInt("id");
        this.usuario = rs.getInt("usuario");
        this.propriedade = rs.getInt("propriedade");
        this.qnt_integrantes = rs.getInt("qnt_integrantes");
        this.qnt_criancas = rs.getInt("qnt_criancas");
        this.qnt_gravidas_am = rs.getInt("qnt_gravidas_am");
       
    }

    @Override
    public JSONObject getJson() throws Exception {
        //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("id", id);
        j.put("usuario", usuario);
        j.put("propriedade", propriedade);
        j.put("qnt_integrantes", qnt_integrantes);
        j.put("qnt_criancas", qnt_criancas);
        j.put("qnt_gravidas_am", qnt_gravidas_am);
        
        return j;
    }
    
    
}
