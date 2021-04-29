package baseballstats;

/**
 *
 * @author alexc
 */
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.time.*;

public class FileClass {
    private List<Player> playerStats = null;
    private Path playerStatsPath = null;
    private File playerStatsFile = null;
    private final int FIELD_SEP = 24;
    
    public FileClass(){
        playerStatsPath = Paths.get("playerStats.txt");
        try {
            if(Files.notExists(playerStatsPath)){
                System.out.println("********************");
                System.out.println("Data file not found.");
                System.out.println("********************");
                System.out.println("Creating file: " + 
                        playerStatsPath.toAbsolutePath() + "\n");
                Files.createFile(playerStatsPath);
            }
        }catch (IOException e){
                 System.out.println(e);
        }
        playerStatsFile = playerStatsPath.toFile();
    }
    
    //saves all of the information that was created into the list 
    public boolean saveAll(List<Player> playerList){
        try(PrintWriter out = new PrintWriter(
                              new BufferedWriter(
                              new FileWriter(playerStatsFile)))) {
            //Writing the stats in the list to the file 
            out.print("This game took place on: ");
            LocalDate currentDate = LocalDate.now();
            out.println(currentDate);
            StringBuilder list = new StringBuilder();
            list.append(StringUtil.pad("Name", FIELD_SEP));
            list.append(StringUtil.pad("# at Bats", FIELD_SEP));
            list.append(StringUtil.pad("# at Hits", FIELD_SEP));
            list.append(StringUtil.pad("# or RBI", FIELD_SEP));
            list.append(StringUtil.pad("# at Homeruns", FIELD_SEP));
            list.append(StringUtil.pad("Batting Average", FIELD_SEP));
            list.append("\n");
            for(Player p : playerList){
                list.append(StringUtil.pad(p.getName(),FIELD_SEP));
                list.append(StringUtil.pad(Integer.toString(p.getBats()), FIELD_SEP));
                list.append(StringUtil.pad(Integer.toString(p.getHits()), FIELD_SEP));
                list.append(StringUtil.pad(Integer.toString(p.getRbi()), FIELD_SEP));
                list.append(StringUtil.pad(Integer.toString(p.getHomeruns()), FIELD_SEP));
                list.append(StringUtil.pad(p.getBatting(), FIELD_SEP));
                list.append("\n");
            }
            out.println(list);
            out.close();
            return true;
        } catch (IOException e){
            System.out.println(e);
            return false;
        }
        
    }
}
