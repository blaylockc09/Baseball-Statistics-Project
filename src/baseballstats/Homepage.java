
package baseballstats;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;



//this is the initial page that is displayed to user, there are several options to for the user to choose from
//and this page updates view accordingly
public class Homepage extends JPanel {
	private JButton createPlayer;
	private JButton editPlayer;
	private JButton addStats;
	private JButton viewStats;
	private JButton Quit;
        private FileClass file = new FileClass();
	
	public Homepage()
	{
		createPlayer=new JButton("Create Player");
		createPlayer.addActionListener(new ButtonListener());
		editPlayer=new JButton("Edit/Delete Player");
		editPlayer.addActionListener(new ButtonListener());
		addStats=new JButton("Add Player Stats");
		addStats.addActionListener(new ButtonListener());
		viewStats=new JButton("View Player Stats");
		viewStats.addActionListener(new ButtonListener());
		Quit=new JButton("Quit");
		Quit.addActionListener(new ButtonListener());
		
		
		setLayout(new GridLayout(5,1));
		add(createPlayer);
		add(editPlayer);
		add(addStats);
		add(viewStats);
		add(Quit);
	}
	
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source=event.getSource();
			if(source==createPlayer)
			{
				BaseBallApp.frame.remove(BaseBallApp.homePage);
				BaseBallApp.frame.add(BaseBallApp.createPlayer);
				BaseBallApp.frame.repaint();
				BaseBallApp.frame.validate();
			}
			if(source==editPlayer)
			{
				BaseBallApp.editPlayer.reset();
				BaseBallApp.frame.remove(BaseBallApp.homePage);
				BaseBallApp.frame.add(BaseBallApp.editPlayer);
				BaseBallApp.frame.repaint();
				BaseBallApp.frame.validate();
			}
			if(source==addStats)
			{
				BaseBallApp.addStats.reset();
				BaseBallApp.frame.remove(BaseBallApp.homePage);
				BaseBallApp.frame.add(BaseBallApp.addStats);
				BaseBallApp.frame.repaint();
				BaseBallApp.frame.validate();
			}
			if(source==viewStats)
			{
				BaseBallApp.viewStats.reset();
				BaseBallApp.frame.remove(BaseBallApp.homePage);
				BaseBallApp.frame.add(BaseBallApp.viewStats);
				BaseBallApp.frame.repaint();
				BaseBallApp.frame.validate();
			}
			if(source==Quit)
			{
                                if(file.saveAll(BaseBallApp.playerList)){
                                    System.out.println("Data saved to file");
                                    System.exit(0);
                                } else {
                                    System.out.println("Something went wrong saving the data to the file");
                                    System.exit(0);
                                }
				
			}
		}
	}

}
