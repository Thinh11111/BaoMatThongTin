/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baithi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.swing.JOptionPane;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author ASUS
 */
public class Tool {

    

    
    
    public static String Des(String vb, String key)
    {
        try{
            FileInputStream fis= new FileInputStream("D:\\Des.txt");
            FileOutputStream fos = new FileOutputStream("D:\\EnDes.txt");
            encryptdes(key,fis,fos);
            try{
                BufferedWriter bw= null;
                String fileName= "D:\\Des.txt";
                String s = vb;
                bw = new BufferedWriter(new FileWriter(fileName));
                bw.write(s);
                bw.close();
                
                //txtmahoa.setText(s);
            }
            catch (IOException ex){
                Logger.getLogger(Tool.class.getName()) .log(Level.SEVERE,null,ex);
            }
        }
        catch(Throwable e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "key khong du do dai");
        }
        
        try{
            BufferedReader br= null;
            String fileName="D:\\EnDes.txt";
            br = new BufferedReader(new FileReader(fileName));
            StringBuffer sb = new StringBuffer();
            
            
            char[] ca= new char[5];
            while(br.ready()){
                int len = br.read(ca);
                sb.append(ca,0,len);
            }
            br.close();
            System.out.println("Du lieu la: "+sb);
            String chuoi = sb.toString();
            return chuoi;
        }
        catch(IOException ex){
            Logger.getLogger(Tool.class.getName()) .log(Level.SEVERE,null,ex);
        }
        return null;
    }
    
    
    public static String DeDes(String vb, String key){
        FileInputStream fis2=null;
        try{
            
            fis2= new FileInputStream("D:\\EnDes.txt");
            FileOutputStream fos2= new FileOutputStream("D:\\DeDes.txt");
            decryptdes(key, fis2, fos2);
            BufferedReader br = null;
            String fileName ="D:\\DeDes.txt";
            br = new BufferedReader(new FileReader(fileName));
            StringBuffer sb= new StringBuffer();
            JOptionPane.showMessageDialog(null, "da giai ma");
            char[] ca= new char[5];
            while(br.ready())
            {
                int len= br.read(ca);
                sb.append(ca,0,len);
            }
            br.close();
            System.out.println("du lieu la: "+sb);
            String chuoi = sb.toString();
            return chuoi;
        }
        catch(Throwable ex){
            Logger.getLogger(Tool.class.getName()) . log(Level.SEVERE,null,ex);
            
        }
        finally{
            try{
                fis2.close();
            }
            catch(IOException ex){
                Logger.getLogger(Tool.class.getName()) .log(Level.SEVERE,null,ex);
            }
        }
        return null;
    }     
    public static void encryptdes(String key, InputStream is, OutputStream os) throws Throwable{
        encryptOrDecrypt(key, Cipher.ENCRYPT_MODE,is,os);
    }
    
    public static void decryptdes(String key, InputStream is, OutputStream os) throws Throwable{
        encryptOrDecrypt(key, Cipher.DECRYPT_MODE,is,os);
    }
    private static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {
        DESKeySpec dks= new DESKeySpec(key.getBytes());
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = skf.generateSecret(dks);
        
        Cipher cipher = Cipher.getInstance("DES");
        
        if (mode== Cipher.ENCRYPT_MODE) {
            cipher.init(Cipher.ENCRYPT_MODE, desKey);
            CipherInputStream cis = new CipherInputStream(is, cipher);
            doCopy(cis, os);
        }
        else if (mode == Cipher.DECRYPT_MODE) {
            cipher.init(Cipher.DECRYPT_MODE, desKey);
            CipherOutputStream cos = new CipherOutputStream(os, cipher);
            doCopy(is, cos);
        }
    }
    private static void doCopy(InputStream is, OutputStream os) throws IOException {
        byte[] bytes= new byte[64];
        int numBytes;
        while((numBytes = is.read(bytes))!=-1){
            os.write(bytes, 0, numBytes);
        }
        os.flush();
        os.close();
        is.close();
    }
    
   
    
    public String Des3(String vb,String txtkhoa){
        try{
            myEncryptionKey= txtkhoa;
            myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
            KeyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
            myKeySpec = new DESedeKeySpec(KeyAsBytes);
            mySecretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
            cipher= Cipher.getInstance(myEncryptionScheme);
            key= mySecretKeyFactory.generateSecret(myKeySpec);
            System.out.println("khoa ma hoa k: "+key);
            
            String plainText= vb;
            String encrypted = encrypt(plainText);
            System.out.println("Encrypted Value: "+ encrypted);
            return encrypted;
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null, "key khong du do dai");
        }
        return null;
    }
    
    public String DeDes3(String vb, String txtkhoa){
        try{
            myEncryptionKey= txtkhoa;
            myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
            KeyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
            myKeySpec = new DESedeKeySpec(KeyAsBytes);
            mySecretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
            cipher= Cipher.getInstance(myEncryptionScheme);
            key= mySecretKeyFactory.generateSecret(myKeySpec);
            System.out.println("khoa ma hoa k: "+key);
            
            String plainText= vb;
            String encrypted = decrypt(plainText);
            System.out.println("Encrypted Value: "+ encrypted);
            return encrypted;
        }
        catch(Exception e){
           
        }
        return null;
    }
    
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME= "DESede";
    private KeySpec myKeySpec;
    private SecretKeyFactory mySecretKeyFactory;
    private Cipher cipher;
    byte[] KeyAsBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;
    
    public String encrypt(String unencrypted)
    {
        String encrypted = null;
        try{
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] plainText= unencrypted.getBytes(UNICODE_FORMAT);
            byte[] encryptedText= cipher.doFinal(plainText);
            BASE64Encoder base64encoder = new BASE64Encoder();
            encrypted = base64encoder.encode(encryptedText);
        } catch(Exception e){
            e.printStackTrace();
        }
        return encrypted;
    }
    
    
    public String decrypt(String encrypted){
        String unencrypted = null;
        try{
            cipher.init(Cipher.DECRYPT_MODE,key);
            BASE64Decoder base64decoder = new BASE64Decoder();
            byte[] encryptedText= base64decoder.decodeBuffer(encrypted);
            byte[] plainText = cipher.doFinal(encryptedText);
            String a = new String(plainText);
            System.out.println("chuoi plaintext: "+a);
            unencrypted = a;
        }catch(Exception e){
            e.printStackTrace();
        }
        return unencrypted;
    }
    
    //Ham bam MD5
    public static String MD5(String vb){
        try{
        
         String bam = vb;
        
         MessageDigest md = MessageDigest.getInstance("MD5");
         System.out.println("md:: " + md.toString());
         md.update(bam.getBytes());
         System.out.println("md:: " + md.toString());
         byte byteData[] = md.digest();
         
         StringBuffer hexString = new StringBuffer();
        for ( int i = 0 ;i < byteData.length; i++){
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        System.out.println("Digest(in hex format):: " + hexString.toString());
        
        
        BufferedWriter bw = null;
        String fileName = "D:\\BamMD5.txt";
        bw = new BufferedWriter(new FileWriter(fileName));
        bw.write(hexString.toString());
        bw.close();
        return hexString.toString();
        
         }catch(Exception ex) 
         {
         System.out.println("Lỗi us và pass: " +ex);
         }
        return null;
    }
    
    //Ham bam SHA
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
            
    //RailFence
    public static String MaHoaRailFence(int k, String s){
        int n=s.length();
        int sd, sc;
        sd = k;
        sc = n /sd+1;
        char hr[][]= new char[sd][sc];
        int c,d;
        c=0;d=0;
        int sodu = n%sd;
        for(int i=0;i<n;i++){
            hr[d][c] = s.charAt(i);
            d++;
            if(d==k){
                c++;
                d=0;
            }

        }
        String kq="";
        int sokytu=sc;
        for(int i=0; i<sd;i++){
            if(i>=sodu) sokytu=sc-1;
            for(int j=0;j<sokytu;j++)
            kq=kq+hr[i][j];
        }
        
        return kq;
    }
    
    public static String GiaiMaRailFence(int k, String s){
        int n=s.length();
        int sd,sc;
        sd=k;
        sc=n/sd+1;
        int t=0;
        int sodu = n%sd;
        int sokytu =sc;
        char hr[][]=new char[sd][sc];
        for(int i=0;i<sd;i++){
            if(i>=sodu) sokytu=sc-1;
            for(int j=0;j<sokytu;j++){
                hr[i][j] =s.charAt(t);
                t++;
            }
        }
        int c,d;
        c=0;d=0;
        String kq="";
        for(int i=0;i<n;i++){
            kq+=hr[d][c];
            d++;
            if(d==k){
                c++;d=0;
            }
        }
        return kq;
    }
    
    public static SecretKey secretKey;
    public String user;
    public String pass;
    public String khoa;
    byte[] byteCipherText;
    public String MaHoaAES(String UserName, String Password, String Plantext) throws InvalidKeyException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IOException{
        user = UserName;
        pass = Password;
        khoa = user+pass;
        BufferedWriter bw = null;
        String fileName = "D:\\AES.txt";
        String s = Plantext;
        bw = new BufferedWriter(new FileWriter(fileName));
        bw.write(khoa);
        bw.close();
        JOptionPane.showMessageDialog(null, "Ban da dang ky thanh cong . Vui long dang nhap lai !!!");
        //Ma = khoa.getBytes().toString();
        
        
        user = UserName;
        pass = Password;
        khoa = user + pass;
        BufferedReader br = null;
        fileName = "D:\\AES.txt";
        br = new BufferedReader(new FileReader(fileName));
        StringBuffer sb = new StringBuffer();
        char[] ca = new char[5];
        while(br.ready()){
            int len = br.read(ca);
            sb.append(ca,0,len);
        }
        br.close();
        System.out.println("Khoa la : " + " " + sb);
        String chuoi = sb.toString();
        Boolean k = khoa.equals(chuoi);
        if(k==true) JOptionPane.showMessageDialog(null, "Dang nhap thanh cong");
        else
            JOptionPane.showMessageDialog(null, "Dang nhap that bai");
        //Ma = chuoi.getBytes().toString();
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        secretKey = keyGen.generateKey();
        
        System.out.println("Sinh khoa : " + secretKey);
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE,secretKey);
        String strData = Plantext;
        byte[] byteDataToEncrypt = strData.getBytes();
        byteCipherText = aesCipher.doFinal(byteDataToEncrypt);
        String strCipherText = new BASE64Encoder().encode(byteCipherText);
        System.out.println("Cipher Text generated using AES is " + strCipherText);
        return strCipherText;
    }
    
    public String GiaiMaAES () throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
        //String cipherText = CipherText;
        //txtPlantext.setText(cipherText);
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE,secretKey,aesCipher.getParameters());
        byte[] byteDecryptedText = aesCipher.doFinal(byteCipherText);
        String strDecryptedText = new String(byteDecryptedText);
        System.out.println("Decrypted Text message is " + strDecryptedText);
        return strDecryptedText;
    }

    //Ceasar
    public String mahoa(String br, int k) {
        
        String kq="";
        int n=br.length();
        for(int i=0;i<n;i++)
        {
            kq+=mahoakt(br.charAt(i),k);
        }
        return kq;
    }
     
    char mahoakt(char c, int k){
        if(!Character.isLetter(c)) return c;
        return (char ) ((((Character.toUpperCase(c)-'A')+k)%26 +26)%26+'A');
    }
     
    //Vigenere
    int vig[][];
    
    public String mahoaVin( String banro, String key){
            int n=banro.length();
            String banma="";
            int k=0;
            for(int i=0;i<n;i++)
                if(Character.isLetter(banro.charAt(i)))
                {
                    banma+=mahoaVin(banro.charAt(i),key.charAt(k));
                    k++;
                    k=k%key.length();

                }else
                    banma+=banro.charAt(i);
            return banma;
    }
    
    char mahoaVin(char x, char k){
        vig=new int[26][26];
        for(int i=0;i<26;i++)
                for(int j=0;j<26;j++)
                     vig[i][j]=(i+j)%26;
        int xn=Character.toUpperCase(x)-'A';
        int kn=Character.toUpperCase(k)-'A';
        int yn=vig[kn][xn];
        return (char)(yn+'A');
    }
    
    public String giaimaVin(String bm, String khoa){
        //String banma= this.txtbm.getText();
        //String k= this.txtkhoa.getText();
        String kt1="";
        int kn=khoa.length();
        for(int i=0;i<kn;i++)
        kt1+=(char)(((26-(Character.toUpperCase(khoa.charAt(i))-'A'))%26)+'A');
        //this.txtkhoa.setText(k);
        String banro= mahoaVin(bm,kt1);
        return banro;
    }
}
