/*
Classe base que da suporte a qualquer classe herdada dela

 */
package to;
import org.json.JSONObject;
/**
 *
 * @author aimee
 */
public class TOBase {
    
    //metodo abstrato que será sobrescrito nas classes herdadas
    //mas nescessario para as outras entidades retornarem
    public JSONObject getJson() throws Exception{
        return null;
    }
    
     public JSONObject retornoListSafra() throws Exception{
        return null;
    }
}
