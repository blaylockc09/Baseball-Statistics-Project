/*
Name: Kush Patel
Date: 05/03/2021    
*/

package baseballapp;
import static baseballapp.FileClass.games;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.*;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;

// BaseballApp class extends the JFrame Class
public class BaseballApp extends JFrame{
    // instance variables
    private JTextField nameField;
    private JTextField dateField;
    private JTextField abField;
    private JTextField rField;
    private JTextField hField;
    private JTextField rbiField;
    private JTextField bbField;
    private JTextField soField;
    private JTextField poField;
    private JTextField aField;
    private JTextField lobField;
    private JTextField h2Field;
    private JTextField h3Field;
    private JTextField hrField;
    private JTextField sfField;
    private JTextField hbpField;
    private BufferedImage image;
    
    private final int FIELD_SPACE_STATS = 7;
    private final int FIELD_SPACE_NAME = 20;
    private final String FIELD_SEP = ",";
    //private FileClass file = new FileClass();
    private JList b;
    String gameSelected = "";
    
    // sets the look of the window same as the current operating system
    public BaseballApp() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        // calls the initComponents() method
        initComponents();
    }

          
    // creates the GUI window and its elements
    private void initComponents() {
      // creates the main window
      JFrame frame = new JFrame();  
      frame.setTitle("Baseball Stats Application");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
      
      // reads an image file
      try {                
            image = ImageIO.read(new File("baseball.png"));
      } catch (IOException ex) {
            System.out.println(ex);
      }
      
      // creates an image icon
      ImageIcon icon = new ImageIcon(image);
      Image newImage = icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
      ImageIcon ic = new ImageIcon(newImage);
      
      // creates a label and adds the image file to the label
      JLabel imageLabel = new JLabel();
      imageLabel.setIcon(ic);
      
      // creates a panel and appends the imagePanel to the frame
      JPanel imagePanel = new JPanel();
      imagePanel.add(imageLabel);
      imagePanel.setPreferredSize(new Dimension(400,90));
      imagePanel.setMaximumSize(new Dimension(400, 90));
      frame.add(imagePanel);

      
      // creates a heading and sets its font type and size
      JLabel heading = new JLabel("Baseball Stats Application", JLabel.CENTER);
      heading.setFont(new Font("Arial", Font.BOLD, 20));
      heading.setHorizontalTextPosition(JLabel.CENTER);
      // creates a panel for the heading
      JPanel headingPanel = new JPanel();
      headingPanel.add(heading);
      headingPanel.setPreferredSize(new Dimension(400,90));
      headingPanel.setMaximumSize(new Dimension(400, 90));
      frame.getContentPane().add(headingPanel);
      
      //frame.getContentPane().setBackground(Color.YELLOW);
      
      // creates the buttons
      JButton insertButton = new JButton("Insert Stats");
      JButton readButton = new JButton("Read Stats");
      JButton helpButton = new JButton("Help");
      JButton exitButton = new JButton("Exit");
      
      // attaches an action listener to the buttons
      insertButton.addActionListener(evt -> insertButtonClicked());
      readButton.addActionListener(evt -> readButtonClicked());
      helpButton.addActionListener(evt -> helpButtonClicked());
      exitButton.addActionListener(evt -> exitButtonClicked());
      
      // sets the preferred and minimum size of the buttons
      insertButton.setPreferredSize(new Dimension(100, 40));
      readButton.setPreferredSize(new Dimension(100, 40));
      helpButton.setPreferredSize(new Dimension(100, 40));
      exitButton.setPreferredSize(new Dimension(100, 40));
      
      // creates a panel and adds the buttons to it
      JPanel panel = new JPanel();
      panel.add(insertButton);
      panel.add(readButton);
      panel.add(helpButton);
      panel.add(exitButton);
      
      
      // sets the layout of the panel
      panel.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.setPreferredSize(new Dimension(110, 500));
      panel.setMaximumSize(new Dimension(110, 500));
      frame.getContentPane().add(panel);
      
      //sets the size of the window and sets its visibility to true
      frame.setMinimumSize(new Dimension(330, 390));
      frame.setVisible(true);
      pack();
      // displays the window in the center
      frame.setLocationRelativeTo(null);
    }
    
    // helper method for getting a GridBagConstraints object
    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 0, 5);
        c.gridx = x;
        c.gridy = y;
        return c;
    }

    // another helper method for getting a GridBagConstraints object
    private GridBagConstraints getConstraints(int x, int y, int gridwidth) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 0, 5);
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = 2;
        return c;
    }

    // this method gets executed when the "Insert Stats" button is clicked
    private void insertButtonClicked() {
        // calls the insertData method 
        insertData();
    }
    
    // this method gets executed when the "Read Stats" button is clicked
    private void readButtonClicked() {
        try{
        readData();
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Please insert data first.");
        }
    }
    
    // this method gets executed when the "Help" button is clicked
    private void helpButtonClicked() {
        // displays a message into an option Pane
        String message = "1. Use Insert Stats option to write the stats to a file.\n" +
                "2. Use Read Stats option to read the stats from a file.";
        JOptionPane.showMessageDialog(null, message, "Help", JOptionPane.INFORMATION_MESSAGE); 
    }

    // this method gets executed when the "Exit" button is clicked
    private void exitButtonClicked() {
        // exits the application 
        System.exit(0);
    }
       
    // this method gets executed when the "Insert Data" button is clicked in the Insert Stats frame
    private void insertDataButtonClicked(JDialog insertFrame) {
        //added 5-14 - KJC
        boolean isValid = false;
        while(!isValid){
            try{
                LocalDate.parse(dateField.getText());
                isValid=true;
            }
            catch(DateTimeParseException err){
                JOptionPane.showMessageDialog(insertFrame, "The date field is either in the wrong format or is empty.\nThe correct format is yyyy/mm/dd.", "Date Entry" ,JOptionPane.ERROR_MESSAGE);
                dateField.setText("");
                return;
            }
        }
        //
        if (nameField.getText().isEmpty()){
            JOptionPane.showMessageDialog(insertFrame, "The Name field is empty.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        }
        //added 5-14 - KJC / Chris
        else if(!nameField.getText().contains(" ")){
            JOptionPane.showMessageDialog(insertFrame, "Please insert both a first and a last name.", "Error" ,JOptionPane.ERROR_MESSAGE);
        }
        //
        else if (abField.getText().isEmpty()){
            JOptionPane.showMessageDialog(insertFrame, "The At Bat field is empty.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        } else if(abField.getText().equals("0")){ 
            JOptionPane.showMessageDialog(insertFrame, "The At Bat field cannot be zero.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        }
        else if (rField.getText().isEmpty()){
            JOptionPane.showMessageDialog(insertFrame, "The Runs field is empty.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        } else if (hField.getText().isEmpty()){
            JOptionPane.showMessageDialog(insertFrame, "The 1 Base Hit field is empty.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        } else if (h2Field.getText().isEmpty()){
            JOptionPane.showMessageDialog(insertFrame, "The 2 Base Hit field is empty.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        } else if (h3Field.getText().isEmpty()){
            JOptionPane.showMessageDialog(insertFrame, "The 3 Base Hit field is empty.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        } else if (hrField.getText().isEmpty()){
            JOptionPane.showMessageDialog(insertFrame, "The Homerun field is empty.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        } else if (rbiField.getText().isEmpty()){
            JOptionPane.showMessageDialog(insertFrame, "The Runs Batetd In field is empty.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        } else if (bbField.getText().isEmpty()){
            JOptionPane.showMessageDialog(insertFrame, "The Walk field is empty.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        } else if (soField.getText().isEmpty()){
            JOptionPane.showMessageDialog(insertFrame, "The Strikeout field is empty.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        } else if (poField.getText().isEmpty()){
            JOptionPane.showMessageDialog(insertFrame, "The Putout field is empty.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        } else if (aField.getText().isEmpty()){
            JOptionPane.showMessageDialog(insertFrame, "The Assist field is empty.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        } else if (lobField.getText().isEmpty()){
            JOptionPane.showMessageDialog(insertFrame, "The Left on Base field is empty.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        } else if (sfField.getText().isEmpty()){
            JOptionPane.showMessageDialog(insertFrame, "The Sacrifice Fly field is empty.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        } else if (hbpField.getText().isEmpty()){
            JOptionPane.showMessageDialog(insertFrame, "The Hit-by-Pitch field is empty.", "Error" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
        } 
        else {
           Batter player = new Batter();
            String[] name = nameField.getText().split(" ");
            String firstName = name[0];
            String lastName = name[1];

            player.setFirstName(firstName);
            player.setLastName(lastName);
            player.setDateOfGame(dateField.getText());
            //added for data validation -- KJC 5-14
            try{
                player.setab(Integer.parseInt(abField.getText()));
                player.setr(Integer.parseInt(rField.getText()));
                player.seth(Integer.parseInt(hField.getText()));
                player.setH2(Integer.parseInt(h2Field.getText()));
                player.setH3(Integer.parseInt(h3Field.getText()));
                player.setHr(Integer.parseInt(hrField.getText()));
                player.setrbi(Integer.parseInt(rbiField.getText()));
                player.setbb(Integer.parseInt(bbField.getText()));
                player.setso(Integer.parseInt(soField.getText()));
                player.setpo(Integer.parseInt(poField.getText()));
                player.seta(Integer.parseInt(aField.getText()));
                player.setlob(Integer.parseInt(lobField.getText()));
                player.setSf(Integer.parseInt(sfField.getText()));
                player.setHbp(Integer.parseInt(hbpField.getText()));
                FileClass file = new FileClass(player);
                file.save(player);
                insertFrame.dispose();
            }
            catch(NumberFormatException err){
                JOptionPane.showMessageDialog(insertFrame, "Please use numbers for stats.", "Non-Numeric Entry" ,JOptionPane.ERROR_MESSAGE);//made these boxes look like errors. -KJC 5-14
            }
            //end KJC
        }
    }
    
    // reads a file when the Read File button is clicked
    private void readFileButtonClicked() throws FileNotFoundException, IOException {
        String selectedFile = "./games/" + (String) b.getSelectedValue();// Chris Blaylock 5/14/21
        // put all filenames into and arraylist so that we can read multiple files - cjb 
        ArrayList selectedFiles = (ArrayList) b.getSelectedValuesList();// Chris Blaylock 5/14/21
        String gameDate = (String) b.getSelectedValue();
        // write selected files to average.txt so that we can use it to make a report for multiple games
        String tempAverage = "average.txt";// Chris Blaylock 5/14/21
        FileWriter pw = new FileWriter(tempAverage);
        // loop through the selected games
        for (Object selectedFilei : selectedFiles) {// Chris Blaylock 5/14/21
            String file = "./games/" + selectedFilei; // we have to add the games directory here
            BufferedReader br = new BufferedReader(new FileReader(file));// Chris Blaylock 5/14/21
            String line = br.readLine();// Chris Blaylock 5/14/21
            while (line != null)// Chris Blaylock 5/14/21
            {
                pw.write(line + "\n"); // write to the average file
                line = br.readLine();// Chris Blaylock 5/14/21
            }
        }
        // closing resources    
        pw.flush();// Chris Blaylock 5/14/21
        pw.close();// Chris Blaylock 5/14/21
        if (selectedFiles.size() <= 1){// Chris Blaylock 5/14/21
            // start reports
            BufferedReader br = new BufferedReader(new FileReader(selectedFile));//added 5/6 - KJC
            String passFile = (String) b.getSelectedValue(); // added 5/5 -KJC
            ReportClass report = new ReportClass(passFile); //added 5/5 -KJC
            try {
                report.createHeader();//create header outside of loop to maintain -KJC
                String line;
                while ((line = br.readLine()) != null){
                    report.writeReport(line);//write report to file -KJC
                }
            }finally { // closes the file after it is read
                br.close();
            }

            //added 5/6 - KJC
            //updated 5/12 - KJC --Report now displays in a JTable

            String selectedReport = "./reports/" + gameDate.substring(0,10) + "_Report.txt";
            BufferedReader reader = new BufferedReader(new FileReader(selectedReport));
            DefaultTableModel model = new DefaultTableModel();//create table model to display report
            try {
                String line1;
                //create title
                String l = reader.readLine();
                String[] titles = l.split(",");

                for(String t : titles){
                    model.addColumn(t);//add to model for JTable (titles)
                }
                while ((line1 = reader.readLine()) != null){
                    String[] data = line1.split(",");
                    model.addRow(data);//add to model for JTable (data)
                }
            }finally { 

                JTable jt = new JTable();//create JTable
                jt.setModel(model);//set to created model
                jt.setEnabled(false);//disallow user to click on or edit any data.

                tableViewPrint(jt, gameDate.substring(0,10));//passes to allow for table printing.
                
                reader.close();// closes the file
            }
            //end KJC
        }
        else{
            // Chris Blaylock 5/14/21
            //Write all the selected files to one file
            BufferedReader br = new BufferedReader(new FileReader("average.txt")); 
            List<String[]> allBatters = new ArrayList<>();
            String line;
            //create a Treemap so that we can map out our data and sort it
            Map<String, ArrayList<Double>> batterMap = new TreeMap<>();
            try {
                int count = 1;
                // read everyline and assign a variable 
                while ((line = br.readLine()) != null){
                    String[] batter = line.split(",");
                    String  lName = batter[0].trim();
                    String  fName = batter[1].trim();
                    String fullName = lName+" "+fName;
                    double ab = Double.parseDouble(batter[2].trim());
                    double r = Integer.parseInt(batter[3].trim());
                    double h = Double.parseDouble(batter[4].trim());
                    double h2 = Double.parseDouble(batter[5].trim());
                    double h3 = Double.parseDouble(batter[6].trim());
                    double hr = Double.parseDouble(batter[7].trim());
                    double rbi = Integer.parseInt(batter[8].trim());
                    double bb = Double.parseDouble(batter[9].trim());
                    double so = Integer.parseInt(batter[10].trim());
                    double po = Integer.parseInt(batter[11].trim());
                    double a = Integer.parseInt(batter[12].trim());
                    double lob = Integer.parseInt(batter[13].trim());
                    double sf = Double.parseDouble(batter[14].trim());
                    double hbp = Double.parseDouble(batter[15].trim());
                    double hits = h + h2 + h3 + hr;//total hits
                    double batAvg = hits/ab;
                    double tBases = h + (2 * h2) + (3 * h3) + (4 * hr);
                    double sPercent = tBases / ab;
                    double onBasePercent = (hits + bb + hbp) / (ab + bb + hbp + sf);
                    String keyToBeChecked = fullName;
                    
                    // this will check if the first and last name is present in our treemap 
                    boolean isKeyPresent = batterMap.containsKey(keyToBeChecked);
                    // if the name exists we will average the values
                    if (isKeyPresent){
                        ArrayList<Double> averages =  new ArrayList<>(Arrays.asList(ab,r,h,h2,h3,hr,rbi,bb,so,po,a,lob,sf,hbp));
                        ArrayList<Double> toBeAveraged = batterMap.get(fullName);
                        //System.out.println(keyToBeChecked + " " + averages);
                        
                        int length = averages.size();
                        if (length != toBeAveraged.size()) { // Too many names, or too many numbers
                            // Fail
                        }
                        ArrayList<Double> array3 = new ArrayList<>(length); // Make a new list
                        for (int i = 0; i < length; i++) { // Loop through every name/stat combo
                            array3.add(averages.get(i) + toBeAveraged.get(i)/2); 
                        }
                        batterMap.put(fullName,array3); 
                        
                    // if it does not exist we will put that key value pair in our treemap   
                    }else{
                        batterMap.put(fullName, new ArrayList<>(Arrays.asList(ab,r,h,h2,h3,hr,rbi,bb,so,po,a,lob,sf,hbp))); 
                    }

                    allBatters.add(batter);    
                    count += 1;   
                }
                // create the panel that will display the report
                String firstDate = (String) selectedFiles.get(0);// get the first date selected
                String lastDate = (String) selectedFiles.get(selectedFiles.size() - 1);// get the last date selected
                String outputFilePath = "./reports/" + firstDate.replace(".txt", "-") + lastDate;// combine the dates to create one file name 
                File file = new File(outputFilePath);
                
                // Oh look another BufferedWriter, how cool.....
                BufferedWriter bf = null;
                try{
            
                        //create new BufferedWriter for the output file
                        bf = new BufferedWriter( new FileWriter(file) );
                        
                        //iterate map entries
                        for(Map.Entry<String, ArrayList<Double>> entry : batterMap.entrySet()){

                            //put key and value separated by a colon
                            bf.write( entry.getKey() + ":" + entry.getValue() );

                            //new line
                            bf.newLine();
                        }

                        bf.flush();

                    }catch(IOException e){
                        e.printStackTrace();
                    }
                // these will be used as the columns for our reports
                String[] columns = new String[] {"Name","At Bat","Runs","Hits","2 Base Hit ","3 Base Hit ","Home Run","Runners Batted In","Walk","Strike Out","Put Out","Assist","Left On Base","Sacrifice Fly","Hit-by-pitch"};//
                DefaultTableModel defaultModel = new DefaultTableModel(columns, 0);
                // create a JTable for the average table
                JTable averageTable = new JTable(defaultModel);
                
                averageTable.setEnabled(false);//disallow user to click on or edit any data.
                // assign our tree map to columns on our average table
                batterMap.entrySet().forEach(entry -> {
                    ArrayList<Double> stats = entry.getValue();
                    defaultModel.addRow(new Object[] {entry.getKey(), stats.get(0),stats.get(1),stats.get(2),stats.get(3),stats.get(4),stats.get(5),
                        stats.get(6),stats.get(7),stats.get(8),stats.get(9),stats.get(10),stats.get(11),stats.get(12),stats.get(13)
                    });
                });
                // display the table view 
                tableViewPrint(averageTable , outputFilePath.substring(10).replace(".txt", " ") + "Average Stats");
            }finally { 
                // close resource
                br.close();
            }
        }
    }
   
    //added 5/12 KJC
    //function accepts table and string parameters to create a dialog for viewing and printing the table created in readFileButtonClicked()
    private void tableViewPrint(JTable jt, String date){
        JDialog tableDialog = new JDialog();
        tableDialog.setTitle(date);//title of the dialog is the date of the game
        tableDialog.setDefaultCloseOperation(tableDialog.DISPOSE_ON_CLOSE);
        JScrollPane playerScrollpane = new JScrollPane(jt);//add table to scrollpane
        playerScrollpane.setPreferredSize(new Dimension(1500,250));//add size for scrollpane
        
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> tableDialog.dispose());
        
        //attempts to print the file, allows user to exit.
        JButton printButton = new JButton("Print");
        printButton.addActionListener(e -> {
            try {
                if (!jt.print()){
                    JOptionPane.showMessageDialog(null, "User cancelled printing.", "Error", JOptionPane.ERROR_MESSAGE);//user cancelled printing.
                }
            }
            catch (java.awt.print.PrinterException err){
                JOptionPane.showMessageDialog(null, err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);//there was an error printing.
            }
        });
        
        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(printButton);
        buttonPanel.add(exitButton);
        
        //scrollpane panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(playerScrollpane, getConstraints(0, 0));
        tableDialog.add(panel, BorderLayout.CENTER);
        tableDialog.add(buttonPanel, BorderLayout.SOUTH);
        tableDialog.setMinimumSize(new Dimension(1500, 250));
        tableDialog.setVisible(true);
        tableDialog.pack();
        tableDialog.setLocationRelativeTo(null);//display in center.
    }
    //end KJC
    
    // this method gets executed when the Clear button in the Insert Stats frame is clicked
    private void clearButtonClicked() {
        // clears all the text fields
        nameField.setText("");
        dateField.setText("");
        abField.setText("");
        rField.setText("");
        hField.setText("");
        h2Field.setText("");
        h3Field.setText("");
        hrField.setText("");
        rbiField.setText("");
        bbField.setText("");
        soField.setText("");
        poField.setText("");
        aField.setText("");
        lobField.setText("");
        sfField.setText("");
        hbpField.setText("");
    }
             
    // creates a frame that is used to insert stats to a file
    private void insertData(){
        // creates a frame
        JDialog insertFrame= new JDialog();
        insertFrame.setTitle("Insert Stats");
        insertFrame.setSize(400, 500); 
        insertFrame.setDefaultCloseOperation(insertFrame.DISPOSE_ON_CLOSE);
        
        // Instance variable taht creates text fields
        nameField = new JTextField();
        dateField = new JTextField();
        abField = new JTextField();
        rField = new JTextField();
        hField = new JTextField();
        h2Field = new JTextField();
        h3Field = new JTextField();
        hrField = new JTextField();
        rbiField = new JTextField();
        bbField = new JTextField();
        soField = new JTextField();
        poField = new JTextField();
        aField = new JTextField();
        lobField = new JTextField();
        sfField = new JTextField();
        hbpField = new JTextField();
        
        // sets the size of the text fields
        Dimension dim = new Dimension(180, 20);
        nameField.setPreferredSize(dim);
        dateField.setPreferredSize(dim);
        abField.setPreferredSize(dim);
        rField.setPreferredSize(dim);
        hField.setPreferredSize(dim);
        h2Field.setPreferredSize(dim);
        h3Field.setPreferredSize(dim);
        hrField.setPreferredSize(dim);
        rbiField.setPreferredSize(dim);
        bbField.setPreferredSize(dim);
        soField.setPreferredSize(dim);
        poField.setPreferredSize(dim);
        aField.setPreferredSize(dim);
        lobField.setPreferredSize(dim);
        sfField.setPreferredSize(dim);
        hbpField.setPreferredSize(dim);
        
        nameField.setMinimumSize(dim);
        dateField.setMinimumSize(dim);
        abField.setMinimumSize(dim);
        rField.setMinimumSize(dim);
        hField.setMinimumSize(dim);
        h2Field.setMinimumSize(dim);
        h3Field.setMinimumSize(dim);
        hrField.setMinimumSize(dim);
        rbiField.setMinimumSize(dim);
        bbField.setMinimumSize(dim);
        soField.setMinimumSize(dim);
        poField.setMinimumSize(dim);
        aField.setMinimumSize(dim);
        lobField.setMinimumSize(dim);
        sfField.setMinimumSize(dim);
        hbpField.setMinimumSize(dim);
        
        // Creates the buttons
        JButton insertDataButton = new JButton("Insert Data");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");

        // Attahces the action listener to the buttons
        insertDataButton.addActionListener(e -> {insertDataButtonClicked(insertFrame);});// -KJC added dispose to close frame after data is entered.
        clearButton.addActionListener(e -> clearButtonClicked());
        exitButton.addActionListener(e -> insertFrame.dispose());

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(insertDataButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);
        
        // main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(new JLabel("Name(First Last):"), getConstraints(0, 0));
        panel.add(nameField, getConstraints(1, 0));
        panel.add(new JLabel("Date(year-mm-dd):"), getConstraints(0, 1));
        panel.add(dateField, getConstraints(1, 1));
        panel.add(new JLabel("At Bat:"), getConstraints(0, 2));
        panel.add(abField, getConstraints(1, 2));
        panel.add(new JLabel("Runs:"), getConstraints(0, 3));
        panel.add(rField, getConstraints(1, 3));
        panel.add(new JLabel("1 Base Hit:"), getConstraints(0, 4));
        panel.add(hField, getConstraints(1, 4));         
        panel.add(new JLabel("2 Base Hit:"), getConstraints(0, 5));
        panel.add(h2Field, getConstraints(1, 5));
        panel.add(new JLabel("3 Base Hit:"), getConstraints(0, 6));
        panel.add(h3Field, getConstraints(1, 6));
        panel.add(new JLabel("Homerun:"), getConstraints(0, 7));
        panel.add(hrField, getConstraints(1, 7));       
        panel.add(new JLabel("Runs Batted In:"), getConstraints(0, 8));
        panel.add(rbiField, getConstraints(1, 8)); 
        panel.add(new JLabel("Walk:"), getConstraints(0, 9));
        panel.add(bbField, getConstraints(1, 9)); 
        panel.add(new JLabel("Strikeout:"), getConstraints(0, 10));
        panel.add(soField, getConstraints(1, 10)); 
        panel.add(new JLabel("Putout:"), getConstraints(0, 11));
        panel.add(poField, getConstraints(1, 11)); 
        panel.add(new JLabel("Assist:"), getConstraints(0, 12));
        panel.add(aField, getConstraints(1, 12)); 
        panel.add(new JLabel("Left on Base:"), getConstraints(0, 13));
        panel.add(lobField, getConstraints(1, 13)); 
        panel.add(new JLabel("Sacrifice Fly:"), getConstraints(0, 14));
        panel.add(sfField, getConstraints(1, 14)); 
        panel.add(new JLabel("Hit-by-Pitch:"), getConstraints(0, 15));
        panel.add(hbpField, getConstraints(1, 15)); 

        // adds the textField panel and the button panel in the specified position
        insertFrame.add(panel, BorderLayout.CENTER);
        insertFrame.add(buttonPanel, BorderLayout.SOUTH);
        
        
        insertFrame.setMinimumSize(new Dimension(420, 425));
        insertFrame.setVisible(true);
        insertFrame.pack();
        // displays the window in the center
        insertFrame.setLocationRelativeTo(null);
    }
    
    // creates a frame that is used to read the data from a file
    private void readData() {
             
    // creates a frame
        JDialog readFrame= new JDialog();
        readFrame.setTitle("Read Stats");
        readFrame.setDefaultCloseOperation(readFrame.DISPOSE_ON_CLOSE);
        
        JLabel heading = new JLabel("Select a file to read the data.");
        JLabel subHeading1 = new JLabel("Hold CTRL to select multiple files");
        JLabel subHeading2 = new JLabel("and see averages.");
        heading.setFont(new Font("Arial", Font.BOLD, 15));
        subHeading1.setFont(new Font("Arial", Font.BOLD, 15));
        subHeading2.setFont(new Font("Arial", Font.BOLD, 15));
        
        // creates a panel for the heading
        JPanel headingPanel = new JPanel(new GridBagLayout());
        headingPanel.add(heading, getConstraints(0, 5));
        headingPanel.add(subHeading1, getConstraints(0, 8));
        headingPanel.add(subHeading2, getConstraints(0, 12));
        headingPanel.setPreferredSize(new Dimension(240,130));
        headingPanel.setMinimumSize(new Dimension(240, 130));
        readFrame.getContentPane().add(headingPanel);
                
        // creates the buttons
        JButton readDataButton = new JButton("Read Data");
        JButton exitButton = new JButton("Exit");
        JList gamesList = new JList();
        // attaches the action listener to the buttons
        readDataButton.addActionListener(e -> {
            try {
                
                readFileButtonClicked();
                
            } catch (IOException ex) {
                Logger.getLogger(BaseballApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        exitButton.addActionListener(e -> readFrame.dispose());

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        // displays the buttons at the specified position
        buttonPanel.add(readDataButton);
        buttonPanel.add(exitButton);
        
        // creates a panel for the text area
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        games = new ArrayList<>();
        
        File folder = new File("./games");
        String[] pathnames;
        
        pathnames = folder.list();
        for (String pathname : pathnames) {
            // Print the names of files and directories          
            games.add(pathname);
        }     
        b = new JList(games.toArray());
        b.setPreferredSize(new Dimension(300, 220));
        b.setSelectedIndex(0);
        panel.add(b);
        
        gameSelected =  "./games/" + b.getSelectedValue().toString();
        
        //System.out.println(gameSelected);
        
        // adds the above text area and button panel to the specified position
        JScrollPane scroll = new JScrollPane(b, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(300, 200));
        panel.add(scroll);
        
        // adds the above text area and button panel to the specified position
        readFrame.add(panel, BorderLayout.EAST);
        readFrame.add(buttonPanel, BorderLayout.SOUTH);
        
        
        readFrame.setMinimumSize(new Dimension(580, 300));
        readFrame.setVisible(true);
        readFrame.pack();
        // displays the window in the center 
        readFrame.setLocationRelativeTo(null);
    }
    
    // method of JPanel used to draw images rather than background color
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        g.drawImage(image, 0, 0, this);             
    }
    
    // main method that calls the BaseballApp class and launches the GUI
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new BaseballApp();           
        });       
    }   
}
