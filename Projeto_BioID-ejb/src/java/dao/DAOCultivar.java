/*
classe de acesso ao banco de dados
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.json.JSONArray;
import to.TOBase;
import to.TOCultivar;

/**
 *
 * @author daniel
 */
@Stateless
public class DAOCultivar implements DAOBase {

    @Override
    public void inserir(Connection c, TOBase t) throws Exception {
        String sql = "insert into cultivar (id, nome, descricao, biofortificado, tipo) values (?, ?, ?, ?, ?)";
        
        TOCultivar to = (TOCultivar)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(to.getId());
        p.add(to.getNome());
        p.add(to.getDescricao());
        p.add(to.getBiofortificado());
        p.add(to.getTipo());
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        Data.executeUpdate(c, sql, p);
    }
    
    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        String sql = "update cultivar set nome = ?, descricao = ?, biofortificado = ?, tipo = ? where id = ? ";
        
        TOCultivar to = (TOCultivar)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(to.getNome());
        p.add(to.getDescricao());
        p.add(to.getBiofortificado());
        p.add(to.getTipo());
        p.add(to.getId());
       
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, p);
    }
    
    @Override
    public void excluir(Connection c, TOBase t) throws Exception {
        String sql = "delete from cultivar where id = ? ";
        
        TOCultivar to = (TOCultivar)t;
        
        List<Object> p = new ArrayList<Object>();
        
        p.add(to.getId());
        
        Data.executeUpdate(c, sql, p);
    } 

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        String sql = "select id, nome, descricao, biofortificado, tipo from cultivar where id = ? ";
        
        ResultSet rs = null;
        
        try{
            TOCultivar to = (TOCultivar)t;
            rs = Data.executeQuery(c, sql, to.getId());
            
            if(rs.next()){
                return new TOCultivar(rs);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }
    
    @Override
    public JSONArray listar(Connection c) throws Exception {
        JSONArray  ja = new JSONArray();
        
        String sql = "select id, nome, descricao, biofortificado, tipo from cultivar order by nome";
        
        ResultSet rs = null;
        
        try{
            rs = Data.executeQuery(c, sql);
            
            while (rs.next()){
                TOCultivar t = new TOCultivar(rs);
                ja.put(t.getJson());
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
