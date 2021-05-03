package baseballapp;

public class Batter 
{
    private String lastName;
    private String firstName;
    private String position;
    private int ab;
    private int r; 
    private int h; 
    private int rbi; 
    private int bb; 
    private int so; 
    private int po; 
    private int a; 
    private int lob; 

    public Batter(){
        this("","","",0,0,0,0,0,0,0,0,0);
    }


    public Batter(String lastName,String firstName,String position,int ab,int r,int h,int rbi,int bb, int so, int po, int a, int lob){
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
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
        this.lastName = firstName;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public void setPosition(String position) 
    {
        this.lastName = position;
    }
    
    public String getPosition()
    {
        return position;
    }
    
    public void setab(String ab) 
    {
        this.lastName = ab;
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