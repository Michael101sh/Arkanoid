package levelbuild;

import java.awt.Color;
import java.awt.Image;
import java.util.Map;
import java.util.TreeMap;

/**
 * The class ParseBlockSpecification - parse some block specification.
 * @author Michael Shachar and Hila Zecharia. */
public class ParseBlockSpecification {
    private String symbol;
    private double width;
    private double height;
    private Color stroke;
    private int hitPoints;
    private Map<Integer, Color> colorPerHitPoints;
    private Map<Integer, Image> imagePerHitPoints;
    private Color generalColor;

    /**
     * Constructor ParseBlockSpecification nu.2.
     * initialize do default values. */
    public ParseBlockSpecification() {
        this.symbol = "";
        this.width = 0;
        this.height = 0;
        this.stroke = null;
        this.hitPoints = 0;
        this.colorPerHitPoints = new TreeMap<Integer, Color>();
        this.imagePerHitPoints = new TreeMap<Integer, Image>();
        this.generalColor = null;
    }

    /**
     * Constructor ParseBlockSpecification nu.2.
     * @param symbol - .
     * @param width - .
     * @param height - .
     * @param hitPoints - .
     * @param colorPerHitPoints - .
     * @param imagePerHitPoints - .
     * @param generalColor */
    public ParseBlockSpecification(String symbol, double width, double height,
                                    int hitPoints, Map<Integer,
                                    Color> colorPerHitPoints,
                                    Map<Integer, Image> imagePerHitPoints,
                                    Color generalColor) {
        this.symbol = symbol;
        this.width = width;
        this.height = height;
        this.hitPoints = hitPoints;
        this.colorPerHitPoints = new TreeMap<Integer, Color>();
        this.colorPerHitPoints.putAll(colorPerHitPoints);
        this.imagePerHitPoints = new TreeMap<Integer, Image>();
        this.imagePerHitPoints.putAll(imagePerHitPoints);
        this.generalColor = generalColor;
    }

    /**
     * The function collectInformaOnBlock/
     * by specials parameters.
     * @param newStroke - .*/
    public void setStroke(Color newStroke) {
        this.stroke = newStroke;
    }

    /**
     * The function collectInformaOnBlock/
     * by specials parameters.
     * @param s - the string which represents. */
    public void collectInformaOnBlock(String s) {
        String copy = s;
        String[] substrings = copy.split(" ");

        for (int i = 0; i < substrings.length; i++) {
            if (substrings[i].contains("symbol")) {
                int j = substrings[i].indexOf(":");
                j++;
                this.symbol = substrings[i].substring(j);
            } else if (substrings[i].contains("width")) {
                int j = substrings[i].indexOf(":");
                j++;
                this.width = Integer.parseInt(substrings[i].substring(j));
            } else if (substrings[i].contains("height")) {
                int j = substrings[i].indexOf(":");
                j++;
                this.height = Integer.parseInt(substrings[i].substring(j));
            } else if (substrings[i].contains("hit_points")) {
                int j = substrings[i].indexOf(":");
                j++;
                this.hitPoints = Integer.parseInt(substrings[i].substring(j));
            } else if (substrings[i].contains("fill-")) {
                int specificHitPoints = 0;
                String hitPoSubstring =
                        this.buildSubstring(substrings[i], "fill-", '-', ':');
                specificHitPoints = Integer.parseInt(hitPoSubstring);
                // valueSubstring is the string of the color or the image.
                String valueSubstring = this
                        .buildSubstring(substrings[i], ":", ':', ')');
                if (valueSubstring.contains("color")) {
                    String colorSubstring = valueSubstring
                            .replace("color(", "");
                    ColorsParser colorsParser = new ColorsParser();
                    Color color = colorsParser.colorFromString(colorSubstring);
                    this.colorPerHitPoints.put(specificHitPoints, color);
                } else if (valueSubstring.contains("image")) {
                    int j = valueSubstring.indexOf('(');
                    j++;
                    String imageString = valueSubstring.substring(j);
                    ImageParser imageParser = new ImageParser();
                    Image image = imageParser.getImageFromDef(imageString);
                    this.imagePerHitPoints.put(specificHitPoints, image);
                }
                // we done with this substring.
                substrings[i] = "";
            } else if (substrings[i].contains("fill")) {
                String valueSubstring = this
                        .buildSubstring(substrings[i], ":", ':', ')');
                if (valueSubstring.contains("color")) {
                    String colorSubstring = valueSubstring
                            .replace("color(", "");
                    ColorsParser colorsParser = new ColorsParser();
                    Color color = colorsParser.colorFromString(colorSubstring);
                    this.generalColor = color;
                } else if (valueSubstring.contains("image")) {
                    int j = valueSubstring.indexOf('(');
                    j++;
                    String imageString = valueSubstring.substring(j);
                    ImageParser imageParser = new ImageParser();
                    Image image = imageParser.getImageFromDef(imageString);
                    // -1 is the default image
                    this.imagePerHitPoints.put(-1, image);
                }
             // we done with this substring.
                substrings[i] = "";
            } else if (substrings[i].contains("stroke")) {
                String strokeSubstring = this
                        .buildSubstring(substrings[i], ":", ':', ')');
                String colorSubstring = strokeSubstring.replace("color(", "");
                ColorsParser colorsParser = new ColorsParser();
                Color color = colorsParser.colorFromString(colorSubstring);
                this.stroke = color;
            }
        }
    }

    /**
     * The function buildSubstring - build substring of string
     * by specials parameters.
     * @param s - the string which represents.
     * @param cutFrom - from which char to cut.
     * @param inrenalChr - the internal char which we take substring
     *          one index after it.
     * @param endChar - where to stop in the string s.
     * @return the desired substring. */
    public String buildSubstring(String s, String cutFrom, char inrenalChr,
            char endChar) {
        int j = s.indexOf(cutFrom);
        //substring is the string of which define the width
        String substring = s.substring(
                j, s.indexOf(endChar, j));
        j = substring.indexOf(inrenalChr);
        j++;
        // the new substing is contains just the value of the substing
        substring = substring.substring(j);
        return substring;
    }

    /**
     * The function clone.
     *  @return clone of the object. */
    public ParseBlockSpecification clone() {
        ParseBlockSpecification parseBlockSpecification =
                new ParseBlockSpecification(this.symbol, this.width,
                                    this.height, this.hitPoints,
                                    this.colorPerHitPoints,
                                    this.imagePerHitPoints, this.generalColor);
        parseBlockSpecification.setStroke(this.stroke);
        return parseBlockSpecification;
    }

    /**
     * The function getSymbol.
     * @return the block's symbol. */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * The function getWidth.
     * @return the block's width. */
    public double getWidth() {
        return this.width;
    }

    /**
     * The function getHeight.
     * @return the x coordinate */
    public double getHeight() {
        return this.height;
    }

    /**
     * The function getStroke.
     * @return block's stroke. */
    public Color getStroke() {
        return this.stroke;
    }

    /**
     * The function getHitPoints.
     * @return block's hit points. */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * The function getColorPerHitPoints.
     * @return getColorPerHitPoints map. */
    public Map<Integer, Color> getColorPerHitPoints() {
        return this.colorPerHitPoints;
    }

    /**
     * The function getImagePerHitPoints.
     * @return the getImagePerHitPoints map. */
    public Map<Integer, Image> getImagePerHitPoints() {
        return this.imagePerHitPoints;
    }

    /**
     * The function getGeneralColor.
     * @return the general color of the block. */
    public Color getGeneralColor() {
        return this.generalColor;
    }
}
