/**
 * 
 */
package sittapp.model;

/**
 * @author Nelich
 *
 */
public class Lesson {
    String name, date, info, time;
    boolean join;

    
    public Lesson(String name, String date, String info, String time, boolean join) {
        this.name = name;
        this.date = date;
        this.info = info;
        this.time = time;
        this.join = join;
        
    }
      
    public String getName() {return name;}
    public String getDate() {return date;}
    public String getInfo() {return info;}
    public String getTime() {return time;}
    
    public void setJoin(boolean join) {
        this.join = join;
    }
    
    public boolean getJoin() {return join;}
    
}
