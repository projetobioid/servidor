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
        
        switch (metodo) {
            case "listarpais":
                this.id = rs.getLong("idpais");
                this.nome = rs.getString("nomepais");
                break;
            case "listarestados":
                this.id = rs.getLong("idestado");
                this.nome = rs.getString("nomeestado");
                break;
            case "listarcidades":
                this.id = rs.getLong("idcidade");
                this.nome = rs.getString("nomecidade");
                break;
            case "listarunidades":
                this.id = rs.getLong("idunidade");
                this.nome = rs.getString("nomeunidade");
                break;
            default:
                break;
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
        
        switch (metodo) {
            case "listarpais":
                //populando o objeto j
                j.put("idpais", id);
                j.put("nomepais", nome);
                break;
            case "listarestados":
                j.put("idestado", id);
                j.put("nomeestado", nome);
                break;
            case "listarcidades":
                j.put("idcidade", id);
                j.put("nomecidade", nome);
                break;
            case "listarunidades":
                j.put("idunidade", id);
                j.put("nomeunidade", nome);
                break;
            default:
                break;
        }
        
      
        
        return j;
    }
    
    
}
