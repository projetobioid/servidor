/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import to.TOBase;
import to.TOOutrosIDNome;

/**
 *
 * @author daniel
 */
public class DAOOutrosIDNome extends DAOBase{

    @Override
    public JSONArray listar(Connection c, String metodo) throws Exception {
       JSONArray  ja = new JSONArray();
        String sql = null;
        ResultSet rs = null;
        
        try{
            
            if(metodo.equals("listarpais")){
                sql = "SELECT idpais, nomepais FROM pais ORDER BY nomepais ASC";
                
            }else if(metodo.equals("listarunidades")){
                sql = "SELECT idunidade, nomeunidade FROM unidade ORDER BY nomeunidade ASC";

            }    
            rs = Data.executeQuery(c, sql);
            

             while (rs.next()){
                TOOutrosIDNome tg = new TOOutrosIDNome(rs, metodo);
                ja.put(tg.getJson(metodo));
            }
                
            
            
        }finally{
            rs.close();
        }
        return ja;
    }

    @Override
    public JSONArray listar(Connection c, TOBase t, String metodo) throws Exception {
        JSONArray  ja = new JSONArray();
        String sql = null;
        ResultSet rs = null;
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            if(metodo.equals("listarestados")){
                sql = "SELECT idestado, nomeestado FROM estado WHERE pais_idpais IN(?) ORDER BY nomeestado ASC";

            }else if(metodo.equals("listarcidades")){
                sql = "SELECT idcidade, nomecidade FROM cidade WHERE estado_idestado IN(?) ORDER BY nomecidade ASC";
                
            }
            
            u.add(((TOOutrosIDNome) t).getId());
            rs = Data.executeQuery(c, sql, u);
            
            
        
            while (rs.next()){
                TOOutrosIDNome ts = new TOOutrosIDNome(rs, metodo);
                ja.put(ts.getJson(metodo));
            }
            
                        
        }finally{
            rs.close();
        }
        return ja;
    
    }
    
}
