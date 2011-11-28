/**
 * 
 */
package sittapp.model;

import java.io.Serializable;

/**
 * @author Nelich
 *
 */
public class Lesson implements Serializable {
    String name, date, info, time;
    boolean join;
    int full;

    
    public Lesson(String name, String date, String info, String time, boolean join, int full) {
        this.name = name;
        this.date = date;
        this.info = info;
        this.time = time;
        this.join = join;
        this.full = full;
        
    }
      
    public String getName() {return name;}
    public String getDate() {return date;}
    public String getInfo() {return info;}
    public String getTime() {return time;}
    
    public void setJoin(boolean join) {
        this.join = join;
    }
    
    public boolean getJoin() {return join;}
    public int getFull() {return full;}
    public void setFull(int full) {
        this.full = full;
   }
    
}
