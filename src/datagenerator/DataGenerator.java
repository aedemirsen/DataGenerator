/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datagenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author aedemirsen
 *
 * CHARACTERS THAT SHOULD NOT BE IN THE GENERATED DATA:
 *
 *
 *
 */
public class DataGenerator {

    static String initialData = "";
    static String generatedData = "";
    static File file = new File("text.txt");
    static String newTxt = "myData.txt";

    public static void main(String[] args) {
        // TODO code application logic here                    
        try {
            initialData = new String(read());
            generate();
            write();
        } catch (IOException ex) {
            Logger.getLogger(DataGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static byte[] generate() {
        initialData = initialData.trim();
        Pattern pattern = Pattern.compile("[A-Za-z]+");
        Matcher matcher = pattern.matcher(initialData);
        while (matcher.find()) {
            generatedData = generatedData.concat(matcher.group() + " ");
        }
        return generatedData.getBytes();
    }

    static byte[] read() throws FileNotFoundException, IOException {
        InputStream is = file.toURI().toURL().openStream();
        byte[] b1 = new byte[is.available()];
        is.read(b1);
        return b1;
    }

    static void write() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(newTxt)) {
            fos.write(generate());
            fos.close();
        }
    }

}
