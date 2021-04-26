
package baseballstats;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;


public class EditPlayer extends JPanel {

	private JButton edit;
	private JButton back;
	private JButton delete;
	private JList list;
	private JScrollPane listScroller;
	
	public EditPlayer()
	{
		list=new JList(BaseBallApp.playerList);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(-1);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(3);
		listScroller=new JScrollPane(list);
		
		edit=new JButton("Edit Player Name");
		edit.addActionListener(new ButtonListener());
		delete=new JButton("Delete Player");
		delete.addActionListener(new ButtonListener());;
		back=new JButton("Go Back");
		back.addActionListener(new ButtonListener());
		setLayout(new GridLayout(4,1));
		

		add(listScroller);
		add(edit);
		add(delete);
		add(back);
	}
	
	public void reset()
	{
		list.updateUI();
	}
	
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source=event.getSource();
			if(source==back)
			{
				BaseBallApp.frame.remove(BaseBallApp.editPlayer);
				BaseBallApp.frame.add(BaseBallApp.homePage);
				BaseBallApp.frame.repaint();
				BaseBallApp.frame.validate();
			}
			try{
			if(source==delete)
			{
				int index=list.getSelectedIndex();
				
				if(list!=null)
				{
					if(index<0)
						JOptionPane.showMessageDialog(BaseBallApp.frame, "Please choose or add a player");
					else
						BaseBallApp.playerList.remove(index);
						updateUI();
				}
				else
					JOptionPane.showMessageDialog(BaseBallApp.frame, "Please choose or add a player");
			}
			if(source==edit)
			{
				int index=list.getSelectedIndex();
				
				if(list!=null)
				{
					if(index<0)
						JOptionPane.showMessageDialog(BaseBallApp.frame, "Please choose or add a player");
					else
						{
							String name=BaseBallApp.playerList.get(index).getName();
							BaseBallApp.createPlayer.updatename(name,index);
							
							
							BaseBallApp.frame.remove(BaseBallApp.editPlayer);
							BaseBallApp.frame.add(BaseBallApp.createPlayer);
							BaseBallApp.frame.repaint();
							BaseBallApp.frame.validate();					
						}
				}
				else
					JOptionPane.showMessageDialog(BaseBallApp.frame, "Please choose or add a player");
				}
			}
			catch(ArrayIndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(BaseBallApp.frame, "It Looks like the list is Empty");
			}
		}
	}
}
