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
    public long inserir(Connection c, TOBase t) throws Exception {
        String sql = "INSERT INTO cultivar(nomecultivar, imagem, descricao, biofortificado, unidademedida, valornutricional) VALUES (?, ?, ?, ?, ?, ?)";
        
        TOCultivar to = (TOCultivar)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(to.getNomecultivar());
        p.add(to.getImagem());
        p.add(to.getDescricao());
        p.add(to.isBiofortificado());
        p.add(to.getUnidademedida());
        p.add(to.getValornutricional());
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }
    
    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        String sql = "update cultivar set nome = ?, descricao = ?, biofortificado = ?, tipo = ? where id = ? ";
        
        TOCultivar to = (TOCultivar)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(to.getNomecultivar());
        p.add(to.getDescricao());
        //p.add(to.getBiofortificado());
        //p.add(to.getTipo());
        //p.add(to.getIdCultivar());
       
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, p);
    }
    
    @Override
    public void excluir(Connection c, TOBase t) throws Exception {
        String sql = "delete from cultivar where id = ? ";
        
        TOCultivar to = (TOCultivar)t;
        
        List<Object> p = new ArrayList<Object>();
        
       // p.add(to.getIdCultivar());
        
        Data.executeUpdate(c, sql, p);
    } 

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        String sql = "select * from cultivar where LOWER(nomecultivar) = LOWER(?) and biofortificado = ?";
        
        ResultSet rs = null;
        List<Object> p = new ArrayList<Object>();
        
        
        
        try{
            TOCultivar to = (TOCultivar)t;
            p.add(to.getNomecultivar());
            p.add(to.isBiofortificado());
            
            rs = Data.executeQuery(c, sql, p);
            
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
        
        String sql = "select * from cultivar where biofortificado = true order by nomecultivar";
        
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

    @Override
    public JSONArray listar(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
