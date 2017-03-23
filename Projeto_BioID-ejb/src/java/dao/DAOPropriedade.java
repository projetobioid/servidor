/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import static fw.Mapeamento.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOBase;
import to.TOLogin;
import to.TOPropriedade;

/**
 *
 * @author Daniel
 */
public class DAOPropriedade extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {

        List<Object> p = new ArrayList<Object>();
      
        String sql = "INSERT INTO propriedade(endereco_idendereco, unidade_idunidade, nomepropriedade, area, "
             + "unidadedemedida, areautilizavel, unidadedemedidaau) VALUES (?, ?, ?, ?, ?, ?, ?)";

         p.add(((TOPropriedade)t).getEndereco_idendereco());
         p.add(((TOPropriedade)t).getUnidade_idunidade());
         p.add(((TOPropriedade)t).getNomepropriedade());
         p.add(((TOPropriedade)t).getArea());
         p.add(((TOPropriedade)t).getUnidadedemedida());
         p.add(((TOPropriedade)t).getAreautilizavel());
         p.add(((TOPropriedade)t).getUnidadedemedidaau());
         
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);    
    }


    @Override
    public JSONObject buscar(Connection c, TOBase t, String metodo) throws Exception {

        ResultSet rs = null;
        List<Object> p = new ArrayList<Object>();
        
        try{
            String sql = null;
            
            switch(metodo){
                case "POR_IDPROPRIEDADE_BACKUP":
                    sql = "SELECT e.rua, e.gps_lat, e.gps_long, e.numero, e.bairro, e.complemento, e.cep, pr.unidade_idunidade, pr.nomepropriedade, "
                        + "pr.area, pr.unidadedemedida, pr.areautilizavel, pr.unidadedemedidaau, c.nomecidade, eS.nomeestado, p.nomepais, pr.idpropriedade FROM propriedade pr "
                        + "INNER JOIN endereco e ON(e.idendereco = pr.endereco_idendereco) "
                        + "INNER JOIN cidade c ON(c.idcidade = e.cidade_idcidade) "
                        + "INNER JOIN estado es ON(es.idestado = c.estado_idestado) "
                        + "INNER JOIN pais p ON(p.idpais = es.pais_idpais) "
                        + "WHERE pr.idpropriedade IN(?)";
                    
                    p.add(((TOPropriedade)t).getIdpropriedade());
                    break;
                default:
                    sql = "SELECT * FROM propriedade pr "
                        + "INNER JOIN relacaopa r ON(r.propriedade_idpropriedade = pr.idpropriedade)"
                        + "INNER JOIN pessoa p ON(p.idpessoa = r.agricultor_pessoa_idpessoa)"
                        + "WHERE LOWER(pr.nomepropriedade) = LOWER(?) and p.cpf = ?";
                    
                    p.add(((TOPropriedade)t).getNomepropriedade());
                    p.add(((TOPropriedade)t).getCpf());
                    break;
            }
                        
            rs = Data.executeQuery(c, sql, p);
            
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
            
            String sql = null;
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            switch(metodo){
                case "NOME_E_ID":
                    sql = "SELECT pr.nomepropriedade, pr.idpropriedade FROM propriedade pr "
                        + "INNER JOIN relacaopa rpa ON(rpa.propriedade_idpropriedade = pr.idpropriedade) "
                        + "INNER JOIN agricultor a ON(a.pessoa_idpessoa = rpa.agricultor_pessoa_idpessoa) "
                        + "INNER JOIN pessoa p ON(p.idpessoa = a.pessoa_idpessoa) "
                        + "INNER JOIN login l ON(l.pessoa_idpessoa = p.idpessoa) "
                        + "WHERE p.idpessoa IN(?) AND l.unidade_idunidade IN(?)";
                    u.add(((TOLogin)t).getPessoa_idpessoa());
                    u.add(((TOLogin)t).getUnidade_idunidade());
                    break;
                case "POR_USUARIO_E_IDUNIDADE":
                    sql = "SELECT pr.nomepropriedade, pr.idpropriedade FROM propriedade pr "
                        + "INNER JOIN relacaopa rpa ON(rpa.propriedade_idpropriedade = pr.idpropriedade) "
                        + "INNER JOIN agricultor a ON(a.pessoa_idpessoa = rpa.agricultor_pessoa_idpessoa) "
                        + "INNER JOIN pessoa p ON(p.idpessoa = a.pessoa_idpessoa) "
                        + "INNER JOIN login l ON(l.pessoa_idpessoa = p.idpessoa) "
                        + "WHERE l.usuario IN(?) AND l.unidade_idunidade IN(?)";
                    u.add(((TOLogin)t).getUsuario());
                    u.add(((TOLogin)t).getUnidade_idunidade());
                    break;
                case "POR_IDPESSOA":
                    sql = "SELECT pr.nomepropriedade, pr.idpropriedade, e.bairro, e.complemento, e.numero, "
                        + "(SELECT status_backup FROM backupentrevista be WHERE be.propriedade_idpropriedade IN(pr.idpropriedade)) FROM propriedade pr "
                        + "INNER JOIN relacaopa rpa ON(rpa.propriedade_idpropriedade = pr.idpropriedade) "
                        + "INNER JOIN agricultor a ON(a.pessoa_idpessoa = rpa.agricultor_pessoa_idpessoa) "
                        + "INNER JOIN pessoa p ON(p.idpessoa = a.pessoa_idpessoa) "
                        + "INNER JOIN login l ON(l.pessoa_idpessoa = p.idpessoa) "
                        + "INNER JOIN endereco e ON(e.idendereco = pr.endereco_idendereco) "
                        + "WHERE l.pessoa_idpessoa IN(?)";
                    u.add(((TOLogin)t).getPessoa_idpessoa());
                    break;
                default:
                    sql = "SELECT cdd.nomecidade, e.rua, e.numero, e.bairro, e.cep, e.complemento, e.gps_lat, e.gps_long, pr.nomepropriedade, pr.idpropriedade "
                        + "FROM propriedade pr INNER JOIN relacaopa rpa ON(pr.idPropriedade = rpa.propriedade_idpropriedade) "
                        + "INNER JOIN pessoa p ON(p.idpessoa = rpa.agricultor_pessoa_idpessoa) "
                        + "INNER JOIN endereco e ON(e.idendereco = pr.endereco_idendereco) "
                        + "INNER JOIN cidade cdd ON(cdd.idcidade = e.cidade_idcidade) "
                        + "WHERE p.idpessoa IN(?)";
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

    

}
