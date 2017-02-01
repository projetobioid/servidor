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
import to.TOEstoque;
import to.TOLogin;

/**
 *
 * @author daniel
 */
@Stateless
public class DAOCultivar extends DAOBase {

    @Override
    public long inserir(Connection c, TOBase t, String metodo) throws Exception {
        String sql = "INSERT INTO cultivar(nomecultivar, imagem, descricao, biofortificado, unidademedida_idunidademedida, valornutricional, tempodecolheita, tempodestinacao, peso_saca) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        TOCultivar to = (TOCultivar)t;
        
        List<Object> p = new ArrayList<Object>();
        
        if(metodo.equals("inserir_cultivar")){
            p.add(to.getNomecultivar());
            p.add(to.getImagem());
            p.add(to.getDescricao());
            p.add(to.isBiofortificado());
            p.add(to.getUnidademedida_idunidademedida());
            p.add(to.getValornutricional());
            p.add(to.getTempodecolheita());
            p.add(to.getTempodestinacao());
            p.add(to.getPeso_saca()); 
        }
        
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }
    
    @Override
    public void editar(Connection c, TOBase t, String metodo) throws Exception {
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
    public void excluir(Connection c, TOBase t, String metodo) throws Exception {
        String sql = "delete from cultivar where id = ? ";
        
        TOCultivar to = (TOCultivar)t;
        
        List<Object> p = new ArrayList<Object>();
        
       // p.add(to.getIdCultivar());
        
        Data.executeUpdate(c, sql, p);
    } 

    @Override
    public TOBase get(Connection c, TOBase t, String metodo) throws Exception {
        String sql = null;
                
        ResultSet rs = null;
        List<Object> p = new ArrayList<Object>();
        
        
        
        try{
            
            switch(metodo){
                case "":
                    sql = "SELECT c.idcultivar, um.grandeza, c.nomecultivar, c.imagem, c.descricao, c.biofortificado, c.valornutricional, c.tempodecolheita, c.tempodestinacao, c.peso_saca "
                        + "FROM cultivar c INNER JOIN unidademedida um ON(um.idunidademedida = c.unidademedida_idunidademedida)"
                        + " where c.idcultivar IN(?)";
                break;
                
                default:
                    sql = "SELECT * FROM cultivar where idcultivar IN(?)";
                break;
                
            }
            TOCultivar to = (TOCultivar)t;
            p.add(to.getIdcultivar());
            
            rs = Data.executeQuery(c, sql, p);
            
            if(rs.next()){
                return new TOCultivar(rs, metodo);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }
    
    @Override
    public JSONArray listar(Connection c, String metodo) throws Exception {
        JSONArray  ja = new JSONArray();
        String sql = null;
        ResultSet rs = null;
        
        try{
        
            if(metodo.equals("listar_bio")){
                sql = "select c.idcultivar, c.nomecultivar, c.unidademedida_idunidademedida, um.grandeza from cultivar c "
                    + "INNER JOIN unidademedida um ON (um.idunidademedida = c.unidademedida_idunidademedida) "
                    + "order by c.nomecultivar WHERE c.bioforticado IN(true)";


            }else if(metodo.equals("listar_naobio")){
            
            }else if(metodo.equals("listar_recebidos")){
                
            }else if(metodo.equals("listar_estoqueunidade")){
                
            }

            rs = Data.executeQuery(c, sql);

            while (rs.next()){
                TOCultivar t = new TOCultivar(rs, metodo);
                ja.put(t.getJson(metodo));
            }
        }finally{
            rs.close();
        }
        return ja;
    }

    @Override
    public JSONArray listar(Connection c, TOBase t, String metodo) throws Exception {
        JSONArray  ja = new JSONArray();           
        ResultSet rs = null;
        
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            String sql = null;
            
            if(metodo.equals("")){
                sql = "SELECT DISTINCT c.idcultivar, c.nomecultivar, c.imagem, c.descricao, c.biofortificado, um.grandeza, c.valornutricional, c.tempodecolheita, c.peso_saca "
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
                    TOCultivar tc = new TOCultivar(rs, metodo);
                    ja.put(tc.getJson(metodo));
                }
            }else if(metodo.equals("listar_estoqueunidade")){
                sql = "SELECT e.cultivar_idcultivar, c.nomecultivar, e.quantidade, u.grandeza FROM estoque e "
                    + "INNER JOIN cultivar c ON (c.idcultivar = e.cultivar_idcultivar) "
                    + " INNER JOIN unidademedida u ON (u.idunidademedida = e.unidademedida_idunidademedida)"
                    + "WHERE e.unidade_idunidade IN(?)";
                u.add(((TOEstoque) t).getUnidade_idunidade());
                
                //busca no banco os resultados
                rs = Data.executeQuery(c, sql, u);
                //popula uma lista com os resultados
                while (rs.next()){
                    TOEstoque tc = new TOEstoque(rs, metodo);
                    ja.put(tc.getJson(metodo));
                }
            
            }else if(metodo.equals("listar_estoqueunidade_input")){
                sql = "SELECT e.cultivar_idcultivar, c.nomecultivar FROM estoque e "
                    + "INNER JOIN cultivar c ON (c.idcultivar = e.cultivar_idcultivar) "
                    + "WHERE e.unidade_idunidade IN(?)";
                u.add(((TOEstoque) t).getUnidade_idunidade());
                
                //busca no banco os resultados
                rs = Data.executeQuery(c, sql, u);
                //popula uma lista com os resultados
                while (rs.next()){
                    TOEstoque tc = new TOEstoque(rs, metodo);
                    ja.put(tc.getJson(metodo));
                }
            }
                
            
        }finally{
            rs.close();
        }
        return ja;
    }

}
