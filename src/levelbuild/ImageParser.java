package levelbuild;

import java.awt.Image;
import java.io.InputStream
import javax.imageio.ImageIO;

/**
 * The class ImageParser - parse image definition and return 
 * the specified image.	
 * @author Michael Shachar and Hila Zecharia. */
public class ImageParser {

    /**
     * The function getImageFromDef - parse image definition and
     *  return the specified image.
     * @param path - the string which represents the image.
     * @return the parsed color. */
    public Image getImageFromDef(String path) {
        Image img = null;
        InputStream is = null;
        try {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
            img = ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
            img = null;
            if (is != null) {
                try {
                    is.close();
                } catch (Exception ex) {
                    e.printStackTrace();
                }
            }
        }
        return img;
    }
}
