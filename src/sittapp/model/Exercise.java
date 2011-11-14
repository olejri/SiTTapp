/**
 * 
 */
package sittapp.model;

/**
 * @author Nelich
 *
 */
public class Exercise {
    String name,time;
    int kg, rep,set;
    
    
    public Exercise(String name, int kg, int rep, int set, String time) {
        this.name = name;
        this.kg = kg;
        this.rep = rep;
        this.set = set;
        this.time = time;
        
    }


    /**
     * @return the time
     */
    public String getTime() {
        return this.time;
    }


    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }


    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }


    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return the kg
     */
    public int getKg() {
        return this.kg;
    }


    /**
     * @param kg the kg to set
     */
    public void setKg(int kg) {
        this.kg = kg;
    }


    /**
     * @return the rep
     */
    public int getRep() {
        return this.rep;
    }


    /**
     * @param rep the rep to set
     */
    public void setRep(int rep) {
        this.rep = rep;
    }


    /**
     * @return the set
     */
    public int getSet() {
        return this.set;
    }


    /**
     * @param set the set to set
     */
    public void setSet(int set) {
        this.set = set;
    }
    
    

}
