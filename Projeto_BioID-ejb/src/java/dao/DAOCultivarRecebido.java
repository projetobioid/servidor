/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import javax.ejb.Stateless;
import org.json.JSONArray;
import to.TOBase;
import to.TOCultivarRecebido;

/**
 *
 * @author daniel
 */
@Stateless
public class DAOCultivarRecebido extends DAOBase{

    @Override
    public JSONArray listar(Connection c) throws Exception {
        JSONArray  ja = new JSONArray();
        
        String sql = "select quantidade, data_recebimento, grandeza, cultivar.nome, relatado, cultivar.imgcultivar from cultivarrecebido join cultivar on cultivar.id = cultivarrecebido.cultivar";
        
        ResultSet rs = null;
        
        try{
            rs = Data.executeQuery(c, sql);
            
            while (rs.next()){
                TOCultivarRecebido t = new TOCultivarRecebido(rs);
                ja.put(t.getJson());
            }
        }finally{
            rs.close();
        }
        return ja;
    }

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        return super.get(c, t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Connection c, TOBase t) throws Exception {
        super.excluir(c, t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        super.editar(c, t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inserir(Connection c, TOBase t) throws Exception {
        super.inserir(c, t); //To change body of generated methods, choose Tools | Templates.
    }
    
}
