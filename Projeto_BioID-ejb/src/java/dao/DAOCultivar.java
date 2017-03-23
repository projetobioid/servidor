/*
classe de acesso ao banco de dados
 */
package dao;

import fw.Data;
import static fw.Mapeamento.*;
import fw.RedimencionarImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOBase;
import to.TOCultivar;
import to.TOEstoque;
import to.TOLogin;

/**
 *
 * @author Daniel
 */

public class DAOCultivar extends DAOBase {

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        String sql = "INSERT INTO cultivar(imagem, nomecultivar, descricao, biofortificado, unidademedida_idunidademedida, valornutricional, tempodecolheita, tempodestinacao, peso_saca) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
       
        List<Object> u = new ArrayList<Object>();
        
         //chama a classe que redimenciona a imagem antes de atribuir ao TOProduto
        RedimencionarImage r = new RedimencionarImage();      
        u.add(r.redimensionaImg(((TOCultivar)t).getString_imagem()));
        u.add(((TOCultivar)t).getNomecultivar());
        u.add(((TOCultivar)t).getDescricao());
        u.add(((TOCultivar)t).isBiofortificado());
        u.add(((TOCultivar)t).getUnidademedida_idunidademedida());
        u.add(((TOCultivar)t).getValornutricional());
        u.add(((TOCultivar)t).getTempodecolheita());
        u.add(((TOCultivar)t).getTempodestinacao());
        u.add(((TOCultivar)t).getPeso_saca());
       
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
    }
    
    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        String sql = "update cultivar set nome = ?, descricao = ?, biofortificado = ?, tipo = ? where id = ? ";
        
        List<Object> p = new ArrayList<Object>();
        
        p.add(((TOCultivar)t).getNomecultivar());
        p.add(((TOCultivar)t).getDescricao());
       
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, p);
    }
    
    @Override
    public void excluir(Connection c, TOBase t) throws Exception {
        String sql = "delete from cultivar where id = ? ";

        List<Object> p = new ArrayList<Object>();
        
        Data.executeUpdate(c, sql, p);
    } 

    @Override
    public JSONObject buscar(Connection c, TOBase t, String metodo) throws Exception {
        
        String sql = null;
                
        ResultSet rs = null;
        
        List<Object> u = new ArrayList<Object>();
        
        try{
            
            switch(metodo){
                case "GET_CULTIVAR":
                    sql = "SELECT c.idcultivar, um.grandeza, c.nomecultivar, c.imagem, c.descricao, c.biofortificado, c.valornutricional, c.tempodecolheita, c.tempodestinacao, c.peso_saca "
                        + "FROM cultivar c INNER JOIN unidademedida um ON(um.idunidademedida = c.unidademedida_idunidademedida)"
                        + " WHERE c.idcultivar IN(?)";
                    u.add(((TOCultivar)t).getIdcultivar());
                break;
                case "GET_NOME":
                    sql = "SELECT * FROM cultivar where LOWER(nomecultivar) IN(?)";
                    u.add(((TOCultivar)t).getNomecultivar());
                    break;
                default:
                    sql = "SELECT * FROM cultivar where idcultivar IN(?)";
                    u.add(((TOCultivar)t).getIdcultivar());
                break;
                
            }
            
            rs = Data.executeQuery(c, sql, u);
            
            if(rs.next()){
                return MapeamentoJson(rs);
            }else{
                return null;
            }
            
        }finally{
            rs.close();
        }
    }
    

    @Override
    public JSONArray listar(Connection c, TOBase t, String metodo) throws Exception {         
        ResultSet rs = null;
        
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            String sql = null;
            
            switch (metodo) {
                case "TODOS":
                    sql = "select c.idcultivar, c.nomecultivar, um.grandeza from cultivar c "
                        + "INNER JOIN unidademedida um ON (um.idunidademedida = c.unidademedida_idunidademedida) "
                        + "order by c.nomecultivar";
                    
                    break;
//                case "bio":
//                    sql = "select c.idcultivar, c.nomecultivar, um.grandeza from cultivar c "
//                        + "INNER JOIN unidademedida um ON (um.idunidademedida = c.unidademedida_idunidademedida) "
//                        + "WHERE c.biofortificado IN(true) order by c.nomecultivar";
//                    rs = Data.executeQuery(c, sql);
//                    while (rs.next()){
//                        TOCultivar tc = new TOCultivar(rs, metodo);
//                        ja.put(tc.getJson(metodo));
//                    }
//                    break;
                case "x":
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
                    break;
                case "estoqueunidade":
                    sql = "SELECT e.cultivar_idcultivar, c.nomecultivar, e.quantidade, u.grandeza FROM estoque e "
                        + "INNER JOIN cultivar c ON (c.idcultivar = e.cultivar_idcultivar) "
                        + " INNER JOIN unidademedida u ON (u.idunidademedida = e.unidademedida_idunidademedida)"
                        + "WHERE e.unidade_idunidade IN(?)";
                    u.add(((TOEstoque) t).getUnidade_idunidade());
                    break;
                case "estoqueunidade_select":
                    sql = "SELECT e.cultivar_idcultivar, c.nomecultivar FROM estoque e "
                        + "INNER JOIN cultivar c ON (c.idcultivar = e.cultivar_idcultivar) "
                        + "WHERE e.unidade_idunidade IN(?)";
                    u.add(((TOEstoque) t).getUnidade_idunidade());
                    break;
                default:
                    break;
            }
            
            rs = Data.executeQuery(c, sql);
            
            if(rs.next()){
                return MapeamentoJsonArray(rs);
            }else{
                return null;
            }
            
        }finally{
            rs.close();
        }
    }

}
