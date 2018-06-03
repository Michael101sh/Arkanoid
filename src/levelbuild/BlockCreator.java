package levelbuild;

import mainpackage.Block;

/**
 * The class BlockCreator - create a block after the width and
 * height were define.
 * @author Michael Shachar and Hila Zecharia. */
public interface BlockCreator {

    /**
     * The function create - Create a block at the specified location.
     * @param xpos - .
     * @param ypos - .
     * @return block.  */
    Block create(int xpos, int ypos);

    /**
     * The function getWidth.
     * @return Return the width of the block. */
    double getWidth();
 }