/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package copyfiles;

/**
 *
 * @author rafch
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFiles {

    /**
     * @param args the command line arguments
     */
    public FileInputStream input;
    public FileOutputStream output;
    public byte[] b;
    public File fileInput;
    public File fileOutput;

    public CopyFiles() {
        fileInput = new File("/Users/peter/JAVA/matriz_java/archivo1.txt");
        fileOutput = new File("/Users/peter/JAVA/matriz_java/archivo2.txt");
    }

    public void duplicar() throws IOException {
        input = new FileInputStream(fileInput);
        output = new FileOutputStream(fileOutput);
        b = new byte[(int) fileInput.length()];
        input.read(b);
        output.write(b);
        input.close();
        output.close();
    }

    public static void main(String[] argumentos) throws IOException {
        CopyFiles cp = new CopyFiles();
        cp.duplicar();
    }

}