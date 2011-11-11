package sittapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Workout implements Serializable  {
	private static final long serialVersionUID = 1L;
	public CharSequence[] weightItems = {"Benk", "Skråbenk", "Triceps", "Biceps", "Arnold Curl", "Biceps Curl", "Øl Curl"};
	public boolean[] weightSelected = makeFalseList(weightItems);
	public CharSequence[] cardioItems = {"Sykkel", "Tredemølle", "Løpeskistavsak"};
	public boolean[] cardioSelected = makeFalseList(cardioItems);
	
	// VARIABLES
	public String type = "";
	
	/*
	public void setType(String type) {
		this.type = type;
		excercices.clear();
		if (type.equals("WEIGHT")) {
			excercices.add(new Item("Benk"));
			excercices.add(new Item("Skråbenk"));
			excercices.add(new Item("Triceps"));
			excercices.add(new Item("Biceps"));
			excercices.add(new Item("Arnold Curl"));
			excercices.add(new Item("Biceps Curl"));
			excercices.add(new Item("Øl Curl"));
		}
		else {
			excercices.add(new Item("Sykkel"));
			excercices.add(new Item("Tredemølle"));
			excercices.add(new Item("Løpeskistavsak"));
			
		}
	}*/
	
	/*
	public String toString() {
		String ret = "Type: "+type+" - Excercices: ";
		for (Item i : excercices) {
			ret += i.name+", ";
		}
		return ret;
	}*/
	
	
	public String toString() {
		String ret = "Type: "+type+" - Excercices: ";
		if (type.equals("WEIGHT")) {
			for (int i=0; i<weightItems.length; i++) {
				if (weightSelected[i]) {
					ret += weightItems[i]+", ";
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
	
    private boolean[] makeFalseList(CharSequence[] items) {
    	boolean[] list = new boolean[items.length];
    	for (int i=0; i<items.length; i++) {
    		list[i] = false;
    	}
    	return list;
    }
}
