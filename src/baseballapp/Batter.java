package baseballapp;

public class Batter 
{
    String lastName;
    private String firstName;
    private String position;
    private String dateOfGame;
    private int ab;
    private int r; 
    //added 5/5 KJC
    private int h; //will need to be converted to counting only 1-base hits.
    private int h2; //2 base hits
    private int h3; //3 base hits
    private int hr; //homeruns
    private int sf; //sacrifice fly
    private int hbp; //hit by pitch
    //
    private int rbi; 
    private int bb; 
    private int so; 
    private int po; 
    private int a; 
    private int lob; 

    public Batter(){
        this("","","","",0,0,0,0,0,0,0,0,0);
    }

    
    public Batter(String lastName,String firstName,String position,String dateOfGame,int ab,int r,int h,int rbi,int bb, int so, int po, int a, int lob){
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.dateOfGame = dateOfGame;
        this.ab = ab;
        this.r = r;
        this.h = h;
        this.rbi = rbi;
        this.bb = bb; 
        this.so = so;
        this.po = po;
        this.a = a;
        this.lob = lob;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public void setDateOfGame(String dateOfGame)
    {
        this.dateOfGame = dateOfGame;
    }
    
    public String getDateOfGame()
    {
        return dateOfGame;
    }
    
    public void setPosition(String position) 
    {
        this.position = position;
    }
    
    public String getPosition()
    {
        return position;
    }
    
    public void setab(int ab) 
    {
        this.ab = ab;
    }
      
    public int getab()
    {
        return ab;
    }
    
    public void setr(int r) 
    {
        this.r = r;
    }
    
    public int getr()
    {
        return r;
    }
    
    public void seth(int h) 
    {
        this.h = h;
    }
    
    public int geth()
    {
        return h;
    }
    
    //Added 5/5 to set up additional fields needed - KJC
    public int getH2(){
        return h2;
    }
    
    public void setH2(int h2){
        this.h2 = h2;
    }
    
    public int getH3(){
        return h3;
    }
    
    public void setH3(int h3){
        this.h3 = h3;
    }
    
    public int getHr(){
        return hr;
    }
    
    public void setHr(int hr){
        this.hr = hr;
    }
    
    public int getSf(){
        return sf;
    }
    
    public void setSf(int sf){
        this.sf = sf;
    }
    
    public int getHbp(){
        return hbp;
    }
    
    public void setHbp(int hbp){
        this.hbp = hbp;
    }
    //
    
    public void setrbi(int rbi) 
    {
        this.rbi = rbi;
    }
    
    public int getrbi()
    {
        return rbi;
    }
    
    public void setbb(int bb) 
    {
        this.bb = bb;
    }
    
    public int getbb()
    {
        return bb;
    }
    
    public void setso(int so) 
    {
        this.so = so;
    }
    
    public int getso()
    {
        return so;
    }
    
    public void setpo(int po)
    {
        this.po = po;
    }
    
    public int getpo()
    {
        return po;
    }
    
    public void seta(int a) 
    {
        this.a = a;
    }
    
    public int geta()
    {
        return a;
    }
    
    public void setlob(int lob)
    {
        this.lob = lob;
    }
    
    public int getlob()
    {
        return lob;
    }
   
}