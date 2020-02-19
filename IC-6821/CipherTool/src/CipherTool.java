public class CipherTool {
    public static void main(String args[]) {
        String message = "¡hola mundo!";
        Cipher cipher = CipherFactory.getInstance("caesar");

        Key key = new CaesarKey(7);
        String encrypted = cipher.encrypt(message, key);
        System.out.println("encrypted = " + encrypted);

        String decrypted = cipher.decrypt(encrypted, key);
        System.out.println("decrypted = " + decrypted);
    }
}

interface Cipher<K extends Key> {
    String encrypt(String message, K key);
    String decrypt(String encryptedMessage, K key);
}

interface Key<T> {
    T get();
}

class CaesarKey implements Key<Integer> {
    private int key;

    CaesarKey(int key) {
        this.key = key;
    }

    @Override
    public Integer get() {
        return this.key;
    }
}

class CipherFactory {
    public static Cipher getInstance(String algorithm) {
        if ("caesar".equals(algorithm)) {
            return new CaesarCipher();
        } else {
            throw new IllegalArgumentException("Unrecognized algorithm");
        }
    }
}

class CaesarCipher implements Cipher<CaesarKey> {
    @Override
    public String encrypt(String message, CaesarKey key) {
        int offset = key.get();
        StringBuilder builder = new StringBuilder(message.length());
        for (final char currentChar : message.toCharArray()) {
            char newChar = (char) (currentChar + offset);
            builder.append(newChar);
        }

        return builder.toString();
    }

    @Override
    public String decrypt(String encryptedMessage, CaesarKey key) {
        int offset = key.get();
        StringBuilder builder = new StringBuilder(encryptedMessage.length());
        for (final char currentChar : encryptedMessage.toCharArray()) {
            char newChar = (char) (currentChar - offset);
            builder.append(newChar);
        }

        return builder.toString();
    }
}