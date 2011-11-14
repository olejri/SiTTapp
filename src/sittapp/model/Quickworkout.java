/**
 * 
 */
package sittapp.model;

import java.util.ArrayList;

/**
 * @author Nelich
 *
 */
public class Quickworkout {
    ArrayList<Exercise> exe = new ArrayList<Exercise>();


    public Quickworkout() {

    }
    public ArrayList<Exercise> getDemo() {
        exe.add(new Exercise("Benkpress", 50,8, 4, "15min"));
        exe.add(new Exercise("Skråbenk", 40,12, 3, "10min"));
        exe.add(new Exercise("Triceps", 20,12, 3, "10min"));
        exe.add(new Exercise("Biceps", 20,12, 3, "10min"));
        exe.add(new Exercise("Arnold", 20,12, 2, "5min"));
        exe.add(new Exercise("Nedtrekk", 50,8, 4, "15min"));
        return exe;

    }

}
