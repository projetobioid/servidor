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
import to.TOLogin;

/**
 *
 * @author daniel
 */
@Stateless
public class DAOCultivar extends DAOBase {

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        String sql = "INSERT INTO cultivar(nomecultivar, imagem, descricao, biofortificado, unidademedida, valornutricional) VALUES (?, ?, ?, ?, ?, ?)";
        
        TOCultivar to = (TOCultivar)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(to.getNomecultivar());
        p.add(to.getImagem());
        p.add(to.getDescricao());
        p.add(to.isBiofortificado());
        p.add(to.getUnidademedida_idunidademedida());
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
        String sql = "SELECT c.idcultivar, um.grandeza, c.nomecultivar, c.imagem, c.descricao, c.biofortificado, c.valornutricional, c.tempodecolheita, c.peso_saca "
                + "FROM cultivar c INNER JOIN unidademedida um ON(um.idunidademedida = c.unidademedida_idunidademedida)"
                + " where LOWER(c.nomecultivar) = LOWER(?) and biofortificado = ?";
        
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
    public JSONArray listar(Connection c, TOBase t) throws Exception {
        JSONArray  ja = new JSONArray();           
        
        
        ResultSet rs = null;
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            String sql = "SELECT DISTINCT c.idcultivar, c.nomecultivar, c.imagem, c.descricao, c.biofortificado, um.grandeza, c.valornutricional, c.tempodecolheita, c.peso_saca "
                + "FROM login l "
                + "INNER JOIN pessoa p ON( p.idpessoa = l.pessoa_idpessoa) "
                + "INNER JOIN relacaopa r ON( r.agricultor_pessoa_idpessoa = p.idpessoa) "
                + "INNER JOIN propriedade pr ON (pr.idpropriedade = r.propriedade_idpropriedade) "
                + "INNER JOIN safra s ON (s.propriedade_idpropriedade = pr.idpropriedade) "
                + "INNER JOIN cultivar c ON (idcultivar = cultivar_idcultivar) "
                + "INNER JOIN unidademedida um ON (idunidademedida = c.unidademedida_idunidademedida) "
                + "where l.pessoa_idpessoa = ?";
            
            u.add(((TOLogin) t).getPessoa_idpessoa());
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOCultivar tc = new TOCultivar(rs);
                ja.put(tc.getJson());
            }
            
        }finally{
            rs.close();
        }
        return ja;
    }

}
