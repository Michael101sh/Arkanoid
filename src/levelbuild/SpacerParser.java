package levelbuild;

/**
 * The class SpacerParser - parse spacer definition and return the symbol
 * name and the width which the spacer represents.
 * @author Michael Shachar and Hila Zecharia. */
public class SpacerParser {
    private String symbol;
    private int width;

    /**
     * Constructor SpacerParser.
     * Initialize to default values. */
    public SpacerParser() {
        this.symbol = null;
        this.width = 0;
    }

    /**
     * The function collectInformaOfSpacer - parse the spacer and
     * inserts it to the members of the class.
     * @param s - the string which represents the parser. */
    public void collectInformaOfSpacer(String s) {
        if (s.contains("sdef")) {
            String[] substrings = s.split(" ");
            for (int i = 0; i < substrings.length; i++) {
                if (substrings[i].contains("symbol")) {
                    int j = substrings[i].indexOf(':');
                    j++;
                    this.symbol = substrings[i].substring(j);
                } else if (substrings[i].contains("width")) {
                    int j = substrings[i].indexOf(':');
                    j++;
                    String widthString = substrings[i].substring(j);
                    this.width = Integer.parseInt(widthString);
                }
            }
        }
    }

    /**
     * The function getSymbol.
     * @return the spacer's symbol. */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * The function getWidth.
     * @return the spacer's width. */
    public int getWidth() {
        return this.width;
    }

}
