/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseballstats;

import java.util.Vector;
import javax.swing.JFrame;

/**
 *
 * @author blayl
 */
public class BaseBallApp {

	//Different windows that will be used throughout the program
	static JFrame frame;
	static Homepage homePage;
	static createPlayerWindow createPlayer;
	static EditPlayer editPlayer;
	static addStats addStats;
	static viewStats viewStats;
	
	//list that will store all players created
	static Vector<Player> playerList;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		homePage=new Homepage();
		createPlayer=new createPlayerWindow();
		playerList=new Vector<Player>();
		editPlayer=new EditPlayer();
		addStats=new addStats();
		viewStats=new viewStats();
		
		frame=new JFrame("Baseball Stat Tracker");
		
		frame.add(homePage);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setSize(300,300);
		frame.setVisible(true);
	}

}
