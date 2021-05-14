/*
Kearstin Carden
5/5/2021
Baseball Statistics Project
This class creates a report based on the information written to the game file.
*/
package baseballapp;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.*;
import java.text.NumberFormat;

public class ReportClass{
    private Path playerReportPath = null;
    private File playerReportFile = null;
    private final int FIELD_SPACE_STATS = 7;
    private final int FIELD_SPACE_NAME = 20;
    private final String FIELD_SEP = ",";
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
         title.append(StringUtil.pad("Last Name",FIELD_SEP,FIELD_SPACE_NAME))
            .append(StringUtil.pad("First Name",FIELD_SEP,FIELD_SPACE_NAME))
            .append(StringUtil.pad("ab",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("r",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("1bh",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("2bh",FIELD_SEP,FIELD_SPACE_STATS))     
            .append(StringUtil.pad("3bh",FIELD_SEP,FIELD_SPACE_STATS))     
            .append(StringUtil.pad("hr",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("rbi",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("bb",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("so",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("po",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("a",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("lob",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("sf",FIELD_SEP,FIELD_SPACE_STATS))     
            .append(StringUtil.pad("hbp",FIELD_SEP,FIELD_SPACE_STATS))     
            .append(StringUtil.pad("ba",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("tb",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("slg",FIELD_SEP,FIELD_SPACE_STATS))
            .append(StringUtil.pad("obp",FIELD_SEP,FIELD_SPACE_STATS));
       
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
        double ab = Double.parseDouble(batter[2].trim());
        int r = Integer.parseInt(batter[3].trim());
        double h = Double.parseDouble(batter[4].trim());
        double h2 = Double.parseDouble(batter[5].trim());
        double h3 = Double.parseDouble(batter[6].trim());
        double hr = Double.parseDouble(batter[7].trim());
        int rbi = Integer.parseInt(batter[8].trim());
        double bb = Double.parseDouble(batter[9].trim());
        int so = Integer.parseInt(batter[10].trim());
        int po = Integer.parseInt(batter[11].trim());
        int a = Integer.parseInt(batter[12].trim());
        int lob = Integer.parseInt(batter[13].trim());
        double sf = Double.parseDouble(batter[14].trim());
        double hbp = Double.parseDouble(batter[15].trim());
        
        double hits = h + h2 + h3 + hr;//total hits
        double batAvg = hits/ab;
        
        double tBases = h + (2 * h2) + (3 * h3) + (4 * hr);
        
        double sPercent = tBases / ab;
        
        double onBasePercent = (hits + bb + hbp) / (ab + bb + hbp + sf);
        
        BigDecimal bat = new BigDecimal(batAvg).setScale(2, RoundingMode.HALF_UP);
        BigDecimal slug = new BigDecimal(sPercent).setScale(2, RoundingMode.HALF_UP);
        BigDecimal ob = new BigDecimal(onBasePercent).setScale(2, RoundingMode.HALF_UP);
        
        String batAvgS = bat.toString();
        String sPercentS = slug.toString();
        String onBasePercentS = ob.toString();
        
        //append all stats
        StringBuilder batterStatsIn = new StringBuilder();
        batterStatsIn.append(StringUtil.pad(lName,FIELD_SEP,FIELD_SPACE_NAME));
        batterStatsIn.append(StringUtil.pad(fName,FIELD_SEP,FIELD_SPACE_NAME));
        batterStatsIn.append(StringUtil.pad(Integer.toString((int)ab), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString(r), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString((int)h), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString((int)h2), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString((int)h3), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString((int)hr), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString(rbi), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString((int)bb), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString(so), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString(po), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString(a), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString(lob), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString((int)sf), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Integer.toString((int)hbp), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(batAvgS, FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(Double.toString((int)tBases), FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(sPercentS, FIELD_SEP,FIELD_SPACE_STATS));
        batterStatsIn.append(StringUtil.pad(onBasePercentS, FIELD_SEP,FIELD_SPACE_STATS));
        
        //write to file
        try(PrintWriter out = new PrintWriter(
                              new BufferedWriter(
                              new FileWriter(playerReportFile, true)))) {
            out.println(batterStatsIn);
            out.close();
        } catch (IOException e){
            System.out.println(e);
        }
        
        
        
        
        

    }
}
