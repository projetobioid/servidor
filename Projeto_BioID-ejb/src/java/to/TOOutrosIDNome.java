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
public class TOOutrosIDNome extends TOBase{
    
    private long id;
    
    private String nome;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TOOutrosIDNome() {
    }

    public TOOutrosIDNome(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }



    
    public TOOutrosIDNome (ResultSet rs, String metodo) throws Exception{
        JSONObject j = new JSONObject();
        
        if(metodo.equals("listarpais")){
            this.id = rs.getLong("idpais");
            this.nome = rs.getString("nomepais");
 
            
        }else if(metodo.equals("listarestados")){
            this.id = rs.getLong("idestado");
            this.nome = rs.getString("nomeestado");

        
        }else if(metodo.equals("listarcidades")){
            this.id = rs.getLong("idcidade");
            this.nome = rs.getString("nomecidade");


        }else if(metodo.equals("listarunidades")){
            this.id = rs.getLong("idunidade");
            this.nome = rs.getString("nomeunidade");


        }
        
      
    }
    
//    public TOOutrosIDNome (ResultSet rs, String metodo) throws Exception{
//        if(metodo.equals("listarPais")){
//            this.id = rs.getLong("idpais");
//            this.nome = rs.getString("nomePais");
// 
//            
//        }else if(metodo.equals("listarEstado")){
//            this.id = rs.getLong("idestado");
//            this.nome = rs.getString("nomeestado");
//
//        
//        }else if(metodo.equals("listarCidade")){
//            this.id = rs.getLong("idcidade");
//            this.nome = rs.getString("nomecidade");
//
//
//        }
//    }
    
    
    @Override
    public JSONObject getJson(String metodo) throws Exception {
        JSONObject j = new JSONObject();
        
        if(metodo.equals("listarpais")){
            //populando o objeto j
            j.put("idpais", id);
            j.put("nomepais", nome);   
        }else if(metodo.equals("listarestados")){
            j.put("idestado", id);
            j.put("nomeestado", nome);
        }else if(metodo.equals("listarcidades")){
            j.put("idcidade", id);
            j.put("nomecidade", nome);
        }else if(metodo.equals("listarunidades")){
            j.put("idunidade", id);
            j.put("nomeunidade", nome);
        }
        
      
        
        return j;
    }
    
    
}
