/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi;

import java.security.MessageDigest;

/**
 *
 * @author Administrator
 */
public class Thi {
    public String SHA(String vb){
        try{
        String chuoi ="";
        chuoi = vb;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(chuoi.getBytes());
        byte byteData[] = md.digest();
        
        StringBuffer hexString = new StringBuffer();
        for ( int i = 0 ;i < byteData.length; i++){
            String hex=Integer.toHexString(0xff & byteData[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        System.out.println("Hex format2: " + hexString.toString());
            //txtSha2.setText(hexString.toString());
        return hexString.toString();   
        }catch(Exception ex)
        {
            
        }
        return null;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
