package sittapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Workout implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	// VARIABLES
	public String type = "";
	public CharSequence[] strengthItems = {"Benk", "Skråbenk", "Triceps", "Biceps", "Arnold Curl", "Biceps Curl", "Øl Curl"};
	public boolean[] strengthSelected = makeFalseList(strengthItems);
	public CharSequence[] cardioItems = {"Sykkel", "Tredemølle", "Løpeskistavsak"};
	public boolean[] cardioSelected = makeFalseList(cardioItems);
	public String alert;
	public ArrayList<WorkoutRepeat> repeat = new ArrayList<WorkoutRepeat>();
	public String clock;
	public Lesson lesson;
	
	public Workout() {
		repeat.add(new WorkoutRepeat("MAN"));
		repeat.add(new WorkoutRepeat("TIR"));
		repeat.add(new WorkoutRepeat("ONS"));
		repeat.add(new WorkoutRepeat("TOR"));
		repeat.add(new WorkoutRepeat("FRE"));
		repeat.add(new WorkoutRepeat("LØR"));
		repeat.add(new WorkoutRepeat("SØN"));
	}
	
	public void randomize(String type) {
		this.type = type;
		Random gen = new Random();
		if (type.equals("STRENGTH")) {
			for (int i=0; i<strengthSelected.length; i++) {
				strengthSelected[i] = gen.nextBoolean();
			}
		}
		else if (type.equals("CARDIO")) {
			for (int i=0; i<cardioSelected.length; i++) {
				cardioSelected[i] = gen.nextBoolean();
			}
		}
		else {
			JointTraining jt = new JointTraining();
			jt.testData();
			lesson = jt.m.get(gen.nextInt(jt.m.size()));
		}
		int onLuck = 7;
		for (WorkoutRepeat r : repeat) {
			if (gen.nextInt(onLuck)==0) {
				r.on = true;
				onLuck += 3;
			}
			else onLuck--;
		}
		this.clock = (gen.nextInt(24)+1)+":00";
		if (this.clock.length()==4) this.clock = "0"+this.clock;
	}
	
	public void repeatClick(String day) {
		for (WorkoutRepeat r : repeat) {
			if (r.day.equals(day)) r.on = !r.on;
		}
	}
	
	public String repeatToString() {
		ArrayList<String> list = new ArrayList<String>();
		for (WorkoutRepeat r : repeat) {
			if (r.on) list.add(r.day);
		}
		return stringifyArrayList(list);
	}
	
	public String selectedItemsString() {
		ArrayList<String> list = new ArrayList<String>();
		if (this.type.equals("STRENGTH")) {
			for (int i=0; i<strengthSelected.length; i++) {
				if (strengthSelected[i]) list.add(""+strengthItems[i]);
			}
		}
		else if (this.type.equals("CARDIO")) {
			for (int i=0; i<cardioSelected.length; i++) {
				if (cardioSelected[i]) list.add(""+cardioItems[i]);
			}
		}
		else {
			return lesson.name;
		}
		return stringifyArrayList(list);
	}
	
	public boolean isValid() {
		if (type.equals("")) return false;
		else return true;
	}
	
	private String stringifyArrayList(ArrayList<String> list) {
		String ret = "";
		for (String s : list) {
			ret += s;
			if (list.indexOf(s)!=list.size()-1) ret += ", ";
		}
		return ret;
	}
	
    private boolean[] makeFalseList(CharSequence[] items) {
    	boolean[] list = new boolean[items.length];
    	for (int i=0; i<items.length; i++) {
    		list[i] = false;
    	}
    	return list;
    }
    
	public String toString() {
		String ret = "";
		if (type.equals("STRENGTH")) {
			for (int i=0; i<strengthItems.length; i++) {
				if (strengthSelected[i]) {
					ret += strengthItems[i]+", ";
				}
			}
		}
		else {
			for (int i=0; i<cardioItems.length; i++) {
				if (cardioSelected[i]) {
					ret += cardioItems[i]+", ";
				}
			}
		}
		return ret;
	}
}
