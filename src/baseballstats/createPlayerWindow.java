
package baseballstats;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class createPlayerWindow extends JPanel{

	private JLabel askforname;
	private JTextField entername;
	private JButton save;
	private JButton back;
	private JPanel getInfo;
	private JPanel buttons;
	
	//When a player is being edited
	private JPanel updateButtons;
	private JButton cancel;
	private JButton update;
	private int indexing;
	
	public createPlayerWindow()
	{
		askforname=new JLabel("Enter Player Name:", JLabel.CENTER);
		entername=new JTextField("");
		getInfo=new JPanel(new GridLayout(2,1));
		getInfo.add(askforname);
		getInfo.add(entername);
		
		save=new JButton("Save");
		save.addActionListener(new ButtonListener());
		back=new JButton("Back");
		back.addActionListener(new ButtonListener());
		buttons=new JPanel(new GridLayout(1,2));
		buttons.add(back);
		buttons.add(save);
		
		
		setLayout(new GridLayout(2,1));
		add(getInfo);
		add(buttons);
		
		updateButtons=new JPanel(new GridLayout(1,2));
		cancel=new JButton("Cancel");
		cancel.addActionListener(new ButtonListener());
		update=new JButton("Update");
		update.addActionListener(new ButtonListener());
		updateButtons.add(cancel);
		updateButtons.add(update);
		indexing=0;
	}
	
	public void updatename(String name, int index)
	{
		entername.setText(name);
		remove(buttons);
		add(updateButtons);
		indexing=index;
	}
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			if(source==save)
			{
				if(entername.getText().equals(""))
					JOptionPane.showMessageDialog(createPlayerWindow.this, "Please Enter a Name before Clicking Save");
				else
				{
					
					
					Player player=new Player(entername.getText());
					BaseBallApp.playerList.add(player);
					
					
					
					entername.setText("");
					BaseBallApp.frame.remove(BaseBallApp.createPlayer);
					BaseBallApp.frame.add(BaseBallApp.homePage);
					BaseBallApp.frame.repaint();
					BaseBallApp.frame.validate();
				}
			}
			if(source==back)
			{
				entername.setText("");
				BaseBallApp.frame.remove(BaseBallApp.createPlayer);
				BaseBallApp.frame.add(BaseBallApp.homePage);
				BaseBallApp.frame.repaint();
				BaseBallApp.frame.validate();
			}
			if(source==cancel)
			{
				entername.setText("");
				BaseBallApp.frame.remove(BaseBallApp.createPlayer);
				BaseBallApp.frame.add(BaseBallApp.editPlayer);
				BaseBallApp.frame.repaint();
				BaseBallApp.frame.validate();
			}
			if(source==update)
			{
				if(entername.getText().equals(""))
					JOptionPane.showMessageDialog(createPlayerWindow.this, "Please Enter a Name before Clicking Update");
				else{
				String temp=entername.getText();
				BaseBallApp.playerList.get(indexing).updateName(temp);
				remove(updateButtons);
				add(buttons);
				entername.setText("");
				repaint();
				revalidate();
				BaseBallApp.frame.remove(BaseBallApp.createPlayer);
				BaseBallApp.frame.add(BaseBallApp.editPlayer);
				BaseBallApp.frame.repaint();
				BaseBallApp.frame.validate();
				}
			}
		}
	}
}
