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
import to.TOBase;
import to.TOLogin;

/**
 *
 * @author daniel
 */
public class DAOLogin extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t, String metodo) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = null;
        
        
        //variavel sendo convertida para toUsuarios
        TOLogin to = (TOLogin)t;
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        switch(metodo){
            default:
            sql = "INSERT INTO login(pessoa_idpessoa, unidade_idunidade, usuario, senha, papel) VALUES (?, ?, ?, ?, ?)";
            u.add(to.getPessoa_idpessoa());
            u.add(to.getUnidade_idunidade());
            u.add(to.getUsuario());
            u.add(to.getSenha());
            u.add(to.getPapel());
            break;
        }
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
    }

   
    @Override
    public TOBase get(Connection c, TOBase t, String metodo) throws Exception {
         //string com o comando sql para editar o banco de dados
        String sql = null;
        ResultSet rs = null;
        
        try{
            //variavel sendo convertida para tologin
            TOLogin to = (TOLogin)t;
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            //testa o metodo a ser executado
            switch(metodo){
                case "get_usuario" :
                sql = "SELECT idlogin, usuario FROM login l WHERE usuario IN(?)";
                
                u.add(to.getUsuario());
                break;
                default :
                sql = "SELECT l.idlogin, l.pessoa_idpessoa, l.unidade_idunidade, l.usuario, l.papel, p.nome "
                    + "FROM login l "
                    + "INNER JOIN pessoa p ON(p.idpessoa = l.pessoa_idpessoa) "
                    + "WHERE usuario IN(?) AND senha IN(?)";
                
                u.add(to.getUsuario());
                u.add(to.getSenha());
                break;
            }
            
            
            
            rs = Data.executeQuery(c, sql, u);
            
            
            if(rs.next()){
                return new TOLogin(rs, metodo);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
        
    }

    @Override
    public void editar(Connection c, TOBase t, String metodo) throws Exception {
        String sql = null;
                
        
        TOLogin to = (TOLogin)t;
        
        List<Object> u = new ArrayList<Object>();
        
        switch(metodo){
            default:
                sql = "update login set sessao = ? where idlogin = ? ";
                u.add(to.getUsuario());
                u.add(to.getSenha());
            break;
        }
        

        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, u);
    }

   
}
