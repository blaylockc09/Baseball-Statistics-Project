
package baseballapp;

import java.util.*;
import java.io.*;
import java.nio.file.*;

public class BatterTextFile implements DAO<Batter>{
    private List<Batter> batters = null;
    private Path batterPath = null;
    private File batterFile = null;
    
    
   
    
    
    public BatterTextFile() {
        batterPath = Paths.get("games/playerStats.txt");
        batterFile = batterPath.toFile();
        batters = this.getAll();
   
    }
    
    
    @Override
    public Batter get(String code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Batter> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Batter t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Batter t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Batter t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
