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
import to.TOSafraImg;

/**
 *
 * @author daniel
 */
public class DAOSafraImg implements DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONArray listar(Connection c) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONArray listar(Connection c, TOBase t) throws Exception {
        JSONArray  ja = new JSONArray();
        
        String sql = "SELECT DISTINCT c.nomecultivar, c.imagem, c.descricao, c.valornutricional FROM login l "
                + "INNER JOIN pessoa p ON( p.idpessoa = l.pessoa_idpessoa) "
                + "INNER JOIN relacaopa r ON( r.agricultor_pessoa_idpessoa = p.idpessoa) "
                + "INNER JOIN propriedade pr ON (pr.idpropriedade = r.propriedade_idpropriedade) "
                + "INNER JOIN safra s ON (s.propriedade_idpropriedade = pr.idpropriedade) "
                + "INNER JOIN cultivar c ON (idcultivar = cultivar_idcultivar) "
                + "INNER JOIN safrarelatada sr ON (sr.safra_idsafra = s.idsafra) "
                + "INNER JOIN destinacao d ON (d.safrarelatada_idsafrarelatada = sr.idsafrarelatada) "
                + "where l.usuario = ?";
           
        
        
        ResultSet rs = null;
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            u.add(((TOSafraImg) t).getUsuario());
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOSafraImg ti = new TOSafraImg(rs);
                ja.put(ti.getJson());
            }
            
        }finally{
            rs.close();
        }
        return ja;
    }


    @Override
    public TOBase getLogin(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
