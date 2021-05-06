/*
Name: Kush Patel
Date: 05/03/2021    
*/

package baseballapp;
import static baseballapp.FileClass.games;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    private JTextArea statsTextArea;
    private BufferedImage image;
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
        readData();           
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
    
    /* this method gets executed when the "Open File" button is clicked 
    *  both, the insert stats frame and in the read stats frame
    */
    private void fileButtonClicked() {
        // creates a file chooser
        JFileChooser fileChooser = new JFileChooser();
        // sets the default directory to the current directory
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        // adds another options that allows to choose only .txt files
        FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
        fileChooser.setFileFilter(restrict);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            //ArrayList<String> batter = new ArrayList<>();
            File selectedFile = fileChooser.getSelectedFile();            
            String selectedFileName = selectedFile.getName();                    
            System.out.println("Selected file: " + selectedFileName);
        }
    }
    
    // this method gets executed when the "Insert Data" button is clicked in the Insert Stats frame
    private void insertDataButtonClicked() {
        if (nameField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The Name field is empty.");
        }else if (dateField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The Date field is empty.");
        } else if (abField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The At Bat field is empty.");
        } else if (rField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The Runs field is empty.");
        } else if (hField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The 1 Base Hit field is empty.");
        } else if (h2Field.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The 2 Base Hit field is empty.");
        } else if (h3Field.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The 3 Base Hit field is empty.");
        } else if (hrField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The Homerun field is empty.");
        } else if (rbiField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The Runs Batetd In field is empty.");
        } else if (bbField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The Walk field is empty.");
        } else if (soField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The Strikeout field is empty.");
        } else if (poField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The Putout field is empty.");
        } else if (aField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The Assist field is empty.");
        } else if (lobField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The Left on Base field is empty.");
        } else if (sfField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The Sacrifice Fly field is empty.");
        } else if (hbpField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The Hit-by-Pitch field is empty.");
        } else {
            Batter player = new Batter();
            String[] name = nameField.getText().split(" ");
            String firstName = name[0];
            String lastName = name[1];

            player.setFirstName(firstName);
            player.setLastName(lastName);
            player.setDateOfGame(dateField.getText());
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
        }
    }
    
    // reads a file when the Read File button is clicked
    private void readFileButtonClicked() throws FileNotFoundException, IOException {
        String selectedFile = "./games/" + (String) b.getSelectedValue();
        BufferedReader br = new BufferedReader(new FileReader(selectedFile));
        String passFile = (String) b.getSelectedValue(); // added 5/5 -KJC
        ReportClass report = new ReportClass(passFile); //added 5/5 -KJC
        try {
            report.createHeader();//create header outside of loop to maintain -KJC
            String line;
            while ((line = br.readLine()) != null){
                JOptionPane.showMessageDialog(null, line, (String) b.getSelectedValue(), JOptionPane.INFORMATION_MESSAGE);
                report.writeReport(line);//write report to file -KJC
            }
        }finally { // closes the file after it is being read
            br.close();
        }
    }
    
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
    
    // this method gets executed when the Clear button in the Read Stats frame is clicked
    private void clearDataButtonClicked(){
        // clears the text area
        statsTextArea.setText("");
    }
    
    
    
    // creates a frame that is used to insert stats to a file
    private void insertData(){
        // creates a frame
        JFrame insertFrame= new JFrame();
        setTitle("Insert Stats");
        insertFrame.setSize(400, 500); 
        insertFrame.setDefaultCloseOperation(insertFrame.EXIT_ON_CLOSE);
        
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
        JButton fileButton = new JButton("Open a File");
        JButton insertDataButton = new JButton("Insert Data");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");

        // Attahces the action listener to the buttons
        fileButton.addActionListener(e -> fileButtonClicked());
        insertDataButton.addActionListener(e -> insertDataButtonClicked());
        clearButton.addActionListener(e -> clearButtonClicked());
        exitButton.addActionListener(e -> dispose());

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(fileButton);
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
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        
        setMinimumSize(new Dimension(420, 425));
        setVisible(true);
        pack();
        // displays the window in the center
        setLocationRelativeTo(null);
    }
    
    // creates a frame that is used to read the data from a file
    private void readData() {
             
    // creates a frame
        JFrame readFrame= new JFrame();
        setTitle("Read Stats");
        readFrame.setDefaultCloseOperation(readFrame.EXIT_ON_CLOSE);
                
        // creates the buttons
        JButton fileButton = new JButton("Open a File");
        JButton readDataButton = new JButton("Read Data");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");
        JList gamesList = new JList();
        // attaches the action listener to the buttons
        fileButton.addActionListener(e -> fileButtonClicked());
        readDataButton.addActionListener(e -> {
            try {
                
                readFileButtonClicked();
                
            } catch (IOException ex) {
                Logger.getLogger(BaseballApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        clearButton.addActionListener(e -> clearDataButtonClicked());
        exitButton.addActionListener(e -> dispose());

        // button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        // displays the buttons at the specified position
        buttonPanel.add(fileButton, getConstraints(4, 0));
        buttonPanel.add(readDataButton, getConstraints(4, 1));
        buttonPanel.add(clearButton, getConstraints(4, 2));
        buttonPanel.add(exitButton, getConstraints(4, 3));
        
        // creates a panel for the text area
        JPanel panel = new JPanel();
        Dimension itemDim = new Dimension(1500, 1500);
        statsTextArea = new JTextArea(17,35);
        statsTextArea.setLineWrap(true);
        statsTextArea.setWrapStyleWord(true);
        statsTextArea.setEditable(false); // sets the text area to not editable
        statsTextArea.setPreferredSize(itemDim);
        statsTextArea.setMinimumSize(itemDim);
        games = new ArrayList<>();
        
        File folder = new File("./games");
        String[] pathnames;
        
        pathnames = folder.list();
        for (String pathname : pathnames) {
            // Print the names of files and directories          
            games.add(pathname);
        }     
        b = new JList(games.toArray());
        b.setPreferredSize(new Dimension(300, 200));
        b.setSelectedIndex(0);
        panel.add(b);
        
        gameSelected =  "./games/" + b.getSelectedValue().toString();
        
        //System.out.println(gameSelected);
        
        // creates a scroll pane and adds it to the statsTextArea
        
        JScrollPane scroll = new JScrollPane(b, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroll);

        // adds the above text area and button panel to the specified position
        add(panel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.EAST);
        
        
        setMinimumSize(new Dimension(490, 380));
        setVisible(true);
        pack();
        // displays the window in the center 
        setLocationRelativeTo(null);
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
