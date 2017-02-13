import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCrypt {


    private static final char[] CLEAR_TEXT_PASSWORD = "PassWorD".toCharArray();
    private static final byte[] SALT_PASSWORD = "bdi-storage.properties".getBytes();


    private SecureRandom secRand = new SecureRandom();
    private Cipher c;
    private IvParameterSpec IV;
    private SecretKey s_KEY;

    // Constructor
    public AESCrypt() throws NoSuchAlgorithmException, NoSuchPaddingException {

        this.c = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        this.IV = generateIV();
        this.s_KEY = generateKEY();
    }

    // COnvert the String to bytes..Should I be using UTF-8? I dont think it
    // messes with the encryption and this way any pc can read it ?
    // Initialize the cipher
    // Encrypt the String of bytes
    // Return encrypted bytes
    protected byte[] encrypt(String strToEncrypt) throws InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException,
            BadPaddingException, UnsupportedEncodingException {

        byte[] byteToEncrypt = strToEncrypt.getBytes("UTF-8");
        this.c.init(Cipher.ENCRYPT_MODE, this.s_KEY, this.IV, this.secRand);
        byte[] encryptedBytes = this.c.doFinal(byteToEncrypt);

        return encryptedBytes;

    }

    // Initialize the cipher in DECRYPT_MODE
    // Decrypt and store as byte[]
    // Convert to plainText and return

    protected String decrypt(byte[] byteToDecrypt) throws InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException,
            BadPaddingException {

        this.c.init(Cipher.DECRYPT_MODE, this.s_KEY, this.IV);

        byte[] plainByte = this.c.doFinal(byteToDecrypt);

        String plainText = new String(plainByte);

        return plainText;

    }

    // Create the IV.
    // Create a Secure Random Number Generator and an empty 16byte array. Fill
    // the array.
    // Returns IV

    private IvParameterSpec generateIV() {

        byte[] newSeed = secRand.generateSeed(16);
        secRand.setSeed(newSeed);

        byte[] byteIV = new byte[16];
        secRand.nextBytes(byteIV);
        IV = new IvParameterSpec(byteIV);
        return IV;
    }

    // Create a "KeyGenerator" that takes in 'AES' as parameter
    // Create a "SecureRandom" Object and use it to initialize the
    // "KeyGenerator"
    // keyGen.init(256, sRandom); Initialize KeyGenerator with parameters
    // 256bits AES

    private SecretKey generateKEY() throws NoSuchAlgorithmException {

        // byte[] bytKey = AES_KEY.getBytes(); // Converts the Cipher Key to
        // Byte format
        // Should I use SHA-2 to get a random key or is this better?

        byte[] newSeed = secRand.generateSeed(32);
        secRand.setSeed(newSeed);

        KeyGenerator keyGen = KeyGenerator.getInstance("AES"); // A
        // "KEyGenerator"
        // object,
        SecureRandom sRandom = secRand.getInstanceStrong(); // A "SecureRandom" object
        // used to init the
        // keyGenerator

        keyGen.init(256, sRandom); // Initialize RAndom Number Generator

        s_KEY = keyGen.generateKey();

        return s_KEY;

    }

    public String byteArrayToString(byte[] s) {
        String string = new String(s);

        return string;

    }

    // Get Methods for all class variables
    public Cipher getCipher() {

        return c;
    }

    public IvParameterSpec getIV() {
        return IV;
    }

    public SecretKey getSecretKey() {
        return s_KEY;

    }

}