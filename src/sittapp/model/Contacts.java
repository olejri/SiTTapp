/**
 * 
 */
package sittapp.model;

/**
 * @author Nelich
 *
 */
public class Contacts {
    String name;
    boolean add;
    
    public Contacts(String name,boolean add) {
        this.add = add;
        this.name = name;
    }
    
    public void setAdd(boolean add) {
        this.add = add;
    }
    
    public boolean getAdd() {
       return add;
    }
    
    public String getName() {
        return name;
    }
}
