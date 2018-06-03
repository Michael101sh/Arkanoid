package levelbuild;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * The class ColorsParser - parse color definition and return the
 * specified color.
 * @author Michael Shachar and Hila Zecharia. */
public class ColorsParser {

    /**
     * The function colorFromString - parse color definition and
     *  return the specified color.
     * @param s - the string which represents the color.
     * @return the parsed color. */
    public java.awt.Color colorFromString(String s) {

        if (s.contains("RGB")) {
            String substring = s.replace("RGB", "");
            substring = substring.replace("(", "");
            if (substring.contains(")")) {
                substring = substring.replaceAll(")", "");
            }
            int red = 0, green = 0, blue = 0;
            List<Integer> colors = new ArrayList<Integer>();
            for (int i = 0; i < substring.length(); i++) {
                int num = 0;
                int single = 0;
                while (i < substring.length()
                        && substring.charAt(i) != ',') {
                    single = Character
                            .getNumericValue(substring.charAt(i));
                    num = single + num * 10;
                    i++;
                }
                colors.add(num);
            }
            red = colors.get(0);
            green = colors.get(1);
            blue = colors.get(2);
            return new Color(red, green, blue);
        } else {
            Color color;
            try {
                Field field = Color.class.getField(s);
                color = (Color) field.get(null);
            } catch (Exception e) {
                color = null; // Not defined
            }
            return color;
        }
    }
 }
