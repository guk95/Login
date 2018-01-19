/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql4java;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Telematica-2-0
 */
public class funciones {

    public boolean isNumeric(Object element) {
        try {
            for (int i = 0; i < element.toString().length(); i++) {
                Integer.parseInt(String.valueOf(element.toString().charAt(i)));
            }
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public String MD5(String dat) 
    {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(dat.getBytes());
           byte[] digest = md.digest();
           String md5 = DatatypeConverter.printHexBinary(digest).toUpperCase();
           return md5;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
