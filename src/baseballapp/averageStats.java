
package baseballapp;

import java.util.Objects;


public class averageStats {
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
    
    public averageStats(String lastName,String firstName,String position,String dateOfGame,int ab,int r,int h, int h2, int h3, int hr, int rbi,int bb, int so, int po, int a, int lob, int sf, int hbp){
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.dateOfGame = dateOfGame;
        this.ab = ab;
        this.r = r;
        this.h = h;
        this.h2 = h2;
        this.h3 = h3;
        this.hr = hr;
        this.rbi = rbi;
        this.bb = bb; 
        this.so = so;
        this.po = po;
        this.a = a;
        this.lob = lob;
        this.sf = sf;
        this.hbp = hbp;
    
    }

    averageStats(String line) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        averageStats that = (averageStats) o;
        return Objects.equals(lastName, that.lastName);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(isbn, palletNumber);
//    }

    @Override
    public String toString() {
        return "Batter{" + firstName +  lastName + '}';
    }

    public averageStats merge(averageStats other) {
        assert(this.equals(other));
        return new averageStats(this.lastName = lastName,
        this.firstName = firstName,
        this.position = position,
        this.dateOfGame = dateOfGame,
        this.ab = ab,
        this.r = r,
        this.h = h,
        this.h2 = h2,
        this.h3 = h3,
        this.hr = hr,
        this.rbi = rbi,
        this.bb = bb,
        this.so = so,
        this.po = po,
        this.a = a,
        this.lob = lob,
        this.sf = sf,
        this.hbp = hbp);
    }
    
    
}
