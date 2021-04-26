package baseballstats;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;


//this page takes user input and adds stats to chosen player
public class addStats extends JPanel {

	private JLabel askBats;
	private JLabel askHits;
	private JLabel askRbi;
	private JLabel askHomeruns;
	private JTextField enterBats;
	private JTextField enterHits;
	private JTextField enterRbi;
	private JTextField enterHomeruns;
	private JPanel batsPanel;
	private JPanel hitsPanel;
	private JPanel rbiPanel;
	private JPanel homerunsPanel;
	
	private JButton back;
	private JButton save;
	private JPanel buttons;
	
	private JList list;
	private JScrollPane listScroller;
	private JLabel intro;
	private JPanel welcome;
	
	public addStats()
	{
		intro=new JLabel("Choose player below, fill in data, select save");
		welcome=new JPanel();
		welcome.add(intro);
		
		list=new JList(BaseBallApp.playerList);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(-1);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(2);
		listScroller=new JScrollPane(list);
		
		
		askBats=new JLabel("Number of at Bats:");
		enterBats=new JTextField();
		batsPanel=new JPanel(new GridLayout(1,2));
		batsPanel.add(askBats);
		batsPanel.add(enterBats);
		
		askHits=new JLabel("Number of Hits:");
		enterHits=new JTextField();
		hitsPanel=new JPanel(new GridLayout(1,2));
		hitsPanel.add(askHits);
		hitsPanel.add(enterHits);
		
		askRbi=new JLabel("Number of Rbi:");
		enterRbi=new JTextField();
		rbiPanel=new JPanel(new GridLayout(1,2));
		rbiPanel.add(askRbi);
		rbiPanel.add(enterRbi);
		
		askHomeruns=new JLabel("Number of Homeruns:");
		enterHomeruns=new JTextField();
		homerunsPanel=new JPanel(new GridLayout(1,2));
		homerunsPanel.add(askHomeruns);
		homerunsPanel.add(enterHomeruns);
		
		back=new JButton("Go Back");
		back.addActionListener(new ButtonListener());
		save=new JButton("Save");
		save.addActionListener(new ButtonListener());
		buttons=new JPanel(new GridLayout(1,2));
		buttons.add(back);
		buttons.add(save);
		
		
		setLayout(new GridLayout(7,1));
		add(welcome);
		add(listScroller);
		add(batsPanel);
		add(hitsPanel);
		add(rbiPanel);
		add(homerunsPanel);
		add(buttons);
		setSize(250,250);
	}
	
	public void reset()
	{
		intro.setText("Choose player below, fill in data, select save");
		list.updateUI();
	}
	
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source=event.getSource();
			if(source==save)
			{
				int index=list.getSelectedIndex();
				
				if(list!=null)
				{
					if(index<0)
						JOptionPane.showMessageDialog(BaseBallApp.frame, "Please choose or add a player");
					else{
						if(enterBats.getText().isEmpty() || enterHits.getText().isEmpty() || enterHomeruns.getText().isEmpty() || enterRbi.getText().isEmpty()){JOptionPane.showMessageDialog(BaseBallApp.frame, "Please fill in all boxes");}
						
						else{
							try{BaseBallApp.playerList.get(index).addGame(Integer.parseInt(enterBats.getText()), Integer.parseInt(enterHits.getText()), Integer.parseInt(enterHomeruns.getText()), Integer.parseInt(enterRbi.getText()));
						intro.setText("Player Data saved! Add more?");
						enterBats.setText("");
						enterHits.setText("");
						enterHomeruns.setText("");
						enterRbi.setText("");}catch(NumberFormatException e){JOptionPane.showMessageDialog(BaseBallApp.frame, "Please make sure you entered numerical values");}}
						
				}}
				else
					JOptionPane.showMessageDialog(BaseBallApp.frame, "Please choose or add a player");
			
			}
			if(source==back)
			{
				BaseBallApp.frame.remove(BaseBallApp.addStats);
				BaseBallApp.frame.add(BaseBallApp.homePage);
				BaseBallApp.frame.repaint();
				BaseBallApp.frame.validate();
				enterBats.setText("");
				enterHits.setText("");
				enterHomeruns.setText("");
				enterRbi.setText("");
			}
		}
	}
}
