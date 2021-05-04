
package baseballapp;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.time.*;

public class FileClass {
    private List<Batter> playerStats = null;
    private Path playerStatsPath = null;
    private File playerStatsFile = null;
    private final int FIELD_SEP = 24;
    String[] pathnames;
    public static List<String> games = new ArrayList<>();
    
    public FileClass(){
        File folder = new File("./games");
        
        
        pathnames = folder.list();
        for (String pathname : pathnames) {
            // Print the names of files and directories          
            games.add(pathname);
        }
        
        //System.out.println(games);
        LocalDate filename = LocalDate.now();
        playerStatsPath = Paths.get(folder + "/" + filename + ".txt");
        
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
    public boolean saveAll(List<Batter> playerList){
        try(PrintWriter out = new PrintWriter(
                              new BufferedWriter(
                              new FileWriter(playerStatsFile, true)))) {
            //Writing the stats in the list to the file 
            LocalDate currentDate = LocalDate.now();
            out.println(currentDate);
            StringBuilder list = new StringBuilder();
            for(Batter p : playerList){
                list.append(StringUtil.pad(p.getLastName(),FIELD_SEP));
                list.append(StringUtil.pad(Integer.toString(p.getab()), FIELD_SEP));
                list.append(StringUtil.pad(Integer.toString(p.geth()), FIELD_SEP));
                list.append(StringUtil.pad(Integer.toString(p.getrbi()), FIELD_SEP));
                list.append(StringUtil.pad(Integer.toString(p.getr()), FIELD_SEP));
                
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
