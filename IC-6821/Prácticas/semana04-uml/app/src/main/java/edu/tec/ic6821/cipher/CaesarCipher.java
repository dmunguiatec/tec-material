package edu.tec.ic6821.cipher;

public class CaesarCipher implements Cipher<CaesarKey> {

    @Override
    public String encrypt(String message, CaesarKey key) {
        int offset = key.get();

        StringBuilder builder = new StringBuilder();
        for (char currentChar : message.toCharArray()) {
            char newChar = (char) (currentChar + offset);
            builder.append(newChar);
        }

        return builder.toString();
    }

    @Override
    public String decrypt(String encryptedMessage, CaesarKey key) {
        int offset = key.get();

        StringBuilder builder = new StringBuilder();
        for (char currentChar : encryptedMessage.toCharArray()) {
            char newChar = (char) (currentChar - offset);
            builder.append(newChar);
        }

        return builder.toString();
    }
}
