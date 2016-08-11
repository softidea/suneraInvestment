package ClassFiles;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Buwaneka
 */
public class ImageSelect {

    private String path;
    BufferedImage image = null;

    public BufferedImage showDialog() throws IOException {
        JFileChooser chooser = new JFileChooser();
        int retVal = chooser.showOpenDialog(chooser);
       
        if (retVal == JFileChooser.APPROVE_OPTION) {
           
            File file = chooser.getSelectedFile();
            path = file.getAbsolutePath();
          
            try {
                image = ImageIO.read(file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return image;

    }

    public String getPath() {
        return path;

    }
}
