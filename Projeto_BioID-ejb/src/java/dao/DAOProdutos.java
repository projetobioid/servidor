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
import to.TOProdutos;

/**
 *
 * @author daniel
 */
@Stateless
public class DAOProdutos extends DAOBase {

    @Override
    public void inserir(Connection c, TOBase t) throws Exception {
        String sql = "insert into produtos (id, nome, tipo) values (?, ?, ?)";
        
        TOProdutos to = (TOProdutos)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(to.getId());
        p.add(to.getNome());
        p.add(to.getTipo());
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        Data.executeUpdate(c, sql, p);
    }
    
    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        String sql = "update produtos set nome = ?, tipo = ? where id = ? ";
        
        TOProdutos to = (TOProdutos)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(to.getNome());
        p.add(to.getTipo());
        p.add(to.getId());
       
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, p);
    }
    
    @Override
    public void excluir(Connection c, TOBase t) throws Exception {
        String sql = "delete from produtos where id = ? ";
        
        TOProdutos to = (TOProdutos)t;
        
        List<Object> p = new ArrayList<Object>();
        
        p.add(to.getId());
        
        Data.executeUpdate(c, sql, p);
    } 

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        String sql = "select id, nome, tipo from produtos where id = ? ";
        
        ResultSet rs = null;
        
        try{
            TOProdutos to = (TOProdutos)t;
            rs = Data.executeQuery(c, sql, to.getId());
            
            if(rs.next()){
                return new TOProdutos(rs);
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
        
        String sql = "select id, nome, tipo from produtos order by nome";
        
        ResultSet rs = null;
        
        try{
            rs = Data.executeQuery(c, sql);
            
            while (rs.next()){
                TOProdutos t = new TOProdutos(rs);
                ja.put(t.getJson());
            }
        }finally{
            rs.close();
        }
        return ja;
    }








    
}
