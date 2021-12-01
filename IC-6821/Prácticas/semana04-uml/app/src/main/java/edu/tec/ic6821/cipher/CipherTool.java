package edu.tec.ic6821.cipher;

public class CipherTool {

    public static void main(String[] args) {
        Cipher cipher = CipherFactory.getInstance(CipherFactory.CAESAR);

        Key key = new CaesarKey(7);

        String message = "¡hola mundo!";
        String encrypted = cipher.encrypt(message, key);
        String decrypted = cipher.decrypt(encrypted, key);

        System.out.println("encrypted = " + encrypted);
        System.out.println("decrypted = " + decrypted);
    }

}
