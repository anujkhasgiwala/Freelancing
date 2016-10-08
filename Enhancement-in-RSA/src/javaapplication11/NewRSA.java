//package javaapplication11;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.math.BigInteger;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.Random;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//class NewRSA {
//
//    private BigInteger p;
//    private BigInteger q;
//    private BigInteger N;
//    private BigInteger phi;
//    private BigInteger e;
//    private BigInteger d;
//    private int bitlength = 1024;
//    private int blocksize = 256; //blocksize in byte
//    private Random r;
//    private static String encryptedStr = "";
//    private String str;
//
//    /**
//     * Init public and private keys
//     */
//    public NewRSA() {
//        r = new Random();
//        // get two big primes
//        p = BigInteger.probablePrime(bitlength, r);
//        q = BigInteger.probablePrime(bitlength, r);
//
//        // get the modulo
//        N = p.multiply(q);
//
//        // phi is needed to compute the exponent for encryption
//        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
//
//        // compute the exponent necessary for encryption (private key)
//        e = BigInteger.probablePrime(bitlength / 2, r);                                   // public key
//
//        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
//            e.add(BigInteger.ONE);                                                        //
//        }
//
//        // compute public key
//        d = e.modInverse(phi);                                                         //  private key
//    }
//
//    public NewRSA(BigInteger e, BigInteger d, BigInteger N) {
//        this.e = e;
//        this.d = d;
//        this.N = N;
//    }
//
//    String fileInput() {
//        String ret = "";
//        try {
//            // Open the file that is the first
//            // command line parameter
//            FileInputStream fis = new FileInputStream("fileInput.txt");
//            InputStreamReader isr16 = new InputStreamReader(fis, "UTF16");
//            InputStreamReader isr8 = new InputStreamReader(fis, "UTF8");
//
//            BufferedReader br16 = new BufferedReader(isr16);
//            BufferedReader br8 = new BufferedReader(isr8);
//            //Read File Line By Line
//            while ((str = br8.readLine()) != null) {
//                // Print the content on the console
////                System.out.println(str);
//                ret += str;
//            }
//            //Close the input stream
//            fis.close();
//        } catch (Exception e) {//Catch exception if any
//            System.err.println("Error: " + e.getMessage());
//        }
//        // System.out.println("text file fileInput.txt input here  ");
//        return ret;
//    }
//
//    public static void main(String[] args) throws UnsupportedEncodingException {
//        NewRSA rsa = new NewRSA();
////            String teststring = rsa.fileInput();
//        String teststring = "nitin surana is an idiot!!";
////            System.out.println("String in Bytes: " + bytesToString(teststring.getBytes("UTF8")));
//
//        byte[] encrypted = rsa.encrypt(teststring.getBytes("UTF8"));
//        String tt = new String(encrypted,"UTF8");
//        System.out.println("Encrypted String : " + tt);
//
//        byte[] decrypted = rsa.decrypt(tt.getBytes("UTF8"));
//        String decryptedstr = new String(decrypted,"UTF8");
//        System.out.println("Decrypted String :" + decryptedstr);
//    }
//
//    byte[] decryptedData(byte[] encryptedData) {
//        return decrypt(encryptedData);
//    }
//
//    /**
//     * Converts a byte array into its String representations
//     * @param encrypted
//     * @return
//     */
//    private static String bytesToString(byte[] encrypted) {
//        encryptedStr = "";
//        for (byte b : encrypted) {
//            encryptedStr += Byte.toString(b);
//        }
//        return encryptedStr;
//    }
//
//    /**
//     * encrypt byte array
//     * @param message
//     * @return
//     */
//    public byte[] encrypt(byte[] message) {
//        return (new BigInteger(message)).modPow(e, N).toByteArray();
//    }
//
//    /**
//     * decrypt byte array
//     * @param message
//     * @return
//     */
//    public byte[] decrypt(byte[] message) {
//        return (new BigInteger(message)).modPow(d, N).toByteArray();
//    }
//}
