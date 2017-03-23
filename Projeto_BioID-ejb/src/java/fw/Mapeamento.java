/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fw;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author daniel
 */
public class Mapeamento {
    
    public static JSONArray MapeamentoJsonArray(ResultSet resultSet) throws SQLException, JSONException {
        if(resultSet == null)
                return null;

        JSONArray json = new JSONArray();

        do {                

            json.put(MapeamentoJson(resultSet));
        } while (resultSet.next());

        return json;
    }
    
    public static JSONObject MapeamentoJson(ResultSet resultSet) throws SQLException, JSONException {
        if(resultSet == null)
                return null;

        ResultSetMetaData metadata = resultSet.getMetaData();
        int numColumns = metadata.getColumnCount();


        JSONObject obj = new JSONObject();		
        for (int i = 1; i <= numColumns; ++i) 			
        {
                String column_name = metadata.getColumnName(i);
                obj.put(column_name, resultSet.getObject(column_name));
        }

        return obj;
    }
}
