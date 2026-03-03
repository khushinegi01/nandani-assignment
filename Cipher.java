public interface Cipher {
    String encrypt(String messageFile, String keyFile);
    String decrypt(String messageFile, String keyFile);
}