package levelbuild;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * The class LevelSpecificationReader - read the level definition file.
 * @author Michael Shachar and Hila Zecharia. */
public class LevelSpecificationReader {

    /**
     * The function fromReader - get a file name and returns a list
     *  of LevelInformation objects.
     *  @param reader - .
     *  @return the list of level information */
   public List<LevelInformation> fromReader(Reader reader) {
       BufferedReader br = null;
       ArrayList<ArrayList<String>> listOfLevels =
               new ArrayList<ArrayList<String>>();
       try {
           br = new BufferedReader(reader);
           String line;
           line = br.readLine();
           while (line != null) {
               List<String> level = new ArrayList<String>();
               level.add(line);
               while (!line.equals("END_LEVEL")) {
                   line = br.readLine();
                   level.add(line);
               }
               listOfLevels.add(new ArrayList<String>(level));
               line = br.readLine();
           }
       } catch (IOException e) {
           System.err.println("Failed read from reader: reader "
                   + ", message:" + e.getMessage());
           e.printStackTrace(System.err);
       } finally {
           try {
               if (br != null) {
                   br.close();
               }
           } catch (IOException e) {
               System.err.println("Failed closing buffer: br");
           }
       }
       List<LevelInformation> levelsList = new ArrayList<LevelInformation>();
       for (int i = 0; i < listOfLevels.size(); i++) {
           ParseLevelSpecification parseLevelSpecification =
                   new ParseLevelSpecification();
           parseLevelSpecification.collectInformaOfLevel(listOfLevels.get(i));
           boolean checkFlag = LevelSpecificationReader
                   .islevelsFileValid(parseLevelSpecification);
           if (checkFlag) {
               throw new RuntimeException("level definitions file is invalid");
           }
           CreateLevel newLevel = new CreateLevel(parseLevelSpecification);
           levelsList.add(newLevel);
       }
       return levelsList;
   }

   /**
    * The function islevelsFileValid.
    * @param parseLevelSpecification - .
    * @return boolean flag which indicates whether the level file is valid. */
   private static boolean islevelsFileValid(
               ParseLevelSpecification parseLevelSpecification) {
       boolean checkFlag = false;
       // whether no ball velocities or background or
       //paddle speed or width was define.
       if (parseLevelSpecification.getIntitialBallVelcoities().size() == 0
               || parseLevelSpecification.getBackground().equals("")
               || parseLevelSpecification.getPaddleSpeed() == 0
               || parseLevelSpecification.getPaddleWidth() == 0) {
           checkFlag = true;
           return checkFlag;
       }

       // whether no blocks start x or y or row height or
       // num of blocks was define.
       if (parseLevelSpecification.getBlockStartX() == 0
               || parseLevelSpecification.getBlockStartY() == 0
               || parseLevelSpecification.getRowHeight() == 0
               || parseLevelSpecification.getNumberOfBalls() == 0) {
           checkFlag = true;
           return checkFlag;
       }
       return checkFlag;
   }
}
