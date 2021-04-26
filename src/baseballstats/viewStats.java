
package baseballstats;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


//this page displays user stats for chosen player
public class viewStats extends JPanel{
	private JList list;
	private JScrollPane listScroller;
	private JLabel intro;
	private JLabel name;
	private JLabel games;
	private JLabel bats;
	private JLabel hits;
	private JLabel battingavg;
	private JLabel homeruns;
	private JLabel rbi;
	private JButton back;
	private JButton view;

	public viewStats()
	{
		//This took me forever to figure so I am pretty proud of it. It takes data from player vector list and 
		//passes it to a list which then is used by a JScrollPane to display the players
		list=new JList(BaseBallApp.playerList);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(-1);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(2);
		listScroller=new JScrollPane(list);
		
		intro=new JLabel("Select player below and click view");
		view=new JButton("View Player Stats");
		view.addActionListener(new ButtonListener());
		name=new JLabel("Player Name: ");
		games=new JLabel("Number of games: ");
		bats=new JLabel("Number of at Bats: ");
		hits=new JLabel("Number of hits: ");
		battingavg=new JLabel("Batting Average: ");
		homeruns=new JLabel("Number of homeruns: ");
		rbi=new JLabel("number of Rbi: ");
		
		
		back=new JButton("Back");
		back.addActionListener(new ButtonListener());
		setLayout(new GridLayout(11,1));
		add(intro);
		add(listScroller);
		add(view);
		add(name);
		add(games);
		add(bats);
		add(hits);
		add(battingavg);
		add(homeruns);
		add(rbi);
		add(back);
	}
	
	public void reset()
	{
		name.setText("Player Name: ");
		games.setText("Number of games: ");
		bats.setText("Number of at Bats: ");
		hits.setText("Number of hits: ");
		battingavg.setText("Batting Average: ");
		homeruns.setText("Number of homeruns: ");
		rbi.setText("number of Rbi: ");
		list.updateUI();

	}

	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source=event.getSource();
			if(source==view)
			{
				int index=list.getSelectedIndex();
				
				if(list!=null)
				{
					if(index<0)
						JOptionPane.showMessageDialog(BaseBallApp.frame, "Please choose or add a player");
					else
					{
						reset();
						name.setText(name.getText()+BaseBallApp.playerList.get(index).getName());
						games.setText(games.getText()+BaseBallApp.playerList.get(index).getGames());
						bats.setText(bats.getText()+BaseBallApp.playerList.get(index).getBats());
						hits.setText(hits.getText()+BaseBallApp.playerList.get(index).getHits());
						battingavg.setText(battingavg.getText()+BaseBallApp.playerList.get(index).getBatting());
						homeruns.setText(homeruns.getText()+BaseBallApp.playerList.get(index).getHomeruns());
						rbi.setText(rbi.getText()+BaseBallApp.playerList.get(index).getRbi());
					}
						
				}
				else
					JOptionPane.showMessageDialog(BaseBallApp.frame, "Please choose or add a player");
			}
			if(source==back)
			{
				BaseBallApp.frame.remove(BaseBallApp.viewStats);
				BaseBallApp.frame.add(BaseBallApp.homePage);
				BaseBallApp.frame.repaint();
				BaseBallApp.frame.validate();
			}
			
		}
	}
}
