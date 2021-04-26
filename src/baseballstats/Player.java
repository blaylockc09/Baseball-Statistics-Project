
package baseballstats;

import java.math.RoundingMode;
import java.text.DecimalFormat;


public class Player {

	private String name;
	private int atBats;
	private int hits;
	private int homeruns;
	private int rbi;
	private int games;
	DecimalFormat df;
	
	public Player(String name)
	{
		this.name=name;
		this.atBats=0;
		this.hits=0;
		this.homeruns=0;
		this.rbi=0;
		this.games=0;
	}
	
	public void addGame(int atBats, int hits, int homeruns, int rbi)
	{
		this.atBats+=atBats;
		this.hits+=hits;
		this.homeruns+=homeruns;
		this.rbi+=rbi;
		games++;
	}
	
	public String getName()
	{
		return name;
	}
	public int getGames()
	{
		return games;
	}
	public int getBats()
	{
		return atBats;
	}
	public int getHits()
	{
		return hits;
	}
	public int getHomeruns()
	{
		return homeruns;
	}
	public int getRbi()
	{
		return rbi;
	}
	public void updateName(String name)
	{
		this.name=name;
	}
	public String getBatting()
	{
		if(atBats==0){
                    return ".000";
                }else{
                    df= new DecimalFormat(".000");
                    df.setRoundingMode(RoundingMode.HALF_DOWN);
                    double average=(double)hits/atBats;
                    return df.format(average);
                }
	}
        @Override
	public String toString()
	{
		return name;
	}
}
