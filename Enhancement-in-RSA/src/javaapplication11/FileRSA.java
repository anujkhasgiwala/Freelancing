/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication11;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

import de.flexiprovider.core.FlexiCoreProvider;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.crypto.NoSuchPaddingException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Nitin
 */
public class FileRSA extends JFrame {

    JPanel pnRSA = new JPanel();
    JLabel rsa_lblEncryptFile = new JLabel("Encrypt File : ");
    JLabel rsa_lblEncryptFilePath = new JLabel();
    JButton rsa_butEncryptChoose = new JButton("Choose...");
    JButton rsa_butEncryptRSA = new JButton("Encrypt DATA");
    
    
   
    JLabel rsa_lblDecryptFile = new JLabel("Decrypt File : ");
    JLabel rsa_lblDecryptFilePath = new JLabel();
    JButton rsa_butDecryptChoose = new JButton("Choose...");
    JButton rsa_butDecryptRSA = new JButton("Decrypt DATA");
    
    JLabel rsa_lbltimes = new JLabel("Times : ");
    JTextField times = new JTextField(8);
    int t = Integer.parseInt(times.getText());
    
    static Cipher cipher;

    public void initComponents() {
        this.setLayout(new GridBagLayout());
        pnRSA.setBorder(BorderFactory.createTitledBorder("Encryption"));
        pnRSA.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnRSA.add(rsa_lblEncryptFile, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        pnRSA.add(rsa_lblEncryptFilePath, gbc);

        gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.gridx = 1;
        pnRSA.add(rsa_butEncryptChoose, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        pnRSA.add(rsa_lblDecryptFile, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        pnRSA.add(rsa_lblDecryptFilePath, gbc);

        gbc = new GridBagConstraints();
        gbc.gridy = 7;
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        pnRSA.add(rsa_butDecryptChoose, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        pnRSA.add(rsa_lbltimes, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        pnRSA.add(times, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridy = 9;
        gbc.gridwidth = 3;
//        gbc.gridx = 1;
        
        gbc = new GridBagConstraints();
        gbc.gridy = 11;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        pnRSA.add(rsa_butEncryptRSA, gbc);

        gbc = new GridBagConstraints();
        gbc.gridy = 11;
        gbc.gridx = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        pnRSA.add(rsa_butDecryptRSA, gbc);

            
        JPanel pnSeparator = new MyLine();
        pnSeparator.setBorder(BorderFactory.createLineBorder(Color.black));
        gbc = new GridBagConstraints();
        gbc.gridy = 4;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        pnRSA.add(pnSeparator, gbc);

        gbc = new GridBagConstraints();
        gbc.gridy = 0;
        this.add(pnRSA, gbc);
    }

    public void setActionListeners() {
        rsa_butEncryptChoose.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JFileChooser fchoose = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(null, "txt");
                fchoose.setFileFilter(filter);
                if (fchoose.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    rsa_lblEncryptFilePath.setText(fchoose.getSelectedFile().getPath());
                }
            }
        });
        rsa_butDecryptChoose.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JFileChooser fchoose = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(null, "txt");
                fchoose.setFileFilter(filter);
                if (fchoose.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    rsa_lblDecryptFilePath.setText(fchoose.getSelectedFile().getPath());
                }
            }
        });
        
        
        rsa_butEncryptRSA.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String x = rsa_lblEncryptFilePath.getText().replace("\\", "\\\\");
                File f = new File(x);
                if (!f.exists()) {
                    JOptionPane.showMessageDialog(null, "File doesn't exist");
                    return;
                }
                long stTime = System.nanoTime();
                try {
                    cipher = Cipher.getInstance("RSA", "FlexiCore");
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(FileRSA.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchProviderException ex) {
                    Logger.getLogger(FileRSA.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchPaddingException ex) {
                    Logger.getLogger(FileRSA.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    RSA_Encrypt(f, f.getName() + "_encrypt");
                } catch (Exception ex) {
                    Logger.getLogger(FileRSA.class.getName()).log(Level.SEVERE, null, ex);
                }
                long etTime = System.nanoTime();
                System.out.println(stTime + "     " + etTime);
                }
        });
       
        rsa_butDecryptRSA.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String x = rsa_lblDecryptFilePath.getText().replace("\\", "\\\\");
                File f = new File(x);
                if (!f.exists()) {
                    JOptionPane.showMessageDialog(null, "File doesn't exist");
                    return;
                }

                try {
                    cipher = Cipher.getInstance("RSA", "FlexiCore");
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(FileRSA.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchProviderException ex) {
                    Logger.getLogger(FileRSA.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchPaddingException ex) {
                    Logger.getLogger(FileRSA.class.getName()).log(Level.SEVERE, null, ex);
                }

                long stTime = System.nanoTime();
                try {
                    RSA_Decrypt(f, f.getName() + "_decrypt");
                } catch (Exception ex) {
                    Logger.getLogger(FileRSA.class.getName()).log(Level.SEVERE, null, ex);
                }
                long etTime = System.nanoTime();
                 }
        });
               
    }

    FileRSA() {
        initComponents();
        setActionListeners();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setTitle("RSA");
        this.setVisible(true);
    }
    static PrivateKey privKey;
    static PublicKey pubKey;
    static KeyPair keyPair;

    public static void run(String[] args) throws FileNotFoundException, IOException {
        File f = new File("C:\\Users\\Afnan\\Desktop");
//        if (f.exists()) {
//            FileInputStream fis = new FileInputStream(f);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write("Afnan Pathan".getBytes());
        fos.flush();
        fos.close();
//            System.out.println(fis.available());
//        }

    }

    public static void main(String[] args) throws Exception {
        Security.addProvider(new FlexiCoreProvider());
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", "FlexiCore");

        kpg.initialize(1024);
        keyPair = kpg.generateKeyPair();
//        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

//        File f = new File("keys");
//        if (!f.exists()) {
////            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
////                    privKey.getEncoded());
//            FileOutputStream fos = new FileOutputStream(f);
////            fos.write(x509EncodedKeySpec.getEncoded());
////            fos.close();
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(keyPair);
//            oos.close();
//        } else {
//            FileInputStream fis = new FileInputStream(f);
////            byte[] encodedPrivateKey = new byte[(int) f.length()];
////            fis.read(encodedPrivateKey);
////            fis.close();
////            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
////                    encodedPrivateKey);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            keyPair =  (KeyPair) ois.readObject();
//            ois.close();
//        }
        privKey = keyPair.getPrivate();
        pubKey = keyPair.getPublic();

        new FileRSA();
    }

    public static void RSA_Decrypt(File f, String outputFileName) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException {
//        Cipher cipher = Cipher.getInstance("RSA", "FlexiCore");
//        Cipher ciph = Cipher.getInstance("RSA/ECB/PKCS1Padding","FlexiCore");
        cipher.init(Cipher.DECRYPT_MODE, privKey);

        File clearTextFile = new File(f.getParent() + "\\\\" + outputFileName);
//        if (clearTextFile.exists()) {
//            clearTextFile.delete();
//        }
//        clearTextFile.createNewFile();

        FileInputStream fis = new FileInputStream(f);
        CipherInputStream cis = new CipherInputStream(fis, cipher);
        FileOutputStream fos = new FileOutputStream(clearTextFile);
        int i;
        byte[] block = new byte[32];
        while ((i = cis.read(block)) != -1) {
            fos.write(block, 0, i);
        }
        fos.flush();
        fos.close();
    }

    public static void RSA_Decrypt_DB(File f, String outputFileName) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException {
        cipher.init(Cipher.DECRYPT_MODE, privKey);

        File clearTextFile = new File(f.getParent() + "\\\\" + outputFileName);

        FileInputStream fis = new FileInputStream(f);
        CipherInputStream cis = new CipherInputStream(fis, cipher);
        FileOutputStream fos = new FileOutputStream(clearTextFile);
        int i;
        byte[] block = new byte[128];
        while ((i = cis.read(block)) != -1) {
            fos.write(block, 0, i);
        }
        fos.flush();
        fos.close();
    }

    public static void RSA_Encrypt(File f, String outputFileName) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException {
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        int j=1;
        File cipherFile = new File(f.getParent() + "\\\\" + outputFileName);

        FileInputStream fis = new FileInputStream(f);
        FileOutputStream fos = new FileOutputStream(cipherFile);
        CipherOutputStream cos = new CipherOutputStream(fos, cipher);

        byte[] block = new byte[32];
        int i;
        while ((i = fis.read(block)) != -1) {
            cos.write(block, 0, i);
        }
        cos.flush();
        fos.flush();
        cos.close();
        fos.close();
        
    }

    public static void RSA_Encrypt_DB(File f, String outputFileName) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException {
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        File cipherFileMain = new File(f.getParent() + "\\\\" + outputFileName);

        FileInputStream fis = new FileInputStream(f);
        FileOutputStream fosMain = new FileOutputStream(cipherFileMain);

        byte[] block = new byte[32];
        int i;
        while ((i = fis.read(block)) != -1) {
            byte[] result = searchDB(block);
            if (result == null) {
                DBStore db = new DBStore();
                db.data = block;
                File cipherFile = new File("ntemp");
                FileOutputStream fos = new FileOutputStream(cipherFile);
//                ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
                CipherOutputStream cos = new CipherOutputStream(fos, cipher);
                cos.write(block, 0, i);
                cos.flush();
                fos.flush();
                cos.close();
                fos.close();
//                System.out.println("available : " + baos.size());
                FileInputStream fisCipherRead = new FileInputStream(cipherFile);
//                ByteArrayInputStream bais=new ByteArrayInputStream(fisCipherRead);
                byte[] bb = new byte[fisCipherRead.available()];
                fisCipherRead.read(bb);
                db.encrypt = bb;
                database.add(db);
                System.out.println("Not Found!! " + db.encrypt.length);
            }
            result = searchDB(block);
            System.out.println("Found!!" + result.length);
            fosMain.write(result, 0, result.length);
            block = new byte[32];
        }
        fosMain.flush();
        fosMain.close();
    }
    static List<DBStore> database = new ArrayList<DBStore>();

    public static byte[] searchDB(byte[] s) {
        DBStore d = null;
        for (int i = 0; i < database.size(); i++) {
            boolean match = true;
            d = database.get(i);
            byte[] arr = d.data;
            for (int z = 0; z < arr.length; z++) {
                if (arr[z] != s[z]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return d.encrypt;
            }
        }
        return null;
    }
}

class MyLine extends JPanel {

    @Override
    public void paint(Graphics g) {
        //Get the current size of this component
        Dimension d = this.getSize();
        //draw in black
        g.setColor(Color.BLACK);
        //draw a centered horizontal line
        g.drawLine(0, d.height / 2, d.width, d.height / 2);
    }
}

class DBStore {

    byte[] data;
    byte[] encrypt;
}
