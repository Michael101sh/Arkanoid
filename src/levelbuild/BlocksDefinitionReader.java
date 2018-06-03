package levelbuild;

import geoshapes.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import mainpackage.Block;

/**
 * The class BlocksDefinitionReader -  in charge of reading a block-definitions.
 * @author Michael Shachar and Hila Zecharia. */
public class BlocksDefinitionReader {

    /**
     * The function BlocksFromSymbolsFactory - reading a block-definitions
     * file and returning a BlocksFromSymbolsFactory object.
     * @param reader - .
     * @return BlocksFromSymbolsFactory object.
     * @throws Exception - .*/
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader)
                                throws Exception {
        BufferedReader br = null;
        // each line is line in the block definitions file.
        ArrayList<String> blocksDefinitions =
                new ArrayList<String>();
        try {
            br = new BufferedReader(reader);
            String line;
             line = br.readLine();
             while (line != null) {
                 blocksDefinitions.add(line);
                 line = br.readLine();
             }
        } catch (IOException e) {
            System.err.println("Failed read from file");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing buffer");
            }
        }
        Map<String, Integer> spacerWidths = new TreeMap<String, Integer>();
        Map<String, BlockCreator> blockCreators =
                new TreeMap<String, BlockCreator>();
        String defaultValues = null;
        List<ParseBlockSpecification> blocksSymoblsDefinitions =
                new ArrayList<ParseBlockSpecification>();
        ParseBlockSpecification parseDefaultValues =
                new ParseBlockSpecification();
        for (int i = 0; i < blocksDefinitions.size(); i++) {
            if (blocksDefinitions.get(i).contains("#")) {
                continue;
            }
            if (blocksDefinitions.get(i).contains("default")) {
                defaultValues = blocksDefinitions.get(i);
                parseDefaultValues.collectInformaOnBlock(defaultValues);
            } else if (blocksDefinitions.get(i).contains("bdef")) {
                ParseBlockSpecification parseDefaultValuesCopy =
                        parseDefaultValues.clone();
                    String blockSymoblDefinitions = blocksDefinitions.get(i);
                    parseDefaultValuesCopy.
                        collectInformaOnBlock(blockSymoblDefinitions);
                    boolean checkFlag = BlocksFromSymbolsFactory
                            .isBLocksFileValid(parseDefaultValuesCopy);
                    if (checkFlag) {
                        throw new RuntimeException("Blocks definitions "
                                + "file is invalid");
                    }
                    blocksSymoblsDefinitions.add(parseDefaultValuesCopy);
            } else if (blocksDefinitions.get(i).contains("sdef")) {
                SpacerParser spacerParser = new SpacerParser();
                spacerParser.collectInformaOfSpacer(blocksDefinitions.get(i));
                spacerWidths.put(spacerParser.getSymbol(),
                        spacerParser.getWidth());
            }
        }
        for (ParseBlockSpecification block : blocksSymoblsDefinitions) {
            Block newBlock = new Block(new Point(-5, -5), block.getWidth(),
                    block.getHeight(),
                    block.getGeneralColor(), block.getHitPoints(),
                    block.getColorPerHitPoints(), block.getImagePerHitPoints());
            newBlock.setStroke(block.getStroke());
            blockCreators.put(block.getSymbol(), newBlock);
        }
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }
}