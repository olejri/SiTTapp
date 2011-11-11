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
        Lesson m1 = new Lesson("PulsStep", "14.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "14:40", false,0);
        Lesson m2 = new Lesson("Pump", "14.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "15:30", false,0);
        Lesson m3 = new Lesson("Afro", "14.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "16:30", false,0);
        Lesson m4 = new Lesson("Kick-off", "14.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "18:30", false,0);
        Lesson tu1 = new Lesson("Pump", "15.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "07:45", false,0);
        Lesson tu2 = new Lesson("Kick-off", "15.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "12:30", false,0);
        Lesson tu3 = new Lesson("Step/pump", "15.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "14:45", false,0);
        Lesson tu4 = new Lesson("Pilates", "15.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "15:30", false,0);
        Lesson w1 = new Lesson("Step/pump", "16.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "08:30", false,0);
        Lesson w2 = new Lesson("Afro", "16.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "14:40", false,0);
        Lesson w3 = new Lesson("Yoga", "16.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "16:30", false,0);
        Lesson w4 = new Lesson("PulsStep", "16.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "17:40", false,0);
        Lesson th1 = new Lesson("Step&Core", "17.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "07:45", false,0);
        Lesson th2 = new Lesson("Step/pump", "17.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "12:30", false,0);
        Lesson th3 = new Lesson("Yoga", "17.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "14:45", false,0);
        Lesson th4 = new Lesson("Corebar", "17.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "16:30", false,0);
        Lesson f1 = new Lesson("Yoga", "11.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "07:45", false, -2);
        Lesson f2 = new Lesson("Corebar", "11.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "08:30", false,3);
        Lesson f3 = new Lesson("Pilates", "11.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "13:45", false,10);
        Lesson f4 = new Lesson("PulsStep", "11.11.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "14:30", false,-5);
        Lesson sa1 = new Lesson("Corebar", "12.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "10:30", false,20);
        Lesson sa2 = new Lesson("Pilates", "12.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "11:30", false,10);
        Lesson su1 = new Lesson("Afro", "13.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "15:00", false,4);
        Lesson su2 = new Lesson("PulsStep", "13.10.2011", "Med tradisjonsrike øvelser blir kroppen styrket og mer bevegelig. Timene avsluttes med avspenning", "16:00", false,1);
        m.add(m1);
        m.add(m2);
        m.add(m3);
        m.add(m4);
        tu.add(tu1);
        tu.add(tu2);
        tu.add(tu3);
        tu.add(tu4);
        w.add(w1);
        w.add(w2);
        w.add(w3);
        w.add(w4);
        th.add(th1);
        th.add(th2);
        th.add(th3);
        th.add(th4);
        f.add(f1);
        f.add(f2);
        f.add(f3);
        f.add(f4);
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
