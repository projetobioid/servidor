/*
classe de acesso ao banco de dados
 */
package dao;

import fw.Data;
import static fw.Mapeamento.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */

public class DAOCultivar extends DAOBase {

    @Override
    public long inserir(Connection c, List<Object> u) throws Exception {
        String sql = "INSERT INTO cultivar(imagem, nomecultivar, descricao, biofortificado, unidademedida_idunidademedida, valornutricional, tempodecolheita, tempodestinacao, peso_saca) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
    }
    
    @Override
    public void editar(Connection c, List<Object> u) throws Exception {
        String sql = "update cultivar set nome = ?, descricao = ?, biofortificado = ?, tipo = ? where id = ? ";

        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, u);
    }
    
    @Override
    public void excluir(Connection c, List<Object> u) throws Exception {
        String sql = "delete from cultivar where id = ? ";

        Data.executeUpdate(c, sql, u);
    } 

    @Override
    public JSONObject buscar(Connection c, List<Object> u, String metodo) throws Exception {
        
        String sql = null;
                
        ResultSet rs = null;
        
        
        try{
            
            switch(metodo){
                case "GET_CULTIVAR":
                    sql = "SELECT c.idcultivar, um.grandeza, c.nomecultivar, c.imagem, c.descricao, c.biofortificado, c.valornutricional, c.tempodecolheita, c.tempodestinacao, c.peso_saca "
                        + "FROM cultivar c INNER JOIN unidademedida um ON(um.idunidademedida = c.unidademedida_idunidademedida)"
                        + " WHERE c.idcultivar IN(?)";
                    
                break;
                case "GET_NOME":
                    sql = "SELECT * FROM cultivar where LOWER(nomecultivar) IN(LOWER(?))";
                  
                    break;
                default:
                    sql = "SELECT * FROM cultivar where idcultivar IN(?)";
              
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
    public JSONArray listar(Connection c, List<Object> u, String metodo) throws Exception {         
        ResultSet rs = null;
        
        try{
            String sql = null;
            
            switch (metodo) {
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
                    break;
                case "estoqueunidade":
                    sql = "SELECT e.cultivar_idcultivar, c.nomecultivar, e.quantidade, u.grandeza FROM estoque e "
                        + "INNER JOIN cultivar c ON (c.idcultivar = e.cultivar_idcultivar) "
                        + " INNER JOIN unidademedida u ON (u.idunidademedida = e.unidademedida_idunidademedida)"
                        + "WHERE e.unidade_idunidade IN(?)";
                    break;
                case "estoqueunidade_select":
                    sql = "SELECT e.cultivar_idcultivar, c.nomecultivar FROM estoque e "
                        + "INNER JOIN cultivar c ON (c.idcultivar = e.cultivar_idcultivar) "
                        + "WHERE e.unidade_idunidade IN(?)";
                    break;
                default:
                    break;
            }
            
            rs = Data.executeQuery(c, sql, u);
            
            if(rs.next()){
                return MapeamentoJsonArray(rs);
            }else{
                return null;
            }
            
        }finally{
            rs.close();
        }
    }
    
    @Override
    public JSONArray listar(Connection c, String metodo) throws Exception {         
        ResultSet rs = null;
        
        try{
            String sql = null;
            
            switch (metodo) {
                case "TODOS":
                    sql = "select c.idcultivar, c.nomecultivar, um.grandeza from cultivar c "
                        + "INNER JOIN unidademedida um ON (um.idunidademedida = c.unidademedida_idunidademedida) "
                        + "order by c.nomecultivar";
                    
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
