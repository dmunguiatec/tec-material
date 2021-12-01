package edu.tec.ic6821.cipher;

public interface Cipher<K extends Key> {

    String encrypt(String message, K key);
    String decrypt(String encryptedMessage, K key);

}
