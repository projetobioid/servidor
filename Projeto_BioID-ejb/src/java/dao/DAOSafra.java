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
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */
public class DAOSafra extends DAOBase{

    @Override
    public long inserir(Connection c, List<Object> u) throws Exception {

        String sql = "INSERT INTO safra(statussafra_idstatussafra, propriedade_idpropriedade, cultivar_idcultivar, safra,"
            + " datareceb, qtdrecebida, status_entrevistador) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
    }

    @Override
    public void editar(Connection c, List<Object> u) throws Exception {

        String sql = "UPDATE safra SET qtdcolhida=?, ultimadatacolheita=?, statussafra_idstatussafra=? WHERE idsafra = ?";

        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, u);
        
    }

    @Override
    public JSONObject buscar(Connection c, List<Object> u, String metodo) throws Exception {
        String sql = null;
        ResultSet rs = null;
        
        try{

            switch(metodo){
                case "GET_POR_IDSAFRA":
                    sql = "SELECT idsafra, cultivar_idcultivar, c.nomecultivar, u.grandeza, safra, datareceb, qtdrecebida, s.descricao_status, statussafra_idstatussafra, c.imagem "
                        + "FROM public.safra "
                        + "INNER JOIN cultivar c ON(idcultivar = cultivar_idcultivar) "
                        + "INNER JOIN unidademedida u ON(idunidademedida = c.unidademedida_idunidademedida) "
                        + "INNER JOIN statussafra s ON(s.idstatussafra = statussafra_idstatussafra) "
                        + "WHERE idsafra IN(?)";

                    break;
                default:
                    sql = "select * from safra where idsafra IN(?)";
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
            
            switch(metodo){
                case "CULTIVARES_RECEBIDOS":
                    sql = "SELECT idsafra, c.nomecultivar, safra, statussafra_idstatussafra "
                        + "FROM public.safra "
                        + "INNER JOIN cultivar c ON(idcultivar = cultivar_idcultivar) "
                        + "INNER JOIN unidademedida u ON(idunidademedida = c.unidademedida_idunidademedida) "
                        + "WHERE propriedade_idpropriedade IN(?)";

                    break;
                case "NAO_RELATADAS":
                    sql = "SELECT idsafra, cultivar_idcultivar, c.nomecultivar, u.grandeza, safra, datareceb, qtdrecebida, status_entrevistador "
                        + "FROM public.safra "
                        + "INNER JOIN cultivar c ON(idcultivar = cultivar_idcultivar) "
                        + "INNER JOIN unidademedida u ON(idunidademedida = c.unidademedida_idunidademedida) "
                        + "WHERE status_entrevistador IN(9) AND propriedade_idpropriedade IN(?)";

                    break;
                case "BACKUP_ENTREVISTA":
                    sql = "SELECT s.idsafra, s.safra, c.nomecultivar, s.qtdrecebida, s.propriedade_idpropriedade, s.datareceb, um.grandeza FROM safra s "
                        + "INNER JOIN cultivar c ON(c.idcultivar = s.cultivar_idcultivar) "
                        + "INNER JOIN unidademedida um ON(um.idunidademedida = c.unidademedida_idunidademedida) "
                        + "WHERE s.propriedade_idpropriedade IN(?) AND s.status_entrevistador IN(9)";
  
                    break;
                default:
                    sql = "SELECT s.idsafra, s.statussafra_idstatussafra, s.safra, s.datareceb, s.qtdrecebida,"
                        + " (select SUM(d.qtddestinada) AS qtddestinada FROM destinacao d WHERE d.safra_idsafra IN(s.idsafra)),"
                        + " um.grandeza as grandeza_recebida, s.qtdcolhida, c.nomecultivar, pr.nomepropriedade, c.tempodecolheita, c.tempodestinacao FROM login l"
                        + " INNER JOIN pessoa p ON( p.idpessoa = l.pessoa_idpessoa)"
                        + " INNER JOIN relacaopa r ON( r.agricultor_pessoa_idpessoa = p.idpessoa)"
                        + " INNER JOIN propriedade pr ON (pr.idpropriedade = r.propriedade_idpropriedade)"
                        + " INNER JOIN safra s ON (s.propriedade_idpropriedade = pr.idpropriedade)"
                        + " INNER JOIN cultivar c ON (c.idcultivar = s.cultivar_idcultivar)"
                        + " INNER JOIN unidademedida um ON(um.idunidademedida = s.unidademedida_idunidademedida) where l.pessoa_idpessoa IN(?) ORDER BY s.safra DESC";

                    break;
            }
            rs = Data.executeQuery(c, sql, u);
            
           

//                switch ((int)((TOSafra)t).getStatussafra_idstatussafra()) {
//                    case 1:
//                    case 2:
//                    case 3:
//                        ((TOSafra)t).setPrazo_colheita(verificarPrazoColheita(((TOSafra)t), metodo));
//                        ((TOSafra)t).setPrazo_destinacao(verificarPrazoDestinacao(((TOSafra)t), metodo));
//                        break;
//                    case 4:
//                    case 5:
//                        ((TOSafra)t).setPrazo_colheita("relatada");
//                        ((TOSafra)t).setPrazo_destinacao(verificarPrazoDestinacao(((TOSafra)t), metodo));
//                        break;
//               
//                    case 6:
//               
//                        ((TOSafra)t).setPrazo_colheita("relatada");
//                        ((TOSafra)t).setPrazo_destinacao("relatada");
//                        break;
//                    case 7:
//                        ((TOSafra)t).setPrazo_colheita("relatada");
//                        ((TOSafra)t).setPrazo_destinacao("expirada");
//                        break;
//                    case 8:
//                        ((TOSafra)t).setPrazo_colheita("expirada");
//                        ((TOSafra)t).setPrazo_destinacao("expirada");
//                        break;
//                    
//                }
                
                
//                ja.put(((TOSafra)t).getJson(metodo));
                

            if(rs.next()){
                return MapeamentoJsonArray(rs);
            }else{
                return null;
            }
                        
        }finally{
            rs.close();
        }
        
    }


//    private String verificarPrazoColheita(TOSafra ts, String metodo) {
//        String teste = null;
//        
//               
//        try{
//            SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
//
//                Calendar datarecebimento = Calendar.getInstance();
//                Calendar diaAtual = Calendar.getInstance();
//
//                datarecebimento.setTime(formatar.parse(ts.getDatareceb()));
//
//                datarecebimento.add(Calendar.DATE, +ts.getTempodecolheita());
//
//
//                if(datarecebimento.getTimeInMillis() < diaAtual.getTimeInMillis()){
//                    teste = "expirada";
//                    //atualiza o status da safra
//                    if(ts.getStatussafra_idstatussafra() == 1){
//                        ts.setStatussafra_idstatussafra(8);  
//                    }else if(ts.getStatussafra_idstatussafra() == 2){
//                        ts.setStatussafra_idstatussafra(4);
//                    }else if(ts.getStatussafra_idstatussafra() == 3){
//                        ts.setStatussafra_idstatussafra(5);
//                    }
//
//                    BOFactory.editar(new DAOSafra(), ts, metodo);
//                   
//                }else{
//
//                    teste = formatar.format(datarecebimento.getTime()); 
//                }
//        }catch(Exception e){
//            teste = "Erro em verificar a safra colheita - "+ e;
//        }
//
//        return teste;  
//    }
//
//    private String verificarPrazoDestinacao(TOSafra ts, String metodo) {
//        String teste = null;
//
//        try{
//            SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
//        
//            Calendar datarecebimento = Calendar.getInstance();
//            Calendar diaAtual = Calendar.getInstance();
//
//            datarecebimento.setTime(formatar.parse(ts.getDatareceb()));
//
//            datarecebimento.add(Calendar.DATE, +ts.getTempodestinacao());
//
//
//
//            if(datarecebimento.getTimeInMillis() < diaAtual.getTimeInMillis()){
//                teste = "expirada";
//                //atualiza o status da safra
//                        
//                if(ts.getStatussafra_idstatussafra() == 4){
//                    ts.setStatussafra_idstatussafra(7);
//                }else if(ts.getStatussafra_idstatussafra() == 5){
//                    ts.setStatussafra_idstatussafra(6);
//                }
//                BOFactory.editar(new DAOSafra(), ts, metodo);
//                ////
//            }else{
//
//                teste = formatar.format(datarecebimento.getTime());
//            }
//        }catch(Exception e){
//            teste = "Erro em verificar a safra destinacao - "+ e;
//        
//        }
//        return teste;
//    }

}






