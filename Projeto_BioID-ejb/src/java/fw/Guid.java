/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fw;
import java.util.Random;

/**
 *
 * @author Inovação02
 */
public class Guid {
    private static Guid guidFactory = new Guid();
    static Random random = new Random();
    
    public static void setGuidImpl(Guid factory){
        guidFactory = factory;
    }
    
    public static String getString(){
        return guidFactory.getGuidString().toUpperCase();
    }
    
    protected String getGuidString(){
        long rand = (random.nextLong() & 0x2FFFFFFFFFFFFFFFL) |
                0X4000000000000000L;
        return Long.toString(rand, 32) +
        Long.toString(System.currentTimeMillis() & 0xFFFFFFFFFFFFFL , 32);
    }
}
