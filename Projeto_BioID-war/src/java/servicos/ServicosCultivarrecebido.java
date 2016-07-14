/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOCultivarRecebido;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.json.JSONArray;
import org.json.JSONObject;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("cultivarrecebido")
public class ServicosCultivarrecebido {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicosCultivarrecebido
     */
    public ServicosCultivarrecebido() {
    }

    //serviço de listar os usuarios
    @GET
    @Path("listar")
    public String listar() throws Exception{
    
        JSONObject j = new JSONObject();
        
        try{
            //cria um jsonarray e popula com uma consulta no banco
            JSONArray ja = BOFactory.listar(new DAOCultivarRecebido());
            j.put("data", ja);
            j.put("sucesso", true);
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        
        return j.toString();
    }
 
      //serviço de listar os usuarios
    @GET
    @Path("teste")
    public String teste()throws Exception{
        
        File file = new File("/home/daniel/Imagens/Captura.jpg");
        
        try {
			/*
			 * Reading a Image file from file system
			 */
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int)file.length()];
			imageInFile.read(imageData);
			
			/*
			 * Converting Image byte array into Base64 String 
			 */
			String imageDataString = encodeImage(imageData);
			System.out.println(imageDataString);
			/*
			 * Converting a Base64 String into Image byte array 
			 */
			byte[] imageByteArray = decodeImage(imageDataString);
			
			/*
			 * Write a image byte array into file system  
			 */
			FileOutputStream imageOutFile = new FileOutputStream("/home/daniel/Imagens/Captura2.jpg");
			imageOutFile.write(imageByteArray);
			
			imageInFile.close();
			imageOutFile.close();
			
			System.out.println("Image Successfully Manipulated!");
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}

       return "a";
    }
    
    
    
    
    public static String encodeImage(byte[] imageByteArray){		
		return Base64.encodeBase64String(imageByteArray);	
	}
	
	/**
	 * Decodes the base64 string into byte array
	 * @param imageDataString - a {@link java.lang.String} 
	 * @return byte array
	 */
	public static byte[] decodeImage(String imageDataString) {		
		return Base64.decodeBase64(imageDataString);
	}
    
      
    
    
    
}


        /* 
        BufferedImage imagem;
        
        imagem = ImageIO.read(new File("/home/daniel/Imagens/Captura.png"));
        
        //OBTEM A IMAGEM E TRANSFORMA EM BYTES[]
        ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
        
        //seta a imagem para bytesImg
        ImageIO.write((BufferedImage)imagem, "png", bytesImg);
        
        //limpa a variável
        bytesImg.flush();
        
        //Converte ByteArrayOutputStream para byte[]
        byte[] byteArray = bytesImg.toByteArray(); 
        
        //fecha a conversão
        bytesImg.close();

        return imagem.toString();*/