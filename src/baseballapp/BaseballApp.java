/*
Name: Kush Patel
Date: 05/03/2021    
*/

package baseballapp;
import static baseballapp.FileClass.games;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

// BaseballApp class extends the JFrame Class
public class BaseballApp extends JFrame{
    // instance variables
    private JTextField nameField;
    private JTextField abField;
    private JTextField rField;
    private JTextField hField;
    private JTextField rbiField;
    private JTextField bbField;
    private JTextField soField;
    private JTextField poField;
    private JTextField aField;
    private JTextField lobField;
    private JTextArea statsTextArea;
    private FileClass file = new FileClass();
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
      
      // displays the window in the center
      frame.setLocationRelativeTo(null);
      //sets the size of the window and sets its visibility to true
      frame.setSize(300, 300);
      frame.setVisible(true);
      pack();
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
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
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
        
    }
    
    // this method gets executed when the Clear button in the Insert Stats frame is clicked
    private void clearButtonClicked() {
        // clears all the text fields
        nameField.setText("");
        abField.setText("");
        rField.setText("");
        hField.setText("");
        rbiField.setText("");
        bbField.setText("");
        soField.setText("");
        poField.setText("");
        aField.setText("");
        lobField.setText("");
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
        abField = new JTextField();
        rField = new JTextField();
        hField = new JTextField();
        rbiField = new JTextField();
        bbField = new JTextField();
        soField = new JTextField();
        poField = new JTextField();
        aField = new JTextField();
        lobField = new JTextField();
        
        // sets the size of the text fields
        Dimension dim = new Dimension(180, 20);
        nameField.setPreferredSize(dim);
        abField.setPreferredSize(dim);
        rField.setPreferredSize(dim);
        hField.setPreferredSize(dim);
        rbiField.setPreferredSize(dim);
        bbField.setPreferredSize(dim);
        soField.setPreferredSize(dim);
        poField.setPreferredSize(dim);
        aField.setPreferredSize(dim);
        lobField.setPreferredSize(dim);
        nameField.setMinimumSize(dim);
        abField.setMinimumSize(dim);
        rField.setMinimumSize(dim);
        hField.setMinimumSize(dim);
        rbiField.setMinimumSize(dim);
        bbField.setMinimumSize(dim);
        soField.setMinimumSize(dim);
        poField.setMinimumSize(dim);
        aField.setMinimumSize(dim);
        lobField.setMinimumSize(dim);
        
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
        panel.add(new JLabel("Name:"), getConstraints(0, 0));
        panel.add(nameField, getConstraints(1, 0));
        panel.add(new JLabel("At Bat:"), getConstraints(0, 1));
        panel.add(abField, getConstraints(1, 1));
        panel.add(new JLabel("Runs:"), getConstraints(0, 2));
        panel.add(rField, getConstraints(1, 2));
        panel.add(new JLabel("Hits:"), getConstraints(0, 3));
        panel.add(hField, getConstraints(1, 3));    
        panel.add(new JLabel("Runs Batted In:"), getConstraints(0, 4));
        panel.add(rbiField, getConstraints(1, 4)); 
        panel.add(new JLabel("Walk:"), getConstraints(0, 5));
        panel.add(bbField, getConstraints(1, 5)); 
        panel.add(new JLabel("Strikeout:"), getConstraints(0, 6));
        panel.add(soField, getConstraints(1, 6)); 
        panel.add(new JLabel("Putout:"), getConstraints(0, 7));
        panel.add(poField, getConstraints(1, 7)); 
        panel.add(new JLabel("Assist:"), getConstraints(0, 8));
        panel.add(aField, getConstraints(1, 8)); 
        panel.add(new JLabel("Left on Base:"), getConstraints(0, 9));
        panel.add(lobField, getConstraints(1, 9)); 

        // adds the textField panel and the button panel in the specified position
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Displays the window in the center
        setLocationRelativeTo(null);
        setSize(new Dimension(390, 380));
        setVisible(true);
        pack();
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
        b.setPreferredSize(new Dimension(200, 200));
        b.setSelectedIndex(0);
        panel.add(b);
        
        gameSelected =  "./games/" + b.getSelectedValue().toString();
        
        System.out.println(gameSelected);
        
        // creates a scroll pane and adds it to the statsTextArea
        
        JScrollPane scroll = new JScrollPane(b, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroll);

        // adds the above text area and button panel to the specified position
        add(panel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.EAST);
        
        // displays the window in the center 
        setLocationRelativeTo(null);
        setSize(new Dimension(490, 380));
        setVisible(true);
        pack();
        
        
        

    }
    
    
    
    // main method that calls the BaseballApp class and launches the GUI
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new BaseballApp();           
        });
        
    }    
    
    
    private void readFileButtonClicked() throws FileNotFoundException, IOException {
        String selectedFile = "./games/" + (String) b.getSelectedValue();
        try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                int i;
                while ((i=br.read()) != -1){
                    System.out.print((char) i);
                } 
            }
        }
    }
