//Jonathan Strickland
//LogPro Project
//Completed on 02/10/2020

package Java;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypter {

    /** Takes a passed string, encrypts it, and returns it */
    public String encryptText(String textToEncrypt) { //SHA-256 Encryption

        String encryptedText = null;

        try {

            MessageDigest md = MessageDigest.getInstance("SHA"); //Create instance for MessageDigest using SHA
            md.update(textToEncrypt.getBytes());

            byte [] bytes = md.digest();

            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < bytes.length; i++) {

                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));

            }

            encryptedText = sb.toString();


        } catch(NoSuchAlgorithmException e) {

            e.printStackTrace();

        }

        return encryptedText;

    }

}
