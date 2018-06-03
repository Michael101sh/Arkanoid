package levelbuild;

import java.util.Map;
import mainpackage.Block;

/**
 * The class BlocksFromSymbolsFactory - .
 * @author Michael Shachar and Hila Zecharia. */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Constructor BlocksFromSymbolsFactory.
     * @param spacerWidths - the map width for spacers (symbols).
     * @param blockCreators - the map of blocks symbols to blocks creator */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths,
            Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * The function isSpaceSymbol.
     * @param s - .
     * @return true if 's' is a valid space symbol. */
    public boolean isSpaceSymbol(String s) {
        if (this.spacerWidths.containsKey(s)) {
            return true;
        }
        return false;
    }

    /**
     * The function isBlockSymbol.
     * @param s - .
     * @return true if 's' is a valid block symbol. */
    public boolean isBlockSymbol(String s) {
        if (this.blockCreators.containsKey(s)) {
            return true;
        }
        return false;
    }

    /**
     * The function getBlock.
     * @param s - .
     * @param xpos - .
     * @param ypos - .
     * @return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos). */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);

    }

    /**
     * The function getSpaceWidth.
      * @param s - .
     * @return the width in pixels associated with the given spacer-symbol. */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * The function getSpacerWidths.
     * @return blockCreators map. */
    public Map<String, Integer> getSpacerWidths() {
        return this.spacerWidths;
    }

    /**
     * The function getBlockCreators.
     * @return blockCreators map. */
    public Map<String, BlockCreator> getBlockCreators() {
        return this.blockCreators;
    }

    /**
     * The function isBLocksFileValid.
      * @param parseDefaultValuesCopy - .
     * @return boolean flag which indicate whether the blocks file is valid.*/
    public static boolean isBLocksFileValid(
            ParseBlockSpecification parseDefaultValuesCopy) {
        boolean checkFlag = false;
        // whether no symbol name or width or height was define.
        if (parseDefaultValuesCopy.getSymbol().equals("")
                || parseDefaultValuesCopy.getWidth() == 0
                || parseDefaultValuesCopy.getHeight() == 0) {
            checkFlag = true;
            return checkFlag;
        }
        // whether no color or image is define.
        if (parseDefaultValuesCopy.getGeneralColor() == null
                && parseDefaultValuesCopy.getColorPerHitPoints().size() == 0
                && parseDefaultValuesCopy.getImagePerHitPoints().size() == 0) {
            checkFlag = true;
            return checkFlag;
        }

        return checkFlag;
    }
}
