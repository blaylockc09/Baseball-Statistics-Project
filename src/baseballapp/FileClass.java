
package baseballapp;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.time.*;

public class FileClass {
    private List<Batter> playerStats = null;
    private Path playerStatsPath = null;
    private File playerStatsFile = null;
    private final int FIELD_SPACE = 18;
    private final String FIELD_SEP = ",";//added field seperator to make reading from the file easier -KJC 5/5
    String[] pathnames;
    public static List<String> games = new ArrayList<>();
    
    public FileClass(Batter player){
        File folder = new File("./games");
        
        
        pathnames = folder.list();
        for (String pathname : pathnames) {
            // Print the names of files and directories          
            games.add(pathname);
        }
        
        //checking if the file with the correct date of today or the players game date 
        //in the folder and if not creating a file for that date 
        LocalDate filename = LocalDate.now();
        LocalDate playerGameDate = LocalDate.parse(player.getDateOfGame());
        playerStatsPath = Paths.get(folder + "/" + playerGameDate + ".txt");
        
        try {
            if(Files.notExists(playerStatsPath)){
                System.out.println("********************");
                System.out.println("Data file not found.");
                System.out.println("********************");
                System.out.println("Creating file: " + playerStatsPath.toAbsolutePath() + "\n");
                Files.createFile(playerStatsPath);
            }
        }catch (IOException e){
                 System.out.println(e);
        }
       playerStatsFile = playerStatsPath.toFile();
        
    }
    
    public static List<String> GetGames(){
        return games;
    }
    
    //saves all of the information that was created into the list 
    public boolean save(Batter player){
        try(PrintWriter out = new PrintWriter(
                              new BufferedWriter(
                              new FileWriter(playerStatsFile, true)))) {
            StringBuilder list = new StringBuilder();
            
            //Added field separator to make reading the file easier for report class - KJC 5/5
            list.append(StringUtil.pad(player.getFirstName(),FIELD_SEP,FIELD_SPACE));
            list.append(StringUtil.pad(player.getLastName(),FIELD_SEP,FIELD_SPACE));
            list.append(StringUtil.pad(Integer.toString(player.getab()), FIELD_SEP,FIELD_SPACE));
            list.append(StringUtil.pad(Integer.toString(player.getr()), FIELD_SEP,FIELD_SPACE));
            list.append(StringUtil.pad(Integer.toString(player.geth()), FIELD_SEP,FIELD_SPACE));
            list.append(StringUtil.pad(Integer.toString(player.getrbi()), FIELD_SEP,FIELD_SPACE));
            list.append(StringUtil.pad(Integer.toString(player.getbb()), FIELD_SEP,FIELD_SPACE));
            list.append(StringUtil.pad(Integer.toString(player.getso()), FIELD_SEP,FIELD_SPACE));
            list.append(StringUtil.pad(Integer.toString(player.getpo()), FIELD_SEP,FIELD_SPACE));
            list.append(StringUtil.pad(Integer.toString(player.geta()), FIELD_SEP,FIELD_SPACE));
            list.append(StringUtil.pad(Integer.toString(player.getlob()), "",FIELD_SPACE));
            //
            
            out.println(list);
            out.close();
            return true;
        } catch (IOException e){
            System.out.println(e);
            return false;
        }
        
    }
}
