/*
Kearstin Carden
5/5/2021
Baseball Statistics Project
This class creates a report based on the information written to the game file.
*/
package baseballapp;

import java.io.*;
import java.nio.file.*;

public class ReportClass{
    private Path playerReportPath = null;
    private File playerReportFile = null;
    private final int FIELD_SPACE_STATS = 5;
    private final int FIELD_SPACE_NAME = 20;
    private final String FIELD_SEP = "";
    String[] pathnames;
    private String filename;
    
    public ReportClass(String passFile){
        filename = passFile;
        File rfolder = new File("./reports");
        playerReportPath = Paths.get(rfolder + "/" + filename.substring(0, 10) + "_Report.txt");
        if(Files.exists(playerReportPath)){
            System.out.println("**********************");
            System.out.println("Deleting current file.");
            System.out.println("**********************");
            playerReportPath.toFile().delete();
        }
    }
    
    public void createHeader(){
        //Create header for report
         StringBuilder title = new StringBuilder();
         title.append(StringUtil.pad("Last Name,",FIELD_SEP,FIELD_SPACE_NAME))
            .append(StringUtil.pad("First Name",FIELD_SEP,FIELD_SPACE_NAME))
            .append(StringUtil.pad("ab",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("r",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("h",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("rbi",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("bb",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("so",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("po",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("a",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("lob",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("ba",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("tb",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("slg",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("obp",FIELD_SEP,FIELD_SPACE_STATS));
       
       StringBuilder d = new StringBuilder();
       for(int i=0;i<110;i++)
           d.append("-");
        
        try {
            if(Files.notExists(playerReportPath)){
                System.out.println("************************");
                System.out.println("Creating file: " + playerReportPath.toAbsolutePath());
                System.out.println("************************");
                Files.createFile(playerReportPath);
            }
        }catch (IOException e){
                 System.out.println("There was an error creating the report.");
        }
       playerReportFile = playerReportPath.toFile();
       
       
       //write header to report
       try(PrintWriter out = new PrintWriter(
                              new BufferedWriter(
                              new FileWriter(playerReportFile, true)))) {
            out.println(title);
            out.println(d);
            out.println();
            out.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }
    
    //Passed lines read from selected data file in GUI
    public void writeReport(String line){
        
        //Split line by commas
        String[] batter = line.split(",");
        
        //assign parts to variables
        String fName = batter[0].trim();
        String lName = batter[1].trim();
        int ab = Integer.parseInt(batter[2].trim());
        int r = Integer.parseInt(batter[3].trim());
        int h = Integer.parseInt(batter[4].trim());
        int rbi = Integer.parseInt(batter[5].trim());
        int bb = Integer.parseInt(batter[6].trim());
        int so = Integer.parseInt(batter[7].trim());
        int po = Integer.parseInt(batter[8].trim());
        int a = Integer.parseInt(batter[9].trim());
        int lob = Integer.parseInt(batter[10].trim());
        
        //convert to double for batAvg 
        //double hits = h + h2 + h3 + hr;
        //double batAvg = hits/ab_d;
        double ab_d = ab;
        double h_d = h;
        double batAvg = h_d/ab_d;
        
        //Total bases stat will need doubles and triples collected in GUI to function
        //int tBases = h + (2 * h2) + (3 * h3) + (4 * hr);
        int tBases = 0;
        
        //will need total bases functional to get this stat
        //sPercent = tBases / ab;
        double sPercent = 0.0;
        
        //need hit-by-pitch and sacrifice fly for on-base-percent
        //double onBasePercent = (h + bb + hbp) / (ab + bb + hbp + sf);
        double onBasePercent = 0.0;
        
        //append all stats
        StringBuilder batterStatsIn = new StringBuilder();
        batterStatsIn.append(StringUtil.pad(lName + ",",FIELD_SEP,FIELD_SPACE_NAME));
        batterStatsIn.append(StringUtil.pad(fName,FIELD_SEP,FIELD_SPACE_NAME));
        batterStatsIn.append(StringUtil.pad(Integer.toString(ab), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString(r), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString(h), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString(rbi), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString(bb), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString(so), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString(po), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString(a), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString(lob), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Double.toString(batAvg), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString(tBases), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Double.toString(sPercent), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Double.toString(onBasePercent), FIELD_SEP,FIELD_SPACE_STATS));
        
        //write to file
        try(PrintWriter out = new PrintWriter(
                              new BufferedWriter(
                              new FileWriter(playerReportFile, true)))) {
            out.println(batterStatsIn + "\n");
            out.close();
        } catch (IOException e){
            System.out.println(e);
        }

    }
}
