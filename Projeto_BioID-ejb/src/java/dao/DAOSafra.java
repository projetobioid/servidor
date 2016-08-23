/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bo.BOFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;
import to.TOBase;
import to.TOSafra;
import to.TOSafrarelatada;

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
        
        String sql = "SELECT s.idsafra, s.propriedade_idpropriedade, s.cultivar_idcultivar, s.safra, s.datareceb, s.qtdrecebida, um.grandeza as grandeza_safra, c.nomecultivar, pr.nomepropriedade, umc.grandeza as grandeza_cultivar, c.tempodecolheita "
                + "FROM login l "
                + "INNER JOIN pessoa p ON( p.idpessoa = l.pessoa_idpessoa) "
                + "INNER JOIN relacaopa r ON( r.agricultor_pessoa_idpessoa = p.idpessoa) "
                + "INNER JOIN propriedade pr ON (pr.idpropriedade = r.propriedade_idpropriedade) "
                + "INNER JOIN safra s ON (s.propriedade_idpropriedade = pr.idpropriedade) "
                + "INNER JOIN cultivar c ON (c.idcultivar = s.cultivar_idcultivar) "
                + "INNER JOIN unidademedida um ON(um.idunidademedida = s.unidademedida_idunidademedida) "
                + "INNER JOIN unidademedida umc ON(umc.idunidademedida = c.unidademedida_idunidademedida) where l.usuario = ?";
           
        
        
        ResultSet rs = null;
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            u.add(((TOSafra) t).getUsuario());
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOSafra ts = new TOSafra(rs);
                ts.setRelatada(verificarRelatar(ts.getIdsafra(), ts.getTempodecolheita(), ts.getDatareceb()));
                ja.put(ts.getJson());
            }
            
                        
        }finally{
            rs.close();
        }
        return ja;
    }



    private String verificarRelatar(long idsafra, int tempodecolheita, String datareceb) throws Exception{
        String teste = null;
        
        try{
            TOSafrarelatada t = new TOSafrarelatada();
        
            t.setSafra_idsafra(idsafra);
        
            JSONArray ja = BOFactory.listar(new DAOSafrarelatada(), t);
            
            
            if(ja.length() > 0){
                teste = "relatada";
            
            }else if(verificartempo(tempodecolheita, datareceb)){
                teste = "tempo expirado para relatar";
            }else{
                teste = "não relatada"; 
            }
        }catch(Exception e){
            teste = "Erro em verificar a safra relatada - "+ e;
        }
        
        return teste;
    }
    
    private boolean verificartempo(int n, String datareceb){
        
        boolean retorno;
        
        try{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Calendar datarecebimento = Calendar.getInstance();
        
        Calendar diaAtual = Calendar.getInstance();
        
        datarecebimento.setTime(format.parse(datareceb));
        
        datarecebimento.add(2, Calendar.DAY_OF_YEAR); //adicionando dois dias
        
        
        if(diaAtual == datarecebimento){
            retorno = true;
        }else{
            retorno = false;
        }
                
                /* Date entrada = new Date();
        
        String temp = datasaida.getText();
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");    
        Date dtsaida = new java.sql.Date(format.parse(temp).getTime());  
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
        Calendar c = Calendar.getInstance();
        c.setTime(dtsaida.getTime() - entrada.getTime()); 
        totaldiarias.setText( String.valueOf(c.get(Calendar.DAY_OF_MONTH)) );*/
        }catch(Exception e){
        
        }
        return true;
    }
}






