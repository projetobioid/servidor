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
import to.TOLogin;

/**
 *
 * @author Aimee
 */
public class DAOSafra extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        String sql = "INSERT INTO safra(statussafra_idstatussafra, unidademedida_idunidademedida, propriedade_idpropriedade, cultivar_idcultivar, safra,"
                + " datareceb, qtdrecebida, status_entrevistador) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        TOSafra to = (TOSafra)t;
        List<Object> p = new ArrayList<Object>();

        p.add(to.getStatussafra_idstatussafra());
        p.add(to.getUnidademedida_idunidademedida());
        p.add(to.getPropriedade_idpropriedade());
        p.add(to.getCultivar_idcultivar());
        p.add(to.getSafra());
        p.add(to.getDatareceb());
        p.add(to.getQtdrecebida());
        p.add(9);
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }

    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        String sql = "UPDATE safra SET qtdcolhida=?, ultimadatacolheita=?, statussafra_idstatussafra=? WHERE idsafra = ?";

        
        TOSafra to = (TOSafra)t;
        
        List<Object> p = new ArrayList<Object>();
        

        p.add(to.getQtdcolhida());
        p.add(to.getUltimadatacolheita());
        p.add(to.getStatussafra_idstatussafra());
        p.add(to.getIdsafra());
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, p);
        
    }

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        String sql = "select * from safra where idsafra IN(?)";
        
        ResultSet rs = null;
        
        try{
            TOSafra to = (TOSafra)t;
            rs = Data.executeQuery(c, sql, to.getIdsafra());
            
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
    public JSONArray listar(Connection c, TOBase t) throws Exception {
        JSONArray  ja = new JSONArray();
  
        ResultSet rs = null;
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            String sql = "SELECT s.idsafra, s.statussafra_idstatussafra, s.safra, s.datareceb, s.qtdrecebida,"
                + " (select SUM(d.qtddestinada) AS qtddestinada FROM destinacao d WHERE d.safra_idsafra IN(s.idsafra)),"
                + " um.grandeza as grandeza_recebida, s.qtdcolhida, c.nomecultivar, pr.nomepropriedade, c.tempodecolheita, c.tempodestinacao FROM login l"
                + " INNER JOIN pessoa p ON( p.idpessoa = l.pessoa_idpessoa)"
                + " INNER JOIN relacaopa r ON( r.agricultor_pessoa_idpessoa = p.idpessoa)"
                + " INNER JOIN propriedade pr ON (pr.idpropriedade = r.propriedade_idpropriedade)"
                + " INNER JOIN safra s ON (s.propriedade_idpropriedade = pr.idpropriedade)"
                + " INNER JOIN cultivar c ON (c.idcultivar = s.cultivar_idcultivar)"
                + " INNER JOIN unidademedida um ON(um.idunidademedida = s.unidademedida_idunidademedida) where l.pessoa_idpessoa IN(?) ORDER BY s.safra DESC";
             
            u.add(((TOLogin) t).getPessoa_idpessoa());
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOSafra ts = new TOSafra(rs);

                switch ((int)ts.getStatussafra_idstatussafra()) {
                    case 1:
                    case 2:
                    case 3:
                        ts.setPrazo_colheita(verificarPrazoColheita(ts));
                        ts.setPrazo_destinacao(verificarPrazoDestinacao(ts));
                        break;
                    case 4:
                    case 5:
                        ts.setPrazo_colheita("relatada");
                        ts.setPrazo_destinacao(verificarPrazoDestinacao(ts));
                        break;
               
                    case 6:
               
                        ts.setPrazo_colheita("relatada");
                        ts.setPrazo_destinacao("relatada");
                        break;
                    case 7:
                        ts.setPrazo_colheita("relatada");
                        ts.setPrazo_destinacao("expirada");
                        break;
                    case 8:
                        ts.setPrazo_colheita("expirada");
                        ts.setPrazo_destinacao("expirada");
                        break;
                    
                }
                
                
                ja.put(ts.getJson());
            }
            
                        
        }finally{
            rs.close();
        }
        return ja;
    }


    private String verificarPrazoColheita(TOSafra ts) {
        String teste = null;
        
               
        try{
            SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");

                Calendar datarecebimento = Calendar.getInstance();
                Calendar diaAtual = Calendar.getInstance();

                datarecebimento.setTime(formatar.parse(ts.getDatareceb()));

                datarecebimento.add(Calendar.DATE, +ts.getTempodecolheita());


                if(datarecebimento.getTimeInMillis() < diaAtual.getTimeInMillis()){
                    teste = "expirada";
                    //atualiza o status da safra
                    if(ts.getStatussafra_idstatussafra() == 1){
                        ts.setStatussafra_idstatussafra(8);  
                    }else if(ts.getStatussafra_idstatussafra() == 2){
                        ts.setStatussafra_idstatussafra(4);
                    }else if(ts.getStatussafra_idstatussafra() == 3){
                        ts.setStatussafra_idstatussafra(5);
                    }

                    BOFactory.editar(new DAOSafra(), ts);
                   
                }else{

                    teste = formatar.format(datarecebimento.getTime()); 
                }
        }catch(Exception e){
            teste = "Erro em verificar a safra colheita - "+ e;
        }

        return teste;  
    }

    private String verificarPrazoDestinacao(TOSafra ts) {
        String teste = null;

        try{
            SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        
            Calendar datarecebimento = Calendar.getInstance();
            Calendar diaAtual = Calendar.getInstance();

            datarecebimento.setTime(formatar.parse(ts.getDatareceb()));

            datarecebimento.add(Calendar.DATE, +ts.getTempodestinacao());



            if(datarecebimento.getTimeInMillis() < diaAtual.getTimeInMillis()){
                teste = "expirada";
                //atualiza o status da safra
                        
                if(ts.getStatussafra_idstatussafra() == 4){
                    ts.setStatussafra_idstatussafra(7);
                }else if(ts.getStatussafra_idstatussafra() == 5){
                    ts.setStatussafra_idstatussafra(6);
                }
                BOFactory.editar(new DAOSafra(), ts);
                ////
            }else{

                teste = formatar.format(datarecebimento.getTime());
            }
        }catch(Exception e){
            teste = "Erro em verificar a safra destinacao - "+ e;
        
        }
        return teste;
    }

//    @Override
//    public JSONArray backupentrevista(Connection c, TOBase t) throws Exception {
//        JSONArray  ja = new JSONArray();
//        
//        
//        String sql = "SELECT s.idsafra, s.safra, c.nomecultivar, s.qtdrecebida, s.propriedade_idpropriedade, um.grandeza as grandeza_recebida, s.datareceb FROM safra s INNER JOIN cultivar c ON(c.idcultivar = s.cultivar_idcultivar) INNER JOIN unidademedida um ON(um.idunidademedida = s.unidademedida_idunidademedida) WHERE s.propriedade_idpropriedade IN(?) AND s.status_entrevistador IN(9);";
//        ResultSet rs = null;
//        
//        try{
//            //variavel com lista dos parametros
//            List<Object> u = new ArrayList<Object>();
//            TOSafra to = (TOSafra)t;
//            u.add(to.getPropriedade_idpropriedade());
//            
//            rs = Data.executeQuery(c, sql, u);
//            while (rs.next()){
//                TOSafra ts = new TOSafra().backupentrevista(rs);
//                ja.put(ts.getJsonConsulta());
//            }
//        }finally{
//            rs.close();
//        }
//        return ja;
//    }
}






