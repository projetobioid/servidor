/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import to.TOBase;
import to.TOSafra;

/**
 *
 * @author Aimee
 */
public class DAOSafra implements DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        String sql = "INSERT INTO safra(unidademedida_idunidademedida, propriedade_idpropriedade, cultivar_idcultivar, safra, datareceb, qtdrecebida) VALUES (?, ?, ?, ?, ?, ?)";
        
        TOSafra to = (TOSafra)t;
        List<Object> p = new ArrayList<Object>();
        
        p.add(to.getUnidademedida_idunidademedida());
        p.add(to.getPropriedade_idpropriedade());
        p.add(to.getCultivar_idcultivar());
        p.add(to.getSafra());
        p.add(to.getDatareceb());
        p.add(to.getQtdrecebida());
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }

    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        String sql = "UPDATE safra SET qtdconsumida=?, qtdreplatar=?, qtddestinada=?, destino=? WHERE propriedade_idpropriedade = ? and cultivar_idcultivar = ? and safra = ?";

        
        TOSafra to = (TOSafra)t;
        
        List<Object> p = new ArrayList<Object>();
        

        p.add(to.getPropriedade_idpropriedade());
        p.add(to.getCultivar_idcultivar());
        p.add(to.getSafra());
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, p);
        
    }

    @Override
    public void excluir(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        String sql = "select * from safra where LOWER(safra) = LOWER(?)";
        
        ResultSet rs = null;
        
        try{
            TOSafra to = (TOSafra)t;
            rs = Data.executeQuery(c, sql, to.getSafra());
            
            if(rs.next()){
                return new TOSafra(rs);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }

    @Override
    public JSONArray listar(Connection c) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TOBase getLogin(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONArray listar(Connection c, TOBase t) throws Exception {
        JSONArray  ja = new JSONArray();
        
        String sql = "SELECT s.idsafra, s.propriedade_idpropriedade, s.cultivar_idcultivar, s.safra, s.datareceb, s.qtdrecebida, uc.grandeza as grandeza_cultivar, d.descricao, us.grandeza as grandeza_safra, c.nomecultivar, pr.nomepropriedade "
                + "FROM login l "
                + "INNER JOIN pessoa p ON( p.idpessoa = l.pessoa_idpessoa) "
                + "INNER JOIN relacaopa r ON( r.agricultor_pessoa_idpessoa = p.idpessoa) "
                + "INNER JOIN propriedade pr ON (pr.idpropriedade = r.propriedade_idpropriedade) "
                + "INNER JOIN safra s ON (s.propriedade_idpropriedade = pr.idpropriedade) "
                + "INNER JOIN cultivar c ON (idcultivar = cultivar_idcultivar) "
                + "INNER JOIN safrarelatada sr ON (sr.safra_idsafra = s.idsafra ) "
                + "INNER JOIN destinacao d ON (d.iddestinacao = sr.destinacao_iddestinacao) "
                + "INNER JOIN unidademedida uc ON(uc.idunidademedida = c.unidademedida_idunidademedida) "
                + "INNER JOIN unidademedida us ON(us.idunidademedida = s.unidademedida_idunidademedida) "
                + "where l.usuario = ?";
           
        
        
        ResultSet rs = null;
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            u.add(((TOSafra) t).getUsuario());
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOSafra ts = new TOSafra(rs);
                ja.put(ts.getJson());
            }
            
                        
        }finally{
            rs.close();
        }
        return ja;
    }



    
}






