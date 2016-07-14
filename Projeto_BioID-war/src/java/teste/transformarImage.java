/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author daniel
 */
public class transformarImage {
    
    /*
	public static void main(String[] args) {
		File file = new File("/Users/jeeva/Pictures/wallpapers/water-drop.jpg");
		
		try {
			/*
			 * Reading a Image file from file system
			 *
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int)file.length()];
			imageInFile.read(imageData);
			
			/*
			 * Converting Image byte array into Base64 String 
			 *
			String imageDataString = encodeImage(imageData);
			
			/*
			 * Converting a Base64 String into Image byte array 
			 *
			byte[] imageByteArray = decodeImage(imageDataString);
			
			/*
			 * Write a image byte array into file system  
			 *
			FileOutputStream imageOutFile = new FileOutputStream("/Users/jeeva/Pictures/wallpapers/water-drop-after-convert.jpg");
			imageOutFile.write(imageByteArray);
			
			imageInFile.close();
			imageOutFile.close();
			
			System.out.println("Image Successfully Manipulated!");
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}

	}*/
    
    
    
    
    
    public static String encodeImage(byte[] imageByteArray){		
		return Base64.encodeBase64URLSafeString(imageByteArray);		
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
