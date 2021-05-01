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
    //private List<Player> playerStats = null;
    private Path playerStatsPath = null;
    private File playerStatsFile = null;
    private final int FIELD_SEP = 24;
    
    //the constructor of the class makes a file if there is not one already there
    //and also sets up the header of the file 
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
                try(PrintWriter out = new PrintWriter(
                              new BufferedWriter(
                              new FileWriter(playerStatsPath.toFile(),true)))) {
                    StringBuilder list = new StringBuilder();
                    list.append(StringUtil.pad("Name", FIELD_SEP));
                    list.append(StringUtil.pad("Date Updated", FIELD_SEP));
                    list.append(StringUtil.pad("# of Games", FIELD_SEP));
                    list.append(StringUtil.pad("# at Bats", FIELD_SEP));
                    list.append(StringUtil.pad("# at Hits", FIELD_SEP));
                    list.append(StringUtil.pad("# or RBI", FIELD_SEP));
                    list.append(StringUtil.pad("# at Homeruns", FIELD_SEP));
                    list.append(StringUtil.pad("Batting Average", FIELD_SEP));
                    list.append("\n");
                    out.println(list);
                    out.close();
                } catch(IOException e){
                    System.out.println(e);
                }
            }
        }catch (IOException e){
                 System.out.println(e);
        }
        playerStatsFile = playerStatsPath.toFile();
    }
    
    //saves all of the information that was entered to the file 
    public boolean saveAll(List<Player> playerList){
        try(PrintWriter out = new PrintWriter(
                              new BufferedWriter(
                              new FileWriter(playerStatsFile,true)))) {
            //getting the current time for the date of the game 
            LocalDate currentDate = LocalDate.now();
            String currentDateString = currentDate.getMonthValue() + "/" +
                    currentDate.getDayOfMonth() + "/" + currentDate.getYear();
            //setting up string builder class to make the correct formatting for the file
            StringBuilder list = new StringBuilder();            
            for(int x = 0; x < playerList.size(); x++){
                list.append(StringUtil.pad(playerList.get(x).getName(),FIELD_SEP));
                list.append(StringUtil.pad(currentDateString, FIELD_SEP));
                list.append(StringUtil.pad(Integer.toString(playerList.get(x).getGames()), FIELD_SEP));
                list.append(StringUtil.pad(Integer.toString(playerList.get(x).getBats()), FIELD_SEP));
                list.append(StringUtil.pad(Integer.toString(playerList.get(x).getHits()), FIELD_SEP));
                list.append(StringUtil.pad(Integer.toString(playerList.get(x).getRbi()), FIELD_SEP));
                list.append(StringUtil.pad(Integer.toString(playerList.get(x).getHomeruns()), FIELD_SEP));
                list.append(StringUtil.pad(playerList.get(x).getBatting(), FIELD_SEP));
                list.append("\n");
            }
            list.delete(list.length()-1,list.length());
            System.out.println(list.length());
            //writing it all to the file 
            out.println(list);
            return true;
        } catch (IOException e){
            System.out.println(e);
            return false;
        }
        
    }
}
