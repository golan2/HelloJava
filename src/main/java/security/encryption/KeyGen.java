package security.encryption;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.*;
import java.util.Arrays;

/**
 * Symmetric Encryption example.
 * Algorithm is: AES.
 * Encryption mode is CBC.
 * Java Crypto package is used.
 * Encryption Keys are generated, not hard-coded.
 * IV is generated, not hard-coded.
 */
public class KeyGen {
    public static void main(String[] args) throws Exception {

        //Generating a Symmetric Encryption AES key
        Key aesKey = generateKey();
        System.out.println(Arrays.toString(aesKey.getEncoded()));
//        encryptDecrypt(aesKey);


    }

    private static void encryptDecrypt(Key aesKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        //Defining the Encryption Cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        //*** Initialization Vector generation using Strong PRNG ***
        //Create a secure random number generator using the SHA1PRNG algorithm
        SecureRandom secureRandomGenerator =   SecureRandom.getInstance("SHA1PRNG");
        byte[] secureRandomBytes = new byte[16];

        //Initializing the generator
        secureRandomGenerator.nextBytes(secureRandomBytes);
        //Generate the Initialization Vector
        IvParameterSpec ivSpec = new IvParameterSpec(secureRandomBytes);

        //Original Cleartext
        byte[] input = "Cleartext for encryption!".getBytes();
        System.out.println("Original Cleartext    : " + new String(input));

        //Encryption
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivSpec);
        byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
        //Required for multiple-part encryption or decryption process
        int ciphertextLength = cipher.update(input, 0, input.length, cipherText, 0);
        ciphertextLength += cipher.doFinal(cipherText, ciphertextLength);
        System.out.println("Encrypted Ciphertext  : " + new String(cipherText));

        // Decryption process
        cipher.init(Cipher.DECRYPT_MODE, aesKey, ivSpec);
        byte[] decryptedText = new byte[cipher.getOutputSize(ciphertextLength)];
        //Required for multiple-part encryption or decryption process
        int cleartextLength = cipher.update(cipherText, 0, ciphertextLength,                     decryptedText, 0);
        cleartextLength += cipher.doFinal(decryptedText, cleartextLength);
        System.out.println("Decrypted Cleartext   : " + new String(decryptedText));
    }

    private static Key generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256, SecureRandom.getInstance("SHA1PRNG"));
        return keyGenerator.generateKey();
    }
}
