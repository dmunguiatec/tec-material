package edu.tec.ic6821.cipher;

public class CipherFactory {

    public static final String CAESAR = "caesar";

    public static Cipher getInstance(String algorithm) {
        if (!CAESAR.equals(algorithm)) {
            throw new IllegalArgumentException(String.format("Unknown algorithm %s", algorithm));
        }

        return new CaesarCipher();
    }
}
