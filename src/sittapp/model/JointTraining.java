/**
 * 
 */
package sittapp.model;

import java.util.ArrayList;

/**
 * @author Nelich
 *
 */
public class JointTraining {
    ArrayList<Lesson> m = new ArrayList<Lesson>();
    ArrayList<Lesson> tu = new ArrayList<Lesson>();
    ArrayList<Lesson> w = new ArrayList<Lesson>();
    ArrayList<Lesson> th = new ArrayList<Lesson>();
    ArrayList<Lesson> f = new ArrayList<Lesson>();
    ArrayList<Lesson> sa = new ArrayList<Lesson>();
    ArrayList<Lesson> su = new ArrayList<Lesson>();
    
    public JointTraining() {
        
        
    }
    
    
    public void testData() {        
        Lesson m1 = new Lesson("PulsStep", "24.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "14:40", false);
        Lesson m2 = new Lesson("Pump", "24.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "15:30", false);
        Lesson tu1 = new Lesson("Pump", "25.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "07:45", false);
        Lesson tu2 = new Lesson("Kick-off", "25.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "12:30", false);
        Lesson w1 = new Lesson("Step/pump", "26.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "08:30", false);
        Lesson w2 = new Lesson("Afro", "26.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "14:40", false);
        Lesson th1 = new Lesson("Step&Core", "27.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "07:45", false);
        Lesson th2 = new Lesson("Step/pump", "27.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "12:30", false);
        Lesson f1 = new Lesson("Yoga", "28.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "07:45", false);
        Lesson f2 = new Lesson("Corebar", "28.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "08:30", false);
        Lesson sa1 = new Lesson("Corebar", "29.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "10:30", false);
        Lesson sa2 = new Lesson("Pilates", "29.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "11:30", false);
        Lesson su1 = new Lesson("Afro", "30.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "15:00", false);
        Lesson su2 = new Lesson("PulsStep", "30.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "16:00", false);
        m.add(m1);
        m.add(m2);
        tu.add(tu1);
        tu.add(tu2);
        w.add(w1);
        w.add(w2);
        th.add(th1);
        th.add(th2);
        f.add(f1);
        f.add(f2);
        sa.add(sa1);
        sa.add(sa2);
        su.add(su1);
        su.add(su2);

    }
    
    public ArrayList<Lesson> getM(){return m;}
    public ArrayList<Lesson> getTu(){return tu;}
    public ArrayList<Lesson> getW(){return w;}
    public ArrayList<Lesson> getTh(){return th;}
    public ArrayList<Lesson> getF(){return f;}
    public ArrayList<Lesson> getSa(){return sa;}
    public ArrayList<Lesson> getSu(){return su;}
    

}
